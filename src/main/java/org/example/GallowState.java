package org.example;

public enum GallowState {
    GALLOW_STATE_ZERO(" +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n=======", 0),
    GALLOW_STATE_ONE(" +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n=======", 1),
    GALLOW_STATE_TWO(" +---+\n  |   |\n  O   |\n /|\\  |\n      |\n=======", 2),
    GALLOW_STATE_THREE(" +---+\n  |   |\n  O   |\n /|   |\n      |\n=======", 3),
    GALLOW_STATE_FOUR(" +---+\n  |   |\n  O   |\n /    |\n      |\n=======", 4),
    GALLOW_STATE_FIVE(" +---+\n  |   |\n  O   |\n      |\n      |\n=======", 5),
    GALLOW_STATE_SIX(" +---+\n  |   |\n      |\n      |\n      |\n=======", 6);

    private String gallowPicture;
    private int life;

    GallowState(String picture, int life) {
        this.gallowPicture = picture;
        this.life = life;
    }

    public static String getGallowPicture(int life) {
        for (GallowState gallow : GallowState.values()) {
            if (gallow.life == life) {
                return gallow.gallowPicture;
            }
        }
        return "Out of bounds!";
    }

}
