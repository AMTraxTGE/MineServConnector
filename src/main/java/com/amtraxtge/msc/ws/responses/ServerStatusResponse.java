package com.amtraxtge.msc.ws.responses;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.UUID;

public class ServerStatusResponse implements IWebSocketResponse {
    private Integer statusCode = 200;
    private Boolean online;
    private List<String> playerNames;
    private UUID serverId;

    private ServerStatusResponse(Boolean online, List<String> playerNames, UUID serverId) {
        this.online = online;
        this.playerNames = playerNames;
        this.serverId = serverId;
    }

    public static ServerStatusResponse create(Boolean online, List<String> playerNames, UUID serverId) {
        return new ServerStatusResponse(online, playerNames, serverId);
    }

    @Override
    public String toJSON() {
        JsonObject object = new JsonObject();
        object.addProperty("status", statusCode);
        object.addProperty("server", serverId.toString());
        object.addProperty("online", online);
        object.addProperty("players", new Gson().toJson(playerNames));
        return object.toString();
    }
}
