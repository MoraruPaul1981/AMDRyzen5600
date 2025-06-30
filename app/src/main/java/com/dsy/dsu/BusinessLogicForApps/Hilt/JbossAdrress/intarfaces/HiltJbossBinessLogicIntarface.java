package com.dsy.dsu.BusinessLogicForApps.Hilt.JbossAdrress.intarfaces;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public interface HiltJbossBinessLogicIntarface {


public LinkedHashMap<Integer,String>    selectenableforSslrequests(@NotNull SharedPreferences preferencesJboss, @NotNull Context context);

}
