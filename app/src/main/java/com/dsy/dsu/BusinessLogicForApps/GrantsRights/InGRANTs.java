package com.dsy.dsu.BusinessLogicForApps.GrantsRights;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

public interface InGRANTs {

    Integer getGrantRemote(@NotNull Context context ,@NonNull Integer getHiltPublicId);

    void setGrantRemote(@NotNull Context context,
                        @NonNull MaterialButton КнопкаЗаявкаНаТранспорт,
                        @NonNull MaterialButton КнопкаСогласование,
                        @NonNull  MaterialButton КнопкаСогласЦен ,
                        @NonNull   MaterialButton КнопкаПоступлениеМатериалов,
                        @NonNull   MaterialButton КнопкаТабель,
                        @NonNull Integer getGrantRemote);





}
