package com.dsy.dsu.TabelsApp.Templates.model;

import android.content.Context;

    class AdinngATemplates implements  AdinngATemplatesInterface {
   private Context context;


    public AdinngATemplates(Context context) {
        this.context = context;
    }

    @Override
    public Long launchAdinng() {
        return null;
    }


    //TODO END CLASS
}










class AdinngInsideTemplate implements  AdinngATemplatesInterface {
    private Context context;


    public AdinngInsideTemplate(Context context) {
        this.context = context;
    }

    @Override
    public Long launchAdinng() {
        return null;
    }


    //TODO END CLASS
}







    interface   AdinngATemplatesInterface{

    Long launchAdinng();

}


