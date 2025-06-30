package com.sous.backasync.launch.interfaces;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;

import androidx.annotation.NonNull;

public interface ModuleDeletingBackAsyncInterface {

    // TODO: 28.01.2025



    @SuppressLint("NewApi")
    Integer getModuleDelete(@NonNull String Таблица,    @NonNull String selection,  @NonNull String[] selectionArgs);

    @SuppressLint("NewApi")
    Integer getModuleDelete(@NonNull String Таблица,@NonNull Bundle bungleModuleBack);

    Integer getModuleSystemDelete(@NonNull String Таблица );


}
