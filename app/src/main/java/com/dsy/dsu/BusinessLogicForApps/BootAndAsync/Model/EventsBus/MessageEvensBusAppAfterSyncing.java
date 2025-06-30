package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.EventsBus;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEvensBusAppAfterSyncing {


    public Intent mess;


    public MessageEvensBusAppAfterSyncing(@NonNull Intent mess) {

        this.mess = mess;
    }
}



