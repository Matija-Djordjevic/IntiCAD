package raf.draft.dsw.logging;

import raf.draft.dsw.config.Config;
import raf.draft.dsw.utils.LogUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class FileLogger implements IMessageListener<LogInfo>{
    private final String path;

    @SuppressWarnings("FieldCanBeLocal")
    private final String defaultPath = "log.txt";

    public FileLogger (String path) {
        this.path = path;
    }
    public FileLogger () { this.path = defaultPath; }

    @Override
    public void onMessageReceived(LogInfo message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(LogUtils.getLogMsg(message) + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
