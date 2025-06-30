package com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.Businesslogic;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.InputStream;

import javax.net.ssl.SSLSocketFactory;

public abstract class BissennsLogica {

    InputStream caFileInputStream=null;

    abstract SSLSocketFactory getSocketFactorySSL(@NonNull Context context);
}
