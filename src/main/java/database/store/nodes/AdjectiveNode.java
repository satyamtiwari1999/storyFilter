package database.store.nodes;

import database.store.Node;

public class AdjectiveNode extends Node {
    private boolean isAdjective = false;
    private AdjectiveNode(final Character character, final AdjectiveNode parent){
        super(character, parent);
    }

    public AdjectiveNode(){
        super(null, null);
    }

    @Override
    public String toString() {
        return "AdjectiveNode{" +
                "isAdjective=" + isAdjective +
                ", character=" + getCharacter() +
                ", parent=" + getParent() +
                '}';
    }

    @Override
    public Node addChild(final char character) {
        if(getChildren().containsKey(character)){
            return getChildren().get(character);
        } else {
            final AdjectiveNode newNode = new AdjectiveNode(character, this);
            getChildren().put(character, newNode);
            return newNode;
        }
    }

    public void setIsAdjective(boolean isAdjective) {
        this.isAdjective = isAdjective;
    }

    public boolean getIsAdjective() {
        return isAdjective;
    }
}
