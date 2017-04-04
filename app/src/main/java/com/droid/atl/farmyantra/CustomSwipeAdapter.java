package com.droid.atl.farmyantra;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Objects;

import static java.lang.System.arraycopy;

/**
 * Created by dev on 27-Mar-17.
 */

public class CustomSwipeAdapter extends PagerAdapter {

    private int[] img_res;// = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
    private int[] img_res_slider = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
    private int[] img_res_tractor = {R.drawable.testimg1, R.drawable.testimg2, R.drawable.testimg3, R.drawable.testimg4, R.drawable.testimg5};

    private Context ctx;
    private LayoutInflater layoutInflater;
    private String caller;

    CustomSwipeAdapter(Context ctx,String caller) {
        this.ctx = ctx; this.caller = caller;
        if (Objects.equals(caller, "slider")) {
            img_res = new int[img_res_slider.length];
            arraycopy(img_res_slider, 0, img_res, 0, img_res_slider.length);// img_res = img_res_slider;
        } else if (Objects.equals(caller, "tractor")) {
            img_res = new int[img_res_tractor.length];
            arraycopy(img_res_tractor, 0, img_res, 0, img_res_tractor.length); //img_res = img_res_tractor;
        }
        //else if (caller == "sow")
        else if (Objects.equals(caller, "sow")) {
            img_res = new int[img_res_tractor.length];
            arraycopy(img_res_tractor, 0, img_res, 0, img_res_tractor.length);//img_res=img_res_tractor;
        } else if (Objects.equals(caller, "tiller")) {
            img_res = new int[img_res_tractor.length];
            arraycopy(img_res_tractor, 0, img_res, 0, img_res_tractor.length);//img_res=img_res_tractor;
        }
    }

    @Override
    public int getCount() {
        return img_res.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        imageView.setImageResource(img_res[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);

    }
}
