package com.sous.backasync.launch.interfaces;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.annotation.NonNull;

public interface ModuleInsertingBackAsyncInterface {

    // TODO: 28.01.2025

    Integer getModuleInsert(@NonNull Bundle bundleModuleBack);
    // TODO: 28.01.2025
    Integer getModuleInsert(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBackAsync);
    Integer getModuleSystemInsert(@NonNull String Таблица, @NonNull ContentValues contentValuesModuleBackAsync);
}
