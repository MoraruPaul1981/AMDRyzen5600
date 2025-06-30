package com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;

public interface HiltJbossBinessLogicIntarface {


  LinkedHashMap<Integer,String> selectingLinkJbossAdress(@NotNull SharedPreferences preferencesJboss, @NotNull Context context,@NotNull    String   getModeTrasport,@NotNull  Integer  getPost);

}
