package com.dsy.dsu.PaysCommitApp.Model.EventBusPays;

import android.content.Intent;

import androidx.annotation.NonNull;

public class MessageEvensBusPays {

    public Intent mess;


    public MessageEvensBusPays( @NonNull Intent mess) {

        this.mess = mess;
    }

    public static class MessageEvensBusCommintPay {

        public Intent mess;


        public MessageEvensBusCommintPay( @NonNull Intent mess) {

            this.mess = mess;
        }

    }
}

