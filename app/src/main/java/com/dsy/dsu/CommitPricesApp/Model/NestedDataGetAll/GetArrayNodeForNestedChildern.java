package com.dsy.dsu.CommitPricesApp.Model.NestedDataGetAll;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Iterator;


// TODO: 09.01.2024  данные класс оплучаеем все дочерниуе едементы для ArrayNode от Согласование Цены
public class GetArrayNodeForNestedChildern implements  InForChilderArrayJSon  {
   private Context context;
    private JsonNode jsonNodeNested;
    private  int position;

    public GetArrayNodeForNestedChildern(Context context, JsonNode jsonNodeNested, int position) {
        this.context = context;
        this.jsonNodeNested = jsonNodeNested;
        this.position = position;
    }





    public JsonNode remoteRowJsonPrices( ) {
        try {

            Iterator<JsonNode> elementsForDelete =    jsonNodeNested.iterator();
            Integer sum = 0;
            while (elementsForDelete.hasNext()) {
              ArrayNode arrayNodeRemote= (ArrayNode) elementsForDelete.next();
                // TODO: 19.03.2024 remote
                arrayNodeRemote.remove(position);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " jsonNode1сСогласования.size() " + jsonNodeNested.size());


                // TODO: 19.03.2024 exit
                break;



/*
                if (sum == position) {
                    elementsForDelete.remove();
                    break;
                }
                sum++;*/

            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " jsonNode1сСогласования.size() " + jsonNodeNested.size());


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return jsonNodeNested;
    }
}
