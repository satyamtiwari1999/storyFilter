package database.store;

import java.util.HashMap;

public abstract class Node {
    private final Node parent;
    private final Character character;
    private final HashMap<Character, Node> children = new HashMap<>();

    public Node(final Character character, final Node parent) {
        this.parent = parent;
        this.character = character;
    }

    public HashMap<Character, Node> getChildren() {
        return children;
    }

    abstract public Node addChild(final char character);

    public Character getCharacter() {
        return character;
    }

    public Node getParent() {
        return parent;
    }
}
