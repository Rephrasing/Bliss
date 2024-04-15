package io.github.rephrasing.bliss.user;


public interface GamePlayer {

    long getId();

    default <P extends GamePlayer> P as(Class<P> clazz) {
        return clazz.cast(this);
    }
}
