package com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces;


import java.util.LinkedHashMap;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@EntryPoint
@InstallIn(SingletonComponent.class)
public interface getHiltPortJbossInterface {
    @QualifierPortJboss
    LinkedHashMap<Integer,String> getHiltPortJboss();
}
