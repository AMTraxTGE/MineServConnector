package com.amtraxtge.msc.ws;

import com.amtraxtge.msc.utilities.logging.MineLogger;
import com.amtraxtge.msc.ws.commands.IWebSocketCommand;
import com.amtraxtge.msc.ws.responses.IWebSocketResponse;
import com.amtraxtge.msc.ws.responses.UnknownCommandResponse;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class MineSocketServer extends WebSocketServer {
    private HashMap<String, IWebSocketCommand> commands = CommandFactory.load();
    private ConnectionManager manager;

    private MineSocketServer(InetSocketAddress address, ConnectionManager manager) {
        super(address);
        this.manager = manager;

        // TODO: ADD SCHEDULER
    }

    public static MineSocketServer create(InetSocketAddress address, ConnectionManager manager) {
        return new MineSocketServer(address, manager);
    }

    @Override
    public void onOpen(WebSocket socket, ClientHandshake handshake) {
        MineLogger.info("New Socket Connection Established");

        IWebSocketCommand cmd = commands.get("STATUS");
        cmd.execute(this, socket, "");

        manager.addConnection(socket);
    }

    @Override
    public void onClose(WebSocket socket, int code, String reason, boolean remote) {
        MineLogger.info("Closing Socket Connection");
        manager.removeConnection(socket);
    }

    @Override
    public void onMessage(WebSocket socket, String message) {
        String command = message.split(" ", 2)[0];
        String params = "";

        if (message.contains(" ")) params = message.split(" ", 2)[1];

        IWebSocketCommand cmd = commands.get(command);

        if (cmd == null) {
            sendToSocket(socket, UnknownCommandResponse.create(message));
        } else {
            cmd.execute(this, socket, params);
        }
    }

    @Override
    public void onError(WebSocket socket, Exception e) {
        MineLogger.warning("Socket Connection Error", e);
    }

    @Override
    public void onStart() {
        MineLogger.info("WebSocket Server Started on " + getAddress());
    }

    public void sendToSocket(WebSocket socket, IWebSocketResponse response) {
        try {
            socket.send(response.toJSON());
        } catch (WebsocketNotConnectedException e) {
            MineLogger.warning("Not Connected to Socket", e);
        }
    }
}
