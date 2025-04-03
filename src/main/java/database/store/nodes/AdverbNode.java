package database.store.nodes;

import database.store.Node;

import java.util.HashMap;

public class AdverbNode extends Node {
    private boolean isAdverb = false;

    public AdverbNode() {
        super(null, null);
    }

    public AdverbNode(final Character character, final Node parent) {
        super(character, parent);
    }

    public boolean getIsAdverb() {
        return isAdverb;
    }

    public void setIsAdverb(final boolean isAdverb) {
        this.isAdverb = isAdverb;
    }

    @Override
    public Node addChild(final char character) {
        final HashMap<Character, Node> children = getChildren();
        if (children.containsKey(character)) {
            return children.get(character);
        } else {
            final Node node = new AdverbNode(character, this);
            children.put(character, node);
            return node;
        }
    }

    @Override
    public String toString() {
        return "AdverbNode{" +
                "isAdverb=" + isAdverb +
                ", character=" + getCharacter() +
                '}';
    }
}