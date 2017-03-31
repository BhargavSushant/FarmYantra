package com.droid.atl.farmyantra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

public class ProductsFragment extends android.app.Fragment {

    ViewPager sliderPager, tractorPager, sowPager, tillerPager;
    CustomSwipeAdapter slider_adapter;
    CustomSwipeAdapter tractorPagerAdapter;
    CustomSwipeAdapter tillerPagerAdapter;
    CustomSwipeAdapter sowPagerAdapter;
    Timer timerSlider, timerTractor, timerSow, timerTiller;
    int page = 1, sliderPage = 0, tractorCurrentPage = 0, sowCurrentPage = 0, tillerCurrentPage = 0;

    View myView;

    public ProductsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_products, container, false);

        sliderPager = (ViewPager) myView.findViewById(R.id.view_pager);
        tractorPager = (ViewPager) myView.findViewById(R.id.tractor_banner);
        sowPager = (ViewPager) myView.findViewById(R.id.sow_banner);
        tillerPager = (ViewPager) myView.findViewById(R.id.tiller_banner);


        slider_adapter = new CustomSwipeAdapter(getActivity(), "slider");
        tractorPagerAdapter = new CustomSwipeAdapter(getActivity(), "tractor");
        sowPagerAdapter = new CustomSwipeAdapter(getActivity(), "sow");
        tillerPagerAdapter = new CustomSwipeAdapter(getActivity(), "tiller");

        sliderPager.setAdapter(slider_adapter);
        pageSwitcher(1, sliderPager, 4, "slider");

        tractorPager.setAdapter(tractorPagerAdapter);
        pageSwitcher(1, tractorPager, 6, "tractor");

        tillerPager.setAdapter(tractorPagerAdapter);
        pageSwitcher(1, tillerPager, 6, "sow");

        sowPager.setAdapter(tractorPagerAdapter);
        pageSwitcher(1, sowPager, 6, "tiller");

        return myView;
    }

    public int pageSwitcher(int seconds, ViewPager viewPagerObj, int totalPages, String caller) {
        switch (caller) {
            case "slider":
                timerSlider = new Timer(); // At this line a new Thread will be created
                timerSlider.scheduleAtFixedRate(new RemindTaskSlider(viewPagerObj, totalPages, "slider"), 0, seconds * 1000);
                break;

            case "tractor":
                timerTractor = new Timer();
                timerTractor.scheduleAtFixedRate(new RemindTaskTractor(viewPagerObj, totalPages, "tractor"), 0, seconds * 1000);
                break;

            case "sow":
                timerSow = new Timer();
                timerSow.scheduleAtFixedRate(new RemindTaskSow(viewPagerObj, totalPages, "sow"), 0, seconds * 1000);
                break;

            case "tiller":
                timerTiller = new Timer();
                timerTiller.scheduleAtFixedRate(new RemindTaskTiller(viewPagerObj, totalPages, "tiller"), 0, seconds * 1000);

                break;

            default:
                break;
        }

        return 1;
    }


    //  inner class
    private class RemindTaskSlider extends TimerTask {
        ViewPager vPager;
        int MaxPages;
        String caller;

        RemindTaskSlider(ViewPager viewPagerObj, int MaxPages, String caller) {
            vPager = viewPagerObj;
            this.MaxPages = MaxPages;
            this.caller = caller;
        }

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (sliderPage > 3)
                        sliderPage = 0;
                    else {
                        vPager.setCurrentItem(sliderPage++);
                        Log.d("SliderPager ======= ", Integer.toString(sliderPage));
                    }

                }
            });

        } // end run()

    }// end RemindTask class


    private class RemindTaskTractor extends TimerTask {
        ViewPager vPager;
        int MaxPages;
        String caller;

        RemindTaskTractor(ViewPager viewPagerObj, int MaxPages, String caller) {
            vPager = viewPagerObj;
            this.MaxPages = MaxPages;
            this.caller = caller;
        }

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    if (tractorCurrentPage > 6)
                        tractorCurrentPage = 0;
                    else
                        vPager.setCurrentItem(tractorCurrentPage++);

                }
            });

        } // end run()

    }// end RemindTask class

    private class RemindTaskSow extends TimerTask {
        ViewPager vPager;
        int MaxPages;
        String caller;

        RemindTaskSow(ViewPager viewPagerObj, int MaxPages, String caller) {
            vPager = viewPagerObj;
            this.MaxPages = MaxPages;
            this.caller = caller;
        }

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    if (sowCurrentPage > 6)
                        sowCurrentPage = 0;
                    else
                        vPager.setCurrentItem(sowCurrentPage++);

                }
            });

        } // end run()

    }// end RemindTask class

    private class RemindTaskTiller extends TimerTask {
        ViewPager vPager;
        int MaxPages;
        String caller;

        RemindTaskTiller(ViewPager viewPagerObj, int MaxPages, String caller) {
            vPager = viewPagerObj;
            this.MaxPages = MaxPages;
            this.caller = caller;
        }

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    if (tillerCurrentPage > 6)
                        tillerCurrentPage = 0;
                    else
                        vPager.setCurrentItem(tillerCurrentPage++);

                }
            });

        } // end run()

    }// end RemindTask class

}