package com.dsy.dsu.BusinessLogicForApps;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;

public class Class_Start_Update_Soft {

    Context context;
    // TODO: 12.11.2021

    Activity activity;
    JbossContext jbossContextПО;

    public Class_Start_Update_Soft(Context context, Activity activity) {

        this.context = context;

        this.activity = activity;


        jbossContextПО = new JbossContext(context);

        Log.d(this.getClass().getName(), " context " + context + "\n" +
                "  activity " + activity);

    }















}
