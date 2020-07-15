package com.amtraxtge.msc.ws.commands;

import com.amtraxtge.msc.ws.MineSocketServer;
import org.java_websocket.WebSocket;

public interface IWebSocketCommand {
    void execute(MineSocketServer server, WebSocket socket, String params);
}
