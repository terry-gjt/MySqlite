package com.terrygjt.mysqlite;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;

/**
 * Created by terry on 2019-05-26.
 * AsyncTask的使用步骤有3个：
 * 创建 AsyncTask 子类 & 根据需求实现核心方法
 * 创建 AsyncTask子类的实例对象（即 任务实例）
 * 手动调用execute(（）从而执行异步线程任务
 */

public class C extends AsyncTask<Image, Integer, Bitmap> {
    @Override
    protected Bitmap doInBackground(Image... params) {
        if (params == null || params.length < 1 || params[0] == null) {
            return null;
        }
        Image image = params[0];
//        Bitmap bitmap=imagetobitmap(image);
//        sendbitmap(bitmap);
//        if (photoout != null) {
//            return bitmap;
//        }
        return null;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
//        if (mVirtualDisplay != null) {
//            mVirtualDisplay.release();
//        }
    }
}
