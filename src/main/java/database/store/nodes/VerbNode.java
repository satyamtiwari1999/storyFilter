package database.store.nodes;

import database.store.Node;

import java.util.HashMap;

public class VerbNode extends Node {
    public boolean isVerb;
    public VerbNode(final Node parent, final Character character) {
        super(character, parent);
    }

    public VerbNode() {
        super(null, null);
    }

    @Override
    public Node addChild(final char character) {
        final HashMap<Character, Node> children = getChildren();
        if(children.containsKey(character)) {
            return children.get(character);
        }
        final Node node = new VerbNode(this, character);
        children.put(character, node);
        return node;
    }

    public boolean getIsVerb() {
        return isVerb;
    }

    public void setIsVerb(final boolean isVerb) {
        this.isVerb = isVerb;
    }

    @Override
    public String toString() {
        return "VerbNode{" +
                "isVerb=" + isVerb +
                ", character=" + getCharacter() +
                '}';
    }
}
