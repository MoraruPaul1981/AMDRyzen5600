package com.dsy.dsu.BusinessLogicForApps.CreateFolderBinatySave;

import android.content.Context;
import android.util.Log;

public class ClassCreateFolderCommitPays1C extends ClassCreateFolders{


    public ClassCreateFolderCommitPays1C(Context context) {
        super(context);
    }

    @Override
    public void МетодCreateFoldersBinaty() {
            patchFileName="SousAvtoFile/AppCommitPays1C/Photos";
        // TODO: 25.03.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        super.МетодCreateFoldersBinaty();
    }
}
