package com.sous.backasync.launch.interfaces;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface ModuleСalledBackAsyncInterface {

    // TODO: 28.01.2025
    @SuppressLint("NewApi")
    Bundle getModuleСalled(@NonNull String method, @Nullable String table, @Nullable Bundle extras);


}
