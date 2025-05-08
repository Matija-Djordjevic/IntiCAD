package raf.draft.dsw.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class TreeCommandsManagerAtomic implements ITreeCommandsManager {
    CommandNode root;
    CommandNode currentNode;

    public TreeCommandsManagerAtomic() {
        root = new CommandNode(null, null);
        currentNode = root;
    }

    @Override
    public void redoAt(int index) throws IndexOutOfBoundsException {
        var children = currentNode.getChildren();
        if(index < 0 || index >= children.size()) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + children.size());

        currentNode = children.get(index);
        currentNode.getCommand().redo();
    }

    @Override
    public List<ICommand> getAvailableCommands() {
        return currentNode
                .getChildren()
                .stream()
                .map(CommandNode::getCommand)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void addCommand(ICommand command) {
        if(command == null) throw new NullPointerException("command is null");

        try{
            command.redo();
            var newChild = new CommandNode(command, currentNode);
            currentNode.addChild(newChild);
            currentNode = newChild;
        }catch(Exception _){
            command.undo();
        }
    }

    @Override
    public void redo() {
        if(!canRedo()) throw new IllegalStateException("Can't redo");
        redoAt(0);
    }

    @Override
    public void undo() {
        if(!canUndo()) throw new IllegalStateException("Can't undo");

        currentNode.getCommand().undo();

        if(currentNode == root) currentNode = null;
        else currentNode = currentNode.getParent();
    }

    @Override
    public boolean canUndo() { return currentNode != root; }

    @Override
    public boolean canRedo() { return !currentNode.getChildren().isEmpty(); }
}
