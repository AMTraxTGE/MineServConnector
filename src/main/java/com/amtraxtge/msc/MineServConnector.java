package com.amtraxtge.msc;

import com.amtraxtge.msc.utilities.configuration.MineConfig;
import com.amtraxtge.msc.utilities.logging.MineLogger;
import com.amtraxtge.msc.ws.ConnectionManager;
import com.amtraxtge.msc.ws.MineSocketServer;
import com.amtraxtge.msc.ws.responses.SocketClosedResponse;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public class MineServConnector extends JavaPlugin {
    private MineSocketServer server;
    private ConnectionManager manager;
    private volatile Thread serverThread;

    @Override
    public void onEnable() {
        manager = ConnectionManager.create();
        server = MineSocketServer.create(MineConfig.get().serverAddress(), manager);

        try {
            startServer();
        } catch (Exception e) {
            MineLogger.log(Level.SEVERE, "Unable to start Web Socket Server", e);
        }
    }

    @Override
    public void onDisable() {
        try {
            manager.closeAll(true);
            server.stop();
        } catch (IOException | InterruptedException | SecurityException e) {
            MineLogger.severe("Error Shutting Down", e);
        }
    }

    private void startServer() {
        if (MineConfig.get().sslEnabled()) {
            // TODO: ENABLE SSL FOR SERVER
        }

        serverThread = new Thread(() -> server.run());
        serverThread.start();
    }
}
