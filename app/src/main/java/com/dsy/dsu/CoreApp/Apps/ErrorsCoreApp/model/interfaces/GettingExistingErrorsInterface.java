package com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.interfaces;

import android.content.Context;

import androidx.annotation.NonNull;

import com.sous.backasync.launch.ModuleQuety;

public interface GettingExistingErrorsInterface {

    StringBuffer gettingExistingErrors(@NonNull Context context, ModuleQuety moduleQuety );
}
