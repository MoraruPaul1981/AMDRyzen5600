package com.dsy.dsu.BusinessLogicForApps.Jakson;


import android.content.Context;
import android.util.Log;

import com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON.Organization;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class GeneratorJSONDeserializer extends JsonDeserializer<Organization> {
    Context context;
    public GeneratorJSONDeserializer(Context context) {

        this.context=context;
    }
    @Override
    public Organization deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        try{
            JsonNode node = p.readValueAsTree();
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return new Organization();
    }
}


