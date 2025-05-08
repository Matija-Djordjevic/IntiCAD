package raf.draft.dsw.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.observer.IPublisher;
import raf.draft.dsw.observer.ISubscriber;
import raf.draft.dsw.utils.JsonSerializationUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@Setter @Getter
public class Config implements IPublisher {
    @JsonIgnore
    final ArrayList<ISubscriber> subscribers = new ArrayList<>();

    @JsonProperty("Language")
    private String language;

    @JsonProperty("ToolBar")
    private ToolBar toolBar;

    @JsonProperty("Settings")
    private Settings settings;

    @JsonProperty("SavePath")
    private String savePath;

    @JsonProperty("MenuBar")
    private MenuBar menuBar;

    @JsonProperty("MainFrame")
    private MainFrame mainFrame;

    @Override
    public void addSubscriber(ISubscriber subscriber) {subscribers.add(subscriber);}

    @Override
    public void removeSubscriber(ISubscriber subscriber) {subscribers.remove(subscriber);}

    @Override
    public void notifySubscribers() {subscribers.forEach(sub -> sub.update(this));}

    @Getter @Setter
    public static class ToolBar {
        @JsonProperty("items")
        private String[] items;
    }

    @Getter
    public static class MenuBar {
        @JsonProperty("file")
        private HashMap<String, String> file;

        @JsonProperty("edit")
        private HashMap<String, String> edit;

        @JsonProperty("view")
        private HashMap<String, String> view;
    }

    @Getter @Setter
    public static class MainFrame {
        @JsonProperty("defaultWidth")
        private int defaultWidth;

        @JsonProperty("defaultHeight")
        private int defaultHeight;

        @JsonProperty("theme")
        private String theme;

        @JsonProperty("title")
        private HashMap<String, String> title;
    }


    @JsonIgnore
    private static Config instance;

    public static Config getInstance() {
        return instance = instance == null
                ? JsonSerializationUtils.loadFromFile(Config.class, new File("config.json"))
                : instance;
    }

    private Config() {};
}