package database.store.nodes;

import database.store.Node;

import java.util.HashMap;

public class NounNode extends Node {
    private boolean isNoun = false;

    public NounNode() {
        super(null, null);
    }
    public NounNode(final Character character, final Node parent) {
        super(character, parent);
    }

    public boolean getIsNoun() {
        return isNoun;
    }

    public void setIsNoun(final boolean isNoun) {
        this.isNoun = isNoun;
    }

    @Override
    public Node addChild(final char character) {
        final HashMap<Character, Node> children = getChildren();
        if(children.containsKey(character)) {
            return children.get(character);
        } else {
            final Node node = new NounNode(character, this);
            children.put(character, node);
            return node;
        }
    }

    @Override
    public String toString() {
        return "NounNode {" +
                "isNoun=" + isNoun +
                ", character=" + getCharacter() +
                '}';
    }
}
