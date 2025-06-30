package com.dsy.dsu.BusinessLogicForApps.BootAndAsync.Model.EventsBus;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEvensBusNetworkStatuses {

    public Intent mess;


    public MessageEvensBusNetworkStatuses(@NonNull Intent mess) {
        this.mess = mess;
    }
}
