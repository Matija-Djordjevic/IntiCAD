package raf.draft.dsw.logging;

public class LoggerFactory {
    public IMessageListener<LogInfo> createLogger(final String name) {
        return switch(name){
            case "console" -> new ConsoleLogger();
            case "file" -> new FileLogger();
            default -> throw new IllegalArgumentException("Unsupported logger: " + name);
        };
    }
}

