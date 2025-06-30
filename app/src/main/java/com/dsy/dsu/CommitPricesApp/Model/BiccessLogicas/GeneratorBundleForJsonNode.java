package com.dsy.dsu.CommitPricesApp.Model.BiccessLogicas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jetbrains.annotations.NotNull;

public class GeneratorBundleForJsonNode {

    public JsonNode generatorBunbleForJsonNode(@NotNull Bundle validadress, @NotNull Context context, @NotNull ObjectMapper objectMapper) {
        JsonNode jsonNode1сСогласованиеЦен=null;
        try{

            // TODO: 25.12.2023  пришел ответ в livedata от VieModel
            byte[] getbyteComminhgPrices=  validadress.getByteArray("getbyteComminhgPrices");

            // TODO: 26.12.2023  когда данные пришли от 1с согласования цен
            if (getbyteComminhgPrices!=null && getbyteComminhgPrices.length>0) {

                // TODO: 26.12.2023 пришли байты  из байт в обьект  json node

                jsonNode1сСогласованиеЦен = new ByteGenetarorJsonNode().genetarorJsonnode(context, objectMapper, getbyteComminhgPrices);
            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +"jsonNode1сСогласованиеЦен  "
                    + jsonNode1сСогласованиеЦен);

            // TODO: 30.12.2023
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
