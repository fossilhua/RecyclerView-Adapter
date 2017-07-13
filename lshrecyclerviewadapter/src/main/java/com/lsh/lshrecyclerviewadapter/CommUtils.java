package com.lsh.lshrecyclerviewadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by 123 on 2017/7/13 15:06.
 */

public class CommUtils {

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int convert(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }


}
