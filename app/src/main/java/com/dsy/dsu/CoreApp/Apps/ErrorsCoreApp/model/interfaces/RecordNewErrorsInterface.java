package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.interfaces;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public interface RecordNewErrorsInterface {
    // TODO: 30.01.2025

      String fileName = "Sous-Avtodor-ERROR.txt";

        String patchFileName="SousAvtoFile";


     void recordnewerror(@NonNull String ТекстОшибки,
                   @NonNull String КлассГнерацииОшибки,
                   @NonNull String МетодаОшибки,
                   @NonNull Integer ЛинияОшибки);


    void getWriteNewErrorNotePad(@NonNull String ТекстОшибки, @NonNull String КлассГнерацииОшибки, @NonNull String МетодаОшибки, @NonNull Integer ЛинияОшибки);

    Integer getWriteNewError(@NonNull String ТекстОшибки,
                             @NonNull String КлассГнерацииОшибки,
                             @NonNull String МетодаОшибки,
                             @NonNull Integer ЛинияОшибки );

    void writeDownAnewErrorFile(@NonNull ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи);


}
