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

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mesibo.uihelper.Utils.Utils;

import java.util.List;

public class ProductTourActivity extends AppCompatActivity {
 
    static int NUM_PAGES = 1;
 
    ViewPager pager;
    PagerAdapter pagerAdapter;
    LinearLayout circles;
    Button skip;
    Button done;
    ImageButton next;
    boolean isOpaque = true;
    List<WelcomeScreen> mScreenList = null;
    MesiboUiHelperConfig mConfig = null;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mConfig = MesiboUiHelper.getConfig();
        if(null == mConfig) {
            return;
        }

        mScreenList = mConfig.mScreens;
        NUM_PAGES = mScreenList.size();

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
 
        setContentView(R.layout.activity_tutorial);
        skip = Button.class.cast(findViewById(R.id.skip));
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTutorial();
            }
        });
 
        next = ImageButton.class.cast(findViewById(R.id.next));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager.getAdapter() == null) {
                    pager.setAdapter(pagerAdapter);
                    pager.setCurrentItem(0);
                }

                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
        });
 
        done = Button.class.cast(findViewById(R.id.done));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTutorial();
            }
        });
 
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        if(mConfig.mScreenAnimation && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            pager.setPageTransformer(true, new CrossfadePageTransformer());
        }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(true)
                    return;

                if (position == NUM_PAGES - 2 && positionOffset > 0) {
                    if (isOpaque) {
                        pager.setBackgroundColor(Color.TRANSPARENT);
                        isOpaque = false;
                    }
                } else {
                    if (!isOpaque) {
                        pager.setBackgroundColor(getResources().getColor(R.color.primary_material_light));
                        isOpaque = true;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
                //NUM_PAGES - 2 because we are using last page as end of the tutorial
                // so when user is scrolling and reaches last dummy page, it can go to login etc
                if (position == NUM_PAGES - 2) {
                    skip.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    done.setVisibility(View.VISIBLE);
                } else if (position < NUM_PAGES - 2) {
                    skip.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    done.setVisibility(View.GONE);
                } else if (position == NUM_PAGES - 1) {
                    endTutorial();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
 
        buildCircles();
    }

    private boolean checkPermissions() {
        if(null == mConfig.mPermissions)
            return true;

        return Utils.aquireUserPermissions(this, mConfig.mPermissions, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pager!=null){
            pager.clearOnPageChangeListeners();
        }
    }

    private void buildCircles(){
        circles = LinearLayout.class.cast(findViewById(R.id.circles));
 
        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (5 * scale + 0.5f);
 
        for(int i = 0 ; i < NUM_PAGES - 1 ; i++){
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.drawable.ic_swipe_indicator_white_18dp);
            circle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            circle.setAdjustViewBounds(true);
            circle.setPadding(padding, 0, padding, 0);
            circles.addView(circle);
        }
 
        setIndicator(0);
    }
 
    private void setIndicator(int index){
        if(index < NUM_PAGES){
            for(int i = 0 ; i < NUM_PAGES - 1 ; i++){
                ImageView circle = (ImageView) circles.getChildAt(i);
                if(i == index){
                	circle.setColorFilter(getResources().getColor(R.color.text_selected));
                }else {
                	circle.setColorFilter(getResources().getColor(android.R.color.transparent));
                }
            }
        }
    }
 
    private void endTutorial(){

        if(!checkPermissions())
            return;

        if(MesiboUiHelper.mProductTourListener != null) {
            MesiboUiHelper.mProductTourListener.onProductTourCompleted(this);
        }
        finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
 
    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
 
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
 
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
 
        @Override
        public Fragment getItem(int position) {
            ProductTourFragment tp = ProductTourFragment.newInstance(position);
            return tp;
        }
 
        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Parcelable saveState() {
            // Do Nothing
            return null;
        }

        /*
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment f = (Fragment)super.instantiateItem(container, position);
            Bundle savedFragmentState = f.mSavedFragmentState;
            if (savedFragmentState != null) {
                savedFragmentState.setClassLoader(f.getClass().getClassLoader());
            }
            return f;
        }
        */
    }



	public class CrossfadePageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();
 
            View backgroundView = page.findViewById(R.id.welcome_fragment);
            View text_head= page.findViewById(R.id.heading);
            View text_content = page.findViewById(R.id.content);
            View welcomeImage01 = page.findViewById(R.id.welcomeImage);

            if(0 <= position && position < 1){
                page.setTranslationX(pageWidth * -position);
            }
            if(-1 < position && position < 0){
                page.setTranslationX(pageWidth * -position);
            }

            if(position <= -1.0f || position >= 1.0f) {
            } else if( position == 0.0f ) {
            } else {
                if(backgroundView != null) {
                    backgroundView.setAlpha(1.0f - Math.abs(position));
                }

                if (text_head != null) {
                    text_head.setTranslationX(pageWidth * position);
                    text_head.setAlpha(1.0f - Math.abs(position));
                }

                if (text_content != null) {
                    text_content.setTranslationX(pageWidth * position);
                    text_content.setAlpha(1.0f - Math.abs(position));
                }

                if (welcomeImage01 != null) {
                    welcomeImage01.setTranslationX((float)(pageWidth/2 * position));
                    welcomeImage01.setAlpha(1.0f - Math.abs(position));
                }
            }
        }

        //This is another transform, we can select above or this
        public void transformPage2(View view, float position) {
            view.setTranslationX(view.getWidth() * -position);

            if(position <= -1.0F || position >= 1.0F) {
                view.setAlpha(0.0F);
            } else if( position == 0.0F ) {
                view.setAlpha(1.0F);
            } else {
                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setAlpha(1.0F - Math.abs(position));
            }
        }
    }

    private boolean mPermissionAlert = false;
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        for(int i=0; i < grantResults.length; i++) {

            if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                if(mPermissionAlert) {
                    DialogInterface.OnClickListener handler = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface var1, int var2) {
                            finish();
                        }
                    };
                    Utils.showAlert(this, "Permission Error", mConfig.mPermissionsDeniedMessage, handler, handler);
                    return;
                }

                DialogInterface.OnClickListener handler = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface var1, int var2) {
                        mPermissionAlert = true;
                        endTutorial();
                    }
                };

                Utils.showAlert(this, "Permissions required", mConfig.mPermissionsRequestMessage, handler, handler);
                return;
            }
        }

        endTutorial();

    }
}
