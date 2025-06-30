package com.dsy.dsu.BusinessLogicForApps.Dates;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SubClassYEARONLY extends GetMainDateForApp {
    public SubClassYEARONLY(Context context) {
        super(context);
    }
    @Override
    public String getMainDateForApp() {
        super.getMainDateForApp();
       // new DateTime().minusMonths(1).toDate();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date Дата = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY", new Locale("ru"));
        Log.d(this.getClass().getName(), "dateFormat "  +dateFormat);
        return  dateFormat.format(Дата);
    }

}
