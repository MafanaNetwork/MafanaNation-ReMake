package me.TahaCheji.itemData;

public enum GameItemLevelXPTo {
    ONE(100),
    TWO(GameItemLevelXPTo.ONE.getXp() * 2),
    THREE(GameItemLevelXPTo.TWO.getXp() * 2),
    FOUR(GameItemLevelXPTo.THREE.getXp() * 2),
    FIVE(GameItemLevelXPTo.FOUR.getXp() * 2),
    SIX(GameItemLevelXPTo.FIVE.getXp() * 2),
    SEVEN(GameItemLevelXPTo.SIX.getXp() * 2),
    EIGHT(GameItemLevelXPTo.SEVEN.getXp() * 2),
    NINE(GameItemLevelXPTo.EIGHT.getXp() * 2),
    TEN(GameItemLevelXPTo.NINE.getXp() * 2);

    private final int xp;

    GameItemLevelXPTo(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    public static GameItemLevelXPTo getXpTo (GameItemLevel gameItemLevel) {
        if(gameItemLevel.getLevel() == 0) {
            return GameItemLevelXPTo.ONE;
        }
        if(gameItemLevel.getLevel() == 1) {
            return GameItemLevelXPTo.TWO;
        }
        if(gameItemLevel.getLevel() == 2) {
            return GameItemLevelXPTo.THREE;
        }
        if(gameItemLevel.getLevel() == 3) {
            return GameItemLevelXPTo.FOUR;
        }
        if(gameItemLevel.getLevel() == 4) {
            return GameItemLevelXPTo.FIVE;
        }
        if(gameItemLevel.getLevel() == 5) {
            return GameItemLevelXPTo.SIX;
        }
        if(gameItemLevel.getLevel() == 6) {
            return GameItemLevelXPTo.SEVEN;
        }
        if(gameItemLevel.getLevel() == 7) {
            return GameItemLevelXPTo.EIGHT;
        }
        if(gameItemLevel.getLevel() == 8) {
            return GameItemLevelXPTo.NINE;
        }
        if(gameItemLevel.getLevel() == 9) {
            return GameItemLevelXPTo.TEN;
        }
        return null;
    }

}
