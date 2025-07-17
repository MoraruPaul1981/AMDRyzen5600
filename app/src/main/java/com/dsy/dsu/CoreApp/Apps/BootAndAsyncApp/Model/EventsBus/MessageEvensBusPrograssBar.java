package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEvensBusPrograssBar {

    public Intent mess;


    public MessageEvensBusPrograssBar(@NonNull  Intent mess) {

        this.mess = mess;
    }
}
