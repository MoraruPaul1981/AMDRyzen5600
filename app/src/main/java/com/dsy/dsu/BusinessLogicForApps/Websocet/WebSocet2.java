/*
package com.dsy.dsu.Websocet;

import android.content.Context;
import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.URI;
import java.nio.ByteBuffer;

public class WebSocet2 {
Context context;
    private WebSocketClient webSocketClient;

    public WebSocet2(Context context) {
        this.context = context;
    }

   public void connectToWebSocket() {

        Log.i("websocket", "connectToWebSocket() called.");
        URI uri;
        try {
          //  uri = new URI("ws://10.21.20.24:8080/websocket");///http://192.168.254.40:8080/jboss-1.0-SNAPSHOT/
            uri = new URI("ws://192.168.254.40:8080/jboss-1.0-SNAPSHOT/sousavtodorwebsocket");///http://192.168.254.40:8080/jboss-1.0-SNAPSHOT/
            Log.i("websocket", "connected to server.");
        } catch(Exception e){
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("websocket", "connected to server.");
                webSocketClient.send("connect:::TESTKEY");
                webSocketClient.send("alert:::HI");
            }

            @Override
            public void onMessage(String message) {
                Log.i("websocket", message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("websocket", "closed.");
            }

            @Override
            public void onError(Exception ex) {
                Log.i("websocket" , "error : " + ex.getMessage());
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                super.onMessage(bytes);
            }
        };
        webSocketClient.connect();
       //webSocketClient.onMessage("ggggg");


       class WebSer extends  WebSocketServer{

           @Override
           public void onOpen(WebSocket conn, ClientHandshake handshake) {

           }

           @Override
           public void onClose(WebSocket conn, int code, String reason, boolean remote) {

           }

           @Override
           public void onMessage(WebSocket conn, String message) {

           }

           @Override
           public void onError(WebSocket conn, Exception ex) {

           }

           @Override
           public void onStart() {

           }
       }
       WebSer webSer=new WebSer();
       webSer.onStart();
       Log.i("websocket", "closed."+  webSer.getAddress());
       Log.i("websocket", "closed."+  webSer.getAddress());



    }

}
*/
