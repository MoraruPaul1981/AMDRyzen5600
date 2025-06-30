package com.dsy.dsu.CommitPricesApp.Model.SendDataTo1C;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

public class StartSendJsonToCOmmintPrices implements  InStartingJsonSend {

    Context context;


    ObjectMapper objectMapper;

    Integer PublicId;

    public StartSendJsonToCOmmintPrices(Context context, ObjectMapper objectMapper, Integer publicId) {
        this.context = context;
        this.objectMapper = objectMapper;
        PublicId = publicId;
    }


    @Override
    public   byte[] startSendJson1c(@NonNull Bundle bundle) {
        byte[] ByteFor1CCommintPrices=null;

try{
    if (bundle!=null){

         LinkedHashMap<String, String> linkedHashMapPostНа1с=new LinkedHashMap<>();
         String UUID=   bundle.getString("UUID").trim();
          linkedHashMapPostНа1с.putIfAbsent("user",PublicId.toString());
          linkedHashMapPostНа1с.putIfAbsent("uuid",UUID);


        // TODO: 10.01.2024  генерируем JSON
        CommintPricesSendJsonTo1C generatorJsonForPostComminhgPrices=new CommintPricesSendJsonTo1C();

      ByteFor1CCommintPrices=   generatorJsonForPostComminhgPrices.GeneratorJsonForPostComminhgPrices(context,linkedHashMapPostНа1с,objectMapper);



    }
        Log.d(this.getClass().getName(),"\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                + " ByteFor1CCommintPrices " +ByteFor1CCommintPrices);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return ByteFor1CCommintPrices;
    }
}
