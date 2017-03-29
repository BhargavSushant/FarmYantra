package com.droid.atl.farmyantra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
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
    Timer timerSlider,timerTractor,timerSow,timerTiller;
    int page = 1,sliderPage=0,tractorCurrentPage=0,sowCurrentPage=0,tillerCurrentPage=0;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_products, container, false);

        sliderPager = (ViewPager) myView.findViewById(R.id.view_pager);
        tractorPager = (ViewPager) myView.findViewById(R.id.tractor_banner);
        sowPager = (ViewPager) myView.findViewById(R.id.sow_banner);
        tillerPager = (ViewPager) myView.findViewById(R.id.tiller_banner);


        slider_adapter = new CustomSwipeAdapter(getActivity(),"slider");
        tractorPagerAdapter = new CustomSwipeAdapter(getActivity(),"tractor");
        sowPagerAdapter = new CustomSwipeAdapter(getActivity(),"sow");
        tillerPagerAdapter = new CustomSwipeAdapter(getActivity(), "tiller");

        sliderPager.setAdapter(slider_adapter);
        pageSwitcher(1,sliderPager,3,"slider");

        tractorPager.setAdapter(tractorPagerAdapter);
        pageSwitcher(1,tractorPager,13,"tractor");

        tillerPager.setAdapter(tractorPagerAdapter);
        pageSwitcher(1,tillerPager,7,"sow");

        sowPager.setAdapter(tractorPagerAdapter);
        pageSwitcher(1,sowPager,7,"tiller");

        return myView;
    }

    public ProductsFragment() {
    }

    public int pageSwitcher(int seconds,ViewPager viewPagerObj,int totalPages,String caller) {
        switch(caller){
            case "slider":
                timerSlider = new Timer(); // At this line a new Thread will be created
                timerSlider.scheduleAtFixedRate(new RemindTask(viewPagerObj,totalPages,"slider"), 0, seconds * 1000);
                break;

            case "tractor":
                timerTractor = new Timer();
                timerTractor.scheduleAtFixedRate(new RemindTask(viewPagerObj,totalPages,"tractor"), 0, seconds * 1000);
                break;

            case "sow":
                timerSow = new Timer();
                timerSow.scheduleAtFixedRate(new RemindTask(viewPagerObj,totalPages,"sow"), 0, seconds * 1000);
                break;

            case "tiller":
                timerTiller = new Timer();
                timerTiller.scheduleAtFixedRate(new RemindTask(viewPagerObj,totalPages,"tiller"), 0, seconds * 1000);

                break;

            default:
                break;
        }

        return 1;
    }


    //  inner class
    private class RemindTask extends TimerTask {
            ViewPager vPager;
            int MaxPages;
        String caller;
        RemindTask(ViewPager viewPagerObj, int MaxPages,String caller){
            vPager=viewPagerObj;
            this.MaxPages=MaxPages;
            this.caller = caller;
        }

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (sliderPage > MaxPages)
                                sliderPage=0;
                    else  vPager.setCurrentItem(++sliderPage);

                   if(tractorCurrentPage>MaxPages)
                                tractorCurrentPage=0;
                    else
                       vPager.setCurrentItem(++tractorCurrentPage);

                    if(sowCurrentPage > MaxPages)
                                sowCurrentPage=0;
                    else
                        vPager.setCurrentItem(sowCurrentPage++);

                    if (tillerCurrentPage >MaxPages)
                                tillerCurrentPage=0;
                    else
                        vPager.setCurrentItem(tillerCurrentPage++);

/*
                    switch(caller){
                            case "slider":
                                vPager.setCurrentItem(sliderPage++);
                                break;

                            case "tractor":
                                vPager.setCurrentItem(tractorCurrentPage++);
                                break;

                            case "sow":
                                vPager.setCurrentItem(sowCurrentPage++);
                                break;

                            case "tiller":
                                vPager.setCurrentItem(tillerCurrentPage++);
                                break;

                            default:
                                break;
                            //currentPage = 0;
                        }//end switch
                    */
                }
            });

        } // end run()

    }// end RemindTask class

}