package com.dsy.dsu.CommitPricesApp.Model.EvenBusPrices;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEventBusItemCount extends Intent {

    public Intent mess;


    public MessageEventBusItemCount(@NonNull Intent mess) {

        this.mess = mess;
    }
}
