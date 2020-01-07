/* By obtaining and/or copying this work, you agree that you have read,
 * understood and will comply with the following terms and conditions.
 *
 * Copyright (c) 2020 Mesibo
 * https://mesibo.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the terms and condition mentioned
 * on https://mesibo.com as well as following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions, the following disclaimer and links to documentation and
 * source code repository.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of Mesibo nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior
 * written permission.
 *
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Documentation
 * https://mesibo.com/documentation/
 *
 * Source Code Repository
 * https://github.com/mesibo/ui-modules-android

 */
package com.mesibo.uihelper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mesibo.uihelper.BuildConfig;
import com.mesibo.uihelper.R;

public class GrantPermissionFragment extends Fragment {
    private static int mTextColor, mBackgroundColor;
    private static int mIcon1, mIcon2, mIcon3;
    private static int mHeaderText, mSubHeaderText;
    private static boolean mAskPermisiionActivty = false;
    private static Context mContext;

    public static GrantPermissionFragment newInstance(Context context, int textColor, int backgroundColor, int icon1, int icon2, int icon3, int headerText, int subHeaderString, boolean askPermisiionActivty) {
        GrantPermissionFragment fragment = new GrantPermissionFragment();
        mContext = context;
        mTextColor = textColor;
        mBackgroundColor = backgroundColor;
        mIcon1 = icon1;
        mIcon2 = icon2;
        mIcon3 = icon3;
        mHeaderText = headerText;
        mSubHeaderText = subHeaderString;
        mAskPermisiionActivty = askPermisiionActivty;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grant_permission, container, false);


        TextView headerTv = view.findViewById(R.id.headingTV);
        TextView subHeaderTv = view.findViewById(R.id.subHeadingTV);
        ImageView icon1 = view.findViewById(R.id.icon1);
        ImageView icon2 = view.findViewById(R.id.icon2);
        ImageView icon3 = view.findViewById(R.id.icon3);
        TextView exitTv = view.findViewById(R.id.menuExitTV);
        TextView settingTv = view.findViewById(R.id.menuSettingsTV);
        TextView grantPermissionTV = view.findViewById(R.id.grantPermisiionTV);
        RelativeLayout rootlayout = view.findViewById(R.id.rootLayout);
        RelativeLayout menuItemLayout = view.findViewById(R.id.menuItemsLayout);
        RelativeLayout grantPermisionLayout = view.findViewById(R.id.grantPermissionLayout);
        RelativeLayout bottomLayout = view.findViewById(R.id.bottom_menu_layout);


        if (mAskPermisiionActivty) {

            grantPermisionLayout.setVisibility(View.VISIBLE);
            menuItemLayout.setVisibility(View.GONE);

        } else {
            grantPermisionLayout.setVisibility(View.GONE);
            menuItemLayout.setVisibility(View.VISIBLE);
        }


        Resources r = mContext.getResources();
        if (null != r) {


            bottomLayout.setBackgroundColor(r.getColor(mBackgroundColor));
            rootlayout.setBackgroundColor(r.getColor(mBackgroundColor));
            headerTv.setTextColor(r.getColor(mTextColor));
            subHeaderTv.setTextColor(r.getColor(mTextColor));
            exitTv.setTextColor(r.getColor(mTextColor));
            settingTv.setTextColor(r.getColor(mTextColor));
            grantPermissionTV.setTextColor(r.getColor(mTextColor));
            headerTv.setText(r.getString(mHeaderText));
            subHeaderTv.setText(r.getString(mSubHeaderText));


            Bitmap mIcon1IV = tint(BitmapFactory.decodeResource(r, mIcon1), mTextColor);
            Bitmap mIcon2IV = tint(BitmapFactory.decodeResource(r, mIcon2), mTextColor);
            Bitmap mIcon3IV = tint(BitmapFactory.decodeResource(r, mIcon3), mTextColor);

            icon1.setImageBitmap(mIcon1IV);
            icon2.setImageBitmap(mIcon2IV);
            icon3.setImageBitmap(mIcon3IV);


        } else {

        }

        exitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        settingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build intent that displays the App settings screen.
                Intent intent = new Intent();
                intent.setAction(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",
                        BuildConfig.APPLICATION_ID, null);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        grantPermissionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }


    public static Bitmap tint(Bitmap bmp, int color) {
        //int color = Color.parseColor(color);

        int red = (color & 0xFF0000) / 0xFFFF;
        int green = (color & 0xFF00) / 0xFF;
        int blue = color
                & 0xFF;

        float[] matrix = {0, 0, 0, 0, red,
                0, 0, 0, 0, green,
                0, 0, 0, 0, blue,
                0, 0, 0, 1, 0};

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        Paint paint = new Paint();
        paint.setColorFilter(colorFilter);

        //ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
        //paint.setColorFilter(filter);


        //Bitmap resultBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
        if (bmp.isMutable()) {

        }

        Bitmap resultBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);

        //image.setImageBitmap(resultBitmap);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, paint);
        return resultBitmap;
    }
}
