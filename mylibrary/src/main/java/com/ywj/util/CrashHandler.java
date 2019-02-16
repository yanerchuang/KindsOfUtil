package com.ywj.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = CrashHandler.class.getSimpleName();
    private UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler instance;
    private Context mContext;
    private Map<String, String> infos = new HashMap<>();

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            //退出程序      
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }


    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        collectDeviceInfo(mContext);

//        new Thread() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                ToastUtil.show(mContext,"很抱歉,程序出现异常,即将退出.");
//                Looper.loop();
//            }
//        }.start();
        saveCatchInfo2File(ex);
        return true;
    }


    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }


    private void saveCatchInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            String path = mContext.getApplicationContext().getExternalCacheDir().getAbsolutePath() + File.separator + "Crash";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(path + File.separator + fileName);
            fos.write(sb.toString().getBytes());
            sendCrashLog2PM(path + File.separator + fileName);
            fos.close();
            return;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return;
    }

    private void sendCrashLog2PM(String fileName) {
        if (!new File(fileName).exists()) {
            return;
        }
        FileInputStream fis = null;
        BufferedReader reader = null;
        String s = null;
        try {
            fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis, "GBK"));
            while (true) {
                s = reader.readLine();
                if (s == null) {
                    break;
                }
                Log.i("info", s.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {   // 关闭流
            try {
                reader.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}    