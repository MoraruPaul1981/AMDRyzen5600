package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_viewpager2;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;

import android.content.Context;
import android.util.Log;

import androidx.viewpager2.widget.ViewPager2;

import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.dashbordfaceapp.DashboardFaceApp;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

public class BunesslogicViewPager2 {
   private ViewPager2 pagerdachbord;
    private Context getcontext;
    private  DemoCollectionAdapter demoCollectionAdapter;
    private DashboardFaceApp dashboardFaceApp;
    public BunesslogicViewPager2(ViewPager2 pagerdachbord, Context getcontext,
                                 DashboardFaceApp dashboardFaceApp) {
        this.pagerdachbord = pagerdachbord;
        this.getcontext = getcontext;
        this.dashboardFaceApp = dashboardFaceApp;
    }

   public void getBunesslogicViewPager2(){
        try{
            demoCollectionAdapter = new DemoCollectionAdapter(dashboardFaceApp);
            dashboardFaceApp.pagerdachbord.setAdapter(demoCollectionAdapter);
            dashboardFaceApp.pagerdachbord.setScrollContainer(true);
            dashboardFaceApp.pagerdachbord.animate().start();
            dashboardFaceApp.pagerdachbord.setPageTransformer(new ZoomOutPageTransformerDashBoard());
            dashboardFaceApp.pagerdachbord.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);


                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                    if(state==SCROLL_STATE_SETTLING){
                    /*    dashboardFaceApp.pagerdachbord.getHandler().postDelayed(()->{
                            dashboardFaceApp.pagerdachbord.setCurrentItem( 0,true);
                        },5000);*/
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            });
        // TODO: 21.06.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getcontext).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


}
