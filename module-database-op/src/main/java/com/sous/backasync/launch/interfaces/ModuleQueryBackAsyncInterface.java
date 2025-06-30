package com.sous.backasync.launch.interfaces;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;

public interface ModuleQueryBackAsyncInterface {

    // TODO: 28.01.2025

    Cursor getModuleQueryForceLoad(@NonNull Bundle bundleModuleBack);
    // TODO: 28.01.2025
      Cursor getModuleQueryForceLoad(@NonNull String Таблица, @NonNull String СамЗапрос, @NonNull String []УсловияВыборки);
      Cursor getModuleQuery(@NonNull String Таблица, @NonNull String СамЗапрос, @NonNull String []УсловияВыборки);
}
