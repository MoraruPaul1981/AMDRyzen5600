package com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.Businesslogic;

import android.content.Context;
import android.util.Log;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class TLSSocketFactoryTLS extends SSLSocketFactory {



    public SSLSocketFactory TLSSocketFactoryTLS(@NotNull Context context) throws KeyManagementException, NoSuchAlgorithmException {
        SSLSocketFactory getinternalSSLSocketFactory = null;
        try {

            KeyStore ksTrust = KeyStore.getInstance("BKS");
            InputStream instream = context.getResources().openRawResource(R.raw.androidserver);
            ksTrust.load(instream, "password".toCharArray());

            // TrustManager decides which certificate authorities to use.
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksTrust);
           SSLContext sslContext = SSLContext.getInstance("TLSv1.3");
            sslContext.init(null, tmf.getTrustManagers(), null);
            // Create an ssl socket factory with our all-trusting manager
            getinternalSSLSocketFactory = sslContext.getSocketFactory();
            // TODO: 25.12.2023  clear
            instream.close();
            Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                    Thread.currentThread().getStackTrace()[2].getMethodName()+
                    " время " +new Date().toLocaleString()+ " sslContext " +sslContext +  "   certificate.getPublicKey() "
                    +   ksTrust.getKey("locahost", "password".toCharArray()));

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
       return getinternalSSLSocketFactory ;
    }


    /**
     * Returns the list of cipher suites which are enabled by default.
     * Unless a different list is enabled, handshaking on an SSL connection
     * will use one of these cipher suites.  The minimum quality of service
     * for these defaults requires confidentiality protection and server
     * authentication (that is, no anonymous cipher suites).
     *
     * @return array of the cipher suites enabled by default
     * @see #getSupportedCipherSuites()
     */
    @Override
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    /**
     * Returns the names of the cipher suites which could be enabled for use
     * on an SSL connection.  Normally, only a subset of these will actually
     * be enabled by default, since this list may include cipher suites which
     * do not meet quality of service requirements for those defaults.  Such
     * cipher suites are useful in specialized applications.
     *
     * <p class="caution">Applications should not blindly enable all supported
     * cipher suites.  The supported cipher suites can include signaling cipher suite
     * values that can cause connection problems if enabled inappropriately.
     *
     * <p>The proper way to use this method is to either check if a specific cipher
     * suite is supported via {@code Arrays.asList(getSupportedCipherSuites()).contains(...)}
     * or to filter a desired list of cipher suites to only the supported ones via
     * {@code desiredSuiteSet.retainAll(Arrays.asList(getSupportedCipherSuites()))}.
     *
     * @return an array of cipher suite names
     * @see #getDefaultCipherSuites()
     */
    @Override
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }

    /**
     * Returns a socket layered over an existing socket connected to the named
     * host, at the given port.  This constructor can be used when tunneling SSL
     * through a proxy or when negotiating the use of SSL over an existing
     * socket. The host and port refer to the logical peer destination.
     * This socket is configured using the socket options established for
     * this factory.
     *
     * @param s         the existing socket
     * @param host      the server host
     * @param port      the server port
     * @param autoClose close the underlying socket when this socket is closed
     * @return a socket connected to the specified host and port
     * @throws IOException          if an I/O error occurs when creating the socket
     * @throws NullPointerException if the parameter s is null
     */
    @Override
    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return null;
    }

    /**
     * Creates a socket and connects it to the specified remote host
     * at the specified remote port.  This socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param host the server host name with which to connect, or
     *             <code>null</code> for the loopback address.
     * @param port the server port
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws UnknownHostException     if the host is not known
     * @throws IllegalArgumentException if the port parameter is outside the
     *                                  specified range of valid port values, which is between 0 and
     *                                  65535, inclusive.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(String, int)
     */
    @Override
    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return null;
    }

    /**
     * Creates a socket and connects it to the specified remote host
     * on the specified remote port.
     * The socket will also be bound to the local address and port supplied.
     * This socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param host      the server host name with which to connect, or
     *                  <code>null</code> for the loopback address.
     * @param port      the server port
     * @param localHost the local address the socket is bound to
     * @param localPort the local port the socket is bound to
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws UnknownHostException     if the host is not known
     * @throws IllegalArgumentException if the port parameter or localPort
     *                                  parameter is outside the specified range of valid port values,
     *                                  which is between 0 and 65535, inclusive.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(String, int, InetAddress, int)
     */
    @Override
    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
        return null;
    }

    /**
     * Creates a socket and connects it to the specified port number
     * at the specified address.  This socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param host the server host
     * @param port the server port
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws IllegalArgumentException if the port parameter is outside the
     *                                  specified range of valid port values, which is between 0 and
     *                                  65535, inclusive.
     * @throws NullPointerException     if <code>host</code> is null.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(InetAddress, int)
     */
    @Override
    public Socket createSocket(InetAddress host, int port) throws IOException {
        return null;
    }

    /**
     * Creates a socket and connect it to the specified remote address
     * on the specified remote port.  The socket will also be bound
     * to the local address and port suplied.  The socket is configured using
     * the socket options established for this factory.
     * <p>
     * If there is a security manager, its <code>checkConnect</code>
     * method is called with the host address and <code>port</code>
     * as its arguments. This could result in a SecurityException.
     *
     * @param address      the server network address
     * @param port         the server port
     * @param localAddress the client network address
     * @param localPort    the client port
     * @return the <code>Socket</code>
     * @throws IOException              if an I/O error occurs when creating the socket
     * @throws SecurityException        if a security manager exists and its
     *                                  <code>checkConnect</code> method doesn't allow the operation.
     * @throws IllegalArgumentException if the port parameter or localPort
     *                                  parameter is outside the specified range of valid port values,
     *                                  which is between 0 and 65535, inclusive.
     * @throws NullPointerException     if <code>address</code> is null.
     * @see SecurityManager#checkConnect
     * @see Socket#Socket(InetAddress, int,
     * InetAddress, int)
     */
    @Override
    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return null;
    }
}