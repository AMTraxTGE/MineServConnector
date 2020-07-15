package com.amtraxtge.msc.ws;

import com.amtraxtge.msc.utilities.configuration.MineConfig;
import com.amtraxtge.msc.ws.responses.IWebSocketResponse;
import com.amtraxtge.msc.ws.responses.SocketClosedResponse;
import org.java_websocket.WebSocket;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.UUID;

public class ConnectionManager {
    private HashMap<UUID, WebSocket> connections;

    private ConnectionManager() {
        this.connections = new HashMap<UUID, WebSocket>();
    }

    public static ConnectionManager create() {
        return new ConnectionManager();
    }

    public UUID addConnection(WebSocket socket) {
        UUID connectionId = UUID.randomUUID();
        connections.put(connectionId, socket);
        return connectionId;
    }

    public void removeConnection(UUID connectionId) {
        WebSocket socket = connections.get(connectionId);

        SocketClosedResponse closedResponse = SocketClosedResponse.create(MineConfig.get().serverId());
        closedResponse.isShutdown(false);

        socket.send(closedResponse.toJSON());
        socket.close();

        connections.remove(connectionId);
    }

    public void removeConnection(WebSocket socket) {
        UUID connectionId = getConnectionKey(socket);
        if (connectionId != null) removeConnection(connectionId);
    }

    public UUID getConnectionKey(WebSocket socket) {
        for (Entry<UUID, WebSocket> connection : connections.entrySet()) {
            if (connection.getValue().equals(socket)) {
                return connection.getKey();
            }
        }
        return null;
    }

    public HashMap<UUID, WebSocket> getConnections() {
        return connections;
    }

    public void closeAll(Boolean shutdown) {
        SocketClosedResponse closedResponse = SocketClosedResponse.create(MineConfig.get().serverId());
        closedResponse.isShutdown(shutdown);

        connections.forEach((key, socket) -> {
            socket.send(closedResponse.toJSON());
            socket.close();
        });
        connections.clear();
    }

    public void messageAll(String msg) {
        connections.forEach((key, socket) -> {
            socket.send(msg);
        });
    }
}
