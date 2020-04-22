package com.monolit.gamesjar.backend.domain;

public enum PlayingTime {
    HALFHOUR("30 min or less"),
    HOUR("About 1 hour"),
    TWOHOURS("Less than two hours"),
    MORETHANTWOHOURS("More than two hours");

    String time;

    PlayingTime(String time) {
        this.time = time;
    }

    String getTime() {
        return time;
    }
}
