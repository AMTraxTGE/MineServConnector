package com.amtraxtge.msc.ws.commands;

import com.amtraxtge.msc.minecraft.info.ServerPlayers;
import com.amtraxtge.msc.utilities.configuration.MineConfig;
import com.amtraxtge.msc.ws.MineSocketServer;
import com.amtraxtge.msc.ws.responses.ServerStatusResponse;
import org.java_websocket.WebSocket;

public class ServerStatusCommand implements IWebSocketCommand {
    @Override
    public void execute(MineSocketServer server, WebSocket socket, String params) {
        server.sendToSocket(socket, ServerStatusResponse.create(true, ServerPlayers.names(), MineConfig.get().serverId()));
    }
}
