package com.amtraxtge.msc.ws.responses;

import com.google.gson.JsonObject;

import java.util.UUID;

public class SocketClosedResponse implements IWebSocketResponse {
    private Integer statusCode = 1;
    private Boolean shutdown = false;
    private UUID serverId;

    private SocketClosedResponse(UUID serverId) {
        this.serverId = serverId;
    }

    public static SocketClosedResponse create(UUID serverId) {
        return new SocketClosedResponse( serverId);
    }

    public void isShutdown(Boolean status) {
        shutdown = status;
    }

    @Override
    public String toJSON() {
        JsonObject object = new JsonObject();
        object.addProperty("status", statusCode);
        object.addProperty("server", serverId.toString());
        object.addProperty("shutdown", shutdown);
        return object.toString();
    }
}
