package com.dsy.dsu.BusinessLogicForApps.GetPingServers;

import org.jetbrains.annotations.NotNull;

import javax.net.ssl.SSLSocketFactory;

public interface GetPingServerNotActivity {






    Boolean pingServerJbossSuccessfulOrNot(@NotNull SSLSocketFactory getsslSocketFactory2) ;





    Long pingingJbossServer(@androidx.annotation.NonNull SSLSocketFactory getsslSocketFactory2,
                            Integer ИмяПорта, String ИмяСервера );



}
