package com.dsy.dsu.CommitPricesApp.Model.SendDataTo1C;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.LinkedHashMap;

import io.reactivex.rxjava3.annotations.NonNull;

public abstract class SendDataParent {


    public  abstract byte[]  GeneratorJsonForPostComminhgPrices(@NotNull Context context,
                                                                @NotNull LinkedHashMap<String,String> linkedHashMapОтпавркаНа1с,
                                                           @NonNull ObjectMapper objectMapper);

    public  abstract  StringBuffer  SendJsonForPostComminhgPrices(@NotNull Context context,
                                                           @NotNull byte[]  ByteОтпавркаНа1с,
                                                           @NotNull Integer PublicId,
                                                           @NotNull String adress,
                                                           @NotNull String uuid);

    public  abstract String GetDeserializerJson1cComminhgPrices(@NotNull Context context,
                                                                @NotNull InputStream inputStream,
                                                                @NonNull ObjectMapper objectMapper);


    public  abstract void motingHideRecyreVIewNested(@NotNull Context context,
                                                                @NotNull InputStream inputStream);

}
