package com.sous.backasync.businesslogic.errors.interfaces;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public interface RecordNewErrorsBackInterface {
    // TODO: 30.01.2025

    String fileName = "Sous-Avtodor-ERROR.txt";

    String patchFileName="SousAvtoFile";

    void recordnewerrorBack(@NonNull String ТекстОшибки,
                        @NonNull String КлассГнерацииОшибки,
                        @NonNull String МетодаОшибки,
                        @NonNull Integer ЛинияОшибки);


    void getWriteNewErrorNotePadBack(@NonNull String ТекстОшибки, @NonNull String КлассГнерацииОшибки, @NonNull String МетодаОшибки, @NonNull Integer ЛинияОшибки);

    Integer getWriteNewErrorBack(@NonNull String ТекстОшибки,
                             @NonNull String КлассГнерацииОшибки,
                             @NonNull String МетодаОшибки,
                             @NonNull Integer ЛинияОшибки );

    void writeDownAnewErrorFileBack(@NonNull ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи);

    void writeDownAnewErrorNotePadBack(@NonNull  ArrayList<String> linkedBlockingQueueВскеОшибкиДляЗаписи);
}
