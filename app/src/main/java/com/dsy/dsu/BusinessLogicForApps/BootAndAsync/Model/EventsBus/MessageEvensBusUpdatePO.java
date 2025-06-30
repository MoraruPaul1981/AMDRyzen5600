package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.EventsBus;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEvensBusUpdatePO {

    public Intent mess;


    public MessageEvensBusUpdatePO( @NonNull Intent mess) {

        this.mess = mess;
    }
}
