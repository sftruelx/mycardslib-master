/*
 * ******************************************************************************
 *   Copyright (c) 2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */

package it.gmariotti.cardslib.demo.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.gmariotti.cardslib.demo.R;
import it.gmariotti.cardslib.demo.fragment.NativeDashFragment;
import it.gmariotti.cardslib.demo.fragment.nativeview.NativeCardFragment;
import it.gmariotti.cardslib.demo.fragment.nativeview.NativeCardWithListFragment;
import it.gmariotti.cardslib.demo.fragment.nativeview.NativeChangeValueCardFragment;
import it.gmariotti.cardslib.demo.fragment.nativeview.NativeHeaderFragment;
import it.gmariotti.cardslib.demo.fragment.nativeview.NativeListBaseFragment;
import it.gmariotti.cardslib.demo.fragment.nativeview.NativeMaterialCardFragment;

/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class TestActivity extends BaseActivity  {

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_TEST;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFinishing()) {
            return;
        }

        setContentView(R.layout.carddemo_activity_test);
       if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new NativeMaterialCardFragment())
                    .commit();
        }
        LinearLayout linear = (LinearLayout)findViewById(R.id.piclist);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
        params.setMargins(20, 20, 20, 20);
        ImageView img=new ImageView(this);
        img.setMaxHeight(200);
        img.setMaxWidth(200);
        img.setAdjustViewBounds(true);
        img.setImageResource(R.drawable.im_beach);
        img.setScaleType(ImageView.ScaleType.CENTER);
        img.setLayoutParams(params);
        linear.addView(img);
        ImageView img5=new ImageView(this);
        img5.setMaxHeight(200);
        img5.setMaxWidth(200);
        img5.setAdjustViewBounds(true);
        img5.setImageResource(R.drawable.im_beach);
        img5.setScaleType(ImageView.ScaleType.CENTER);
        img5.setLayoutParams(params);
        linear.addView(img5);
        ImageView img0=new ImageView(this);
        img0.setMaxHeight(200);
        img0.setMaxWidth(200);
        img0.setAdjustViewBounds(true);
        img0.setImageResource(R.drawable.im_beach);
        img0.setScaleType(ImageView.ScaleType.CENTER);
        img0.setLayoutParams(params);
        linear.addView(img0);
        ImageView img1=new ImageView(this);
        img1.setMaxHeight(200);
        img1.setMaxWidth(200);
        img1.setAdjustViewBounds(true);
        img1.setImageResource(R.drawable.im_beach);
        img1.setScaleType(ImageView.ScaleType.CENTER);
        img1.setLayoutParams(params);
        linear.addView(img1);
        ImageView img2=new ImageView(this);
        img2.setMaxHeight(200);
        img2.setMaxWidth(200);
        img2.setAdjustViewBounds(true);
        img2.setImageResource(R.drawable.im_beach);
        img2.setScaleType(ImageView.ScaleType.CENTER);
        img2.setLayoutParams(params);
        linear.addView(img2);
        ImageView img3=new ImageView(this);
        img3.setMaxHeight(200);
        img3.setMaxWidth(200);
        img3.setAdjustViewBounds(true);
        img3.setImageResource(R.drawable.im_beach);
        img3.setScaleType(ImageView.ScaleType.CENTER);
        img3.setLayoutParams(params);
        linear.addView(img3);
        TextView tx = (TextView) findViewById(R.id.guidelines_text);
        tx.setText(R.string.test_text);
        tx.setMovementMethod(new LinkMovementMethod());


        overridePendingTransition(0, 0);
    }


}
