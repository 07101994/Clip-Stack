package com.catchingnow.tinyclipboardmanager;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by heruoxin on 15/3/4.
 */

public class MyUtil {
    public final static String PACKAGE_NAME = "tinyclipboardmanager";

    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static String stringLengthCut(String string) {
        return stringLengthCut(string, 200);
    }

    public static String stringLengthCut(String string, int length) {
        string = string.trim();
        return  (string.length() > length) ?
                string.substring(0, length - 2).trim()+"…"
                : string.trim();
    }

    public static String getFormatDate(Context context, Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.date_format));
        return dateFormat.format(date);
    }

    public static String getFormatTime(Context context, Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.time_format));
        return dateFormat.format(date);
    }

    public static String getFormatTimeWithSecond(Context context, Date date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.time_format_with_second));
        return dateFormat.format(date);
    }

    public static class ResizeWidthAnimation extends Animation {
        private int mWidth;
        private int mStartWidth;
        private View mView;

        public ResizeWidthAnimation(View view, int width)
        {
            mView = view;
            mWidth = width;
            mStartWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            int newWidth = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);

            mView.getLayoutParams().width = newWidth;
            mView.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight)
        {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds()
        {
            return true;
        }
    }

    public static void requestBackup(Context context) {
        Log.d(MyUtil.PACKAGE_NAME, "requestBackup");
        BackupManager backupManager = new BackupManager(context);
        backupManager.dataChanged();
    }

    public static SharedPreferences getLocalSharedPreferences(Context context) {
        //return PreferenceManager.getDefaultSharedPreferences(context);
        return context.getSharedPreferences("LocalSharedPreference", Context.MODE_PRIVATE);
    }
}
