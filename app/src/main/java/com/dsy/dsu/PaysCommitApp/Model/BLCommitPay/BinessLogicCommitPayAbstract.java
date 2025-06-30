package com.dsy.dsu.PaysCommitApp.Model.BLCommitPay;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

public abstract class BinessLogicCommitPayAbstract {
   @BinderThread
   abstract void runningJsonNodeCommitPay(@NonNull Intent intent, @NonNull Context context);

   @BinderThread
   abstract void callbackJsonNodeCommitPay(  @NonNull Context context, @NotNull JsonNode jsonNode);
}
