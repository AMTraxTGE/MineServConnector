package com.amtraxtge.msc.ws.responses;

import com.google.gson.JsonObject;

public class UnknownCommandResponse implements IWebSocketResponse {
    private Integer statusCode = 404;
    private String command;

    private UnknownCommandResponse(String command) {
        this.command = command;
    }

    public static UnknownCommandResponse create(String command) {
        return new UnknownCommandResponse(command);
    }

    @Override
    public String toJSON() {
        JsonObject object = new JsonObject();
        object.addProperty("status", statusCode);
        object.addProperty("type", "UnknownCommand");
        object.addProperty("message", "Command not found");
        object.addProperty("command", command);
        return object.toString();
    }
}
