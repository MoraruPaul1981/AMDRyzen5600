package com.dsy.dsu.BusinessLogicForApps.EventBus;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.getHiltPortJbossInterface;

import java.util.LinkedHashMap;

import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.EntryPoints;

public class EventBuss   {

Activity  activity;

    Context context;


    SSLSocketFactory getsslSocketFactory2;

      LinkedHashMap<Integer,String> getHiltPortJboss;


    public   EventBuss(@NonNull Activity activity, @NonNull Context context, @NonNull SSLSocketFactory getsslSocketFactory2) {
        this.activity = activity;
        this.context = context;
        this.getsslSocketFactory2 = getsslSocketFactory2;
        getHiltPortJboss=   EntryPoints.get(context, getHiltPortJbossInterface.class).getHiltPortJboss();
    }




    





}
