package me.TahaCheji.itemData;

public enum ItemAbilityType {

    LEFT_CLICK("ON HIT"),
    RIGHT_CLICK("RIGHT CLICK"),
    HOLDING("HOLDING"),
    NONE("");

    private final String text;

    ItemAbilityType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
