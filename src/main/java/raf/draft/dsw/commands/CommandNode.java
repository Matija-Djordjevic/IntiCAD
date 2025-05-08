package raf.draft.dsw.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CommandNode {
    final ICommand command;
    final CommandNode parent;
    final List<CommandNode> children;

    public CommandNode(ICommand command, CommandNode parent) {
        this.command = command;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public void addChild(CommandNode child) { children.add(child); }
}