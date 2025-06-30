package com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2;


import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@EntryPoint
@InstallIn(SingletonComponent.class)
public interface GetsslSocketFactory2Interface {

    @QualifiergetsslSocketFactory2
    SSLSocketFactory getsslSocketFactory2( );
}


