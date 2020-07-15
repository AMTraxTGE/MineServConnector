package com.amtraxtge.msc.ws;

import com.amtraxtge.msc.ws.commands.IWebSocketCommand;
import com.amtraxtge.msc.ws.commands.ServerStatusCommand;

import java.util.HashMap;

public class CommandFactory {
    private static HashMap<String, IWebSocketCommand> commands = new HashMap<String, IWebSocketCommand>();

    public static HashMap<String, IWebSocketCommand> load() {
        commands.put("STATUS", new ServerStatusCommand());

        return commands;
    }
}
