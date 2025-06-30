/*
package com.dsy.dsu.Websocet;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class WebSer {


    public void staringwebservet(){



    class WebSer2 extends WebSocketServer {

        public WebSer2(InetSocketAddress address) {
            super(address);
        }

        @Override
        public void onOpen(WebSocket conn, ClientHandshake handshake) {
            Log.i("websocket", "closed." );
        }

        @Override
        public void onClose(WebSocket conn, int code, String reason, boolean remote) {
            Log.i("websocket", "closed." );
        }

        @Override
        public void onMessage(WebSocket conn, String message) {
            Log.i("websocket", "closed." );
        }

        @Override
        public void onError(WebSocket conn, Exception ex) {
            Log.i("websocket", "closed." );
        }

        @Override
        public void onStart() {
            Log.i("websocket", "closed." );
        }
    }


        String ipAddress = "192.168.254.40";
        InetSocketAddress inetSockAddress = new InetSocketAddress(ipAddress, 38301);
        WebSer2 webSer2=new WebSer2(inetSockAddress);
    webSer2.run();

    Log.i("websocket", "closed."+  webSer2.getAddress());
    Log.i("websocket", "closed."+  webSer2.getAddress());

}





}
*/
