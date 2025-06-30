package com.dsy.dsu.BusinessLogicForApps.CreateFolderBinatySave;


import android.content.Context;
import android.util.Log;

public class ClassCreateFolderBinatyMatrilal  extends  ClassCreateFolders{


    public ClassCreateFolderBinatyMatrilal(Context context) {
        super(context);
    }

    @Override
    public void МетодCreateFoldersBinaty() {
        patchFileName="SousAvtoFile/AppMaterial/Photos";
        // TODO: 25.03.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        super.МетодCreateFoldersBinaty();
    }
}





