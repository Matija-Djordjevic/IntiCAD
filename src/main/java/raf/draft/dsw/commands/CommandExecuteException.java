package raf.draft.dsw.commands;

public class CommandExecuteException extends RuntimeException {
    public CommandExecuteException() { super(); }
    public CommandExecuteException(String message) { super(message); }
    public CommandExecuteException(String message, Throwable cause) { super(message, cause); }
    public CommandExecuteException(Throwable cause) { super(cause); }
}

