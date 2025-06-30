package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ByteGenetarorJsonNode {



   public JsonNode genetarorJsonnode(@NotNull Context context,
                                     @NotNull ObjectMapper objectMapper,
                                     @NotNull  byte[] getbyteComminhgPrices){
       JsonNode jsonNode1сСогласованиеЦен = null;
       try{
        Single<JsonNode> jsonNodeSingle=  Single.fromCallable(()->{
            // TODO: 28.12.2023  получаем данные о т 1с
                    final JsonParser jsonParser= objectMapper.createParser(getbyteComminhgPrices);
                JsonNode jsonNode1с   = jsonParser.readValueAsTree();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"getbyteComminhgPrices  "
                    + getbyteComminhgPrices+ " jsonNode1с "+ jsonNode1с);
               return jsonNode1с;
           }).subscribeOn(Schedulers.single())
                .doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                throwable.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        });
           // TODO: 19.03.2024
           jsonNode1сСогласованиеЦен = jsonNodeSingle.blockingGet();

       Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
               " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
               " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"getbyteComminhgPrices  "
               + getbyteComminhgPrices+ " jsonNode1сСогласованиеЦен "+ jsonNode1сСогласованиеЦен);
   } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    return jsonNode1сСогласованиеЦен;
    }
}
