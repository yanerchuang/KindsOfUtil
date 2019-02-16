package com.ywj.util;//package com.ywj.util;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.support.annotation.DrawableRes;
//import android.widget.ImageView;
//
//import com.bumptech.glide.load.MultiTransformation;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.bitmap.CenterCrop;
//import com.ywj.R;
//import com.ywj.widget.GlideRoundTransform;
//
//import java.io.File;
//
///**
// * 类描述：Glide图片加载<br/>
// * Created by hyx on 2017/6/21.
// */
//
//public class GlideUtil {
//
//    public static void loadImg(Context context, String url, ImageView imageView) {
//        GlideApp.with(context)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }
//    public static void loadImg(Context context, String url, ImageView imageView, int placeholder, int error) {
//        GlideApp.with(context)
//                .load(url)
//                .placeholder(placeholder)
//                .error(error)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }
//
//    public static void loadImgRound(Context context, String url, ImageView imageView, @DrawableRes int placeholderId, @DrawableRes int errorId, int radius) {
//        GlideApp.with(context)
//                .load(url)
//                .placeholder(placeholderId)
//                .error(errorId)
//                .transform(new MultiTransformation<Bitmap>(new CenterCrop(),new GlideRoundTransform(radius)))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }
//
//    public static void loadImgRound(Context context, String url, ImageView imageView) {
//
//        GlideApp.with(context)
//                .load(url)
//                .fitCenter()
//                .placeholder(R.color.bg_img_gray)
//                .error(R.color.bg_img_gray)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .thumbnail(0.1f)////用原图的1/10作为缩略图
//                .into(imageView);
//    }
//
//
//    public static void loadImgCircle(Context context, String url, ImageView imageView, @DrawableRes int placeholderId, @DrawableRes int errorId) {
//        GlideApp.with(context)
//                .load(url)
//                .placeholder(placeholderId)
//                .error(errorId)
//                .circleCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }
//
//    public static void loadImg(Context context, File file, ImageView imageView, int placeholder, int error) {
//        GlideApp.with(context)
//                .load(file)
//                .placeholder(placeholder)
//                .error(error)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }
//
//    public static <T> void loadImg(Context context, T t, int placeholder, int error, ImageView imageView) {
//        GlideApp.with(context)
//                .load(t)
//                .placeholder(placeholder)
//                .error(error)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imageView);
//    }
//
//}
