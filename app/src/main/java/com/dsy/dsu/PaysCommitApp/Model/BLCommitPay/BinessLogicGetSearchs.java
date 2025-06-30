package com.dsy.dsu.PaysCommitApp.Model.BLCommitPay;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;

public class BinessLogicGetSearchs extends BinessLogicCommitPayAbstract {

    Context context;
 private Boolean ОдинРАзСработал = false;

    public BinessLogicGetSearchs(Context context) {
        this.context = context;
    }


    @Override
    public void runningJsonNodeCommitPay(@NonNull Intent intent, @NonNull Context context) {

        Observable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {

                // TODO: 25.11.2023 end TEST
                final JsonNode[] jsonNode1сСогласованияSearch = new JsonNode[1];
                // TODO: 23.11.2023
                Bundle getbungle = intent.getExtras();
                JsonNode jsonNode1сСогласованияSearchROWS = (JsonNode) getbungle.getSerializable("JsonNode");
                jsonNode1сСогласованияSearch[0] = jsonNode1сСогласованияSearchROWS.deepCopy();
                String query = getbungle.getString("query");

                // TODO: 23.11.2023  Первый ЦИКл по строчкам
                Iterator<JsonNode> iteratorRowSearch = jsonNode1сСогласованияSearch[0].iterator();
                while (iteratorRowSearch.hasNext()) {
                    JsonNode jsonNodeRowFutureDelete = iteratorRowSearch.next();

                    // TODO: 24.11.2023 ROW ONLY
                    ОдинРАзСработал = false;

                    jsonNodeRowFutureDelete.fields()
                            .forEachRemaining(new Consumer<Map.Entry<String, JsonNode>>() {
                                @Override
                                public void accept(Map.Entry<String, JsonNode> stringJsonNodeEntry) {
                                    // TODO: 23.11.2023 обьект
                                    //String valueFind= stringJsonNodeEntry.getValue().asText().trim();
                                    JsonNode valueFind = stringJsonNodeEntry.getValue().deepCopy();

                                    if (valueFind.isArray()) {
                                        valueFind.forEach(new Consumer<JsonNode>() {
                                            @Override
                                            public void accept(JsonNode jsonNodeArray) {
                                                // TODO: 24.11.2023 если совпадает то запускаем данные обратно в Фрагмент
                                                if (jsonNodeArray.hasNonNull("nomen")) {
                                                    JsonNode jsonNodeArrayFind = jsonNodeArray.get("nomen");
                                                    // TODO: 24.11.2023 операция вставки
                                                    if (!jsonNodeArrayFind.isNull()) {
                                                        // TODO: 24.11.2023 attay
                                                        metodBoolenEmptyOrISEmpty(jsonNodeArrayFind, query);

                                                        Log.d(this.getClass().getName(), "\n" + " class "
                                                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                                    }
                                                }

                                                Log.d(this.getClass().getName(), "\n" + " class "
                                                        + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                            }
                                        });

                                        // TODO: 24.11.2023 КОГДА НЕ МАССИВ
                                    } else {
                                        // TODO: 24.11.2023 если совпадает то запускаем данные обратно в Фрагмент
                                        if (!valueFind.isNull()) {
                                            metodBoolenEmptyOrISEmpty(valueFind, query);
                                        }
                                        // TODO: 24.11.2023
                                        Log.d(this.getClass().getName(), "\n" + " class "
                                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                                " valueFind " + valueFind);

                                    }


                                }


                            });

                    // TODO: 26.12.2022  конец основгого кода
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            "  ОдинРАзСработал[0] " + ОдинРАзСработал);

                    if (ОдинРАзСработал == false) {
                        iteratorRowSearch.remove();
                        ObjectNode objectNodeRow = jsonNodeRowFutureDelete.deepCopy();
                        objectNodeRow.removeAll();
                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(this.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " jsonNode1сСогласованияSearch " + jsonNode1сСогласованияSearch[0].size());
                    } else {
                        // TODO: 26.12.2022  конец основгого кода
                        Log.d(this.getClass().getName(), "\n" + " class "
                                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                " jsonNode1сСогласованияSearch " + jsonNode1сСогласованияSearch[0].size());
                    }


                    // TODO: 26.12.2022  конец основгого кода
                    Log.d(this.getClass().getName(), "\n" + " class "
                            + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                            " jsonNode1сСогласованияSearch " + jsonNode1сСогласованияSearch[0].size());
                }


                // TODO: 24.11.2023  END Iterator


                // TODO: 24.11.2023  метод Ответ Клиенту
                callbackJsonNodeCommitPay(context, jsonNode1сСогласованияSearch[0]);
            }


        })

                .onErrorReturn(err->{
                    new RecordNewErros(context).recordnewerror(err.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                    return Log.e(context.getClass().getName(),
                            "Ошибка " + err + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber() +" err " +err.getMessage());

                })
                .blockingSubscribe();


        // TODO: 26.12.2022  конец основгого кода
        Log.d(this.getClass().getName(), "\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    }



    private void metodBoolenEmptyOrISEmpty(@NonNull JsonNode objectNode,@NonNull String query) {

        String valueFind=    objectNode.asText();

        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(valueFind);
        boolean matches = matcher.find();


        if(matches){
            ОдинРАзСработал =true;
            // TODO: 26.12.2022  конец основгого кода
            Log.d(this.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " valueFind " + valueFind);
        }else {
            // TODO: 26.12.2022  конец основгого кода
            Log.d(this.getClass().getName(), "\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                    " valueFind " + valueFind);
        }


    }

    // TODO: 23.11.2023 метод обратоно возваряем Данные


    @Override
    void callbackJsonNodeCommitPay(  @NonNull Context context, @NotNull JsonNode jsonNode1сСогласованияSearchCallBack) {

        Intent intent1Send=new Intent("custom-event-name");
        Bundle bundleCallBack=  new Bundle();

        if (jsonNode1сСогласованияSearchCallBack.size()>0) {
            bundleCallBack.putSerializable("callbacksearchjsonnode", (Serializable) jsonNode1сСогласованияSearchCallBack);
            bundleCallBack.putInt("message",   jsonNode1сСогласованияSearchCallBack.size());
        }else {
            bundleCallBack.putInt("message", jsonNode1сСогласованияSearchCallBack.size());
        }
        intent1Send.putExtras(bundleCallBack);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent1Send);


        // TODO: 26.12.2022  конец основгого кода
        Log.d(context.getClass().getName(), "\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                " jsonNode1сСогласованияSearchCallBack " +jsonNode1сСогласованияSearchCallBack);


        // TODO: 26.12.2022  конец основгого кода
        Log.d(context.getClass().getName(), "\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

    }


}
