package io.github.rephrasing.bliss.game;

import io.github.rephrasing.bliss.async.BlissTask;
import io.github.rephrasing.bliss.user.GamePlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface Game<P extends GamePlayer> {

    long getId();
    int getMinSize();
    int getMaxSize();
    String getGameTitle();
    void onStart();
    boolean isRunning();
    void setRunning(boolean running);
    GameLoop getGameLoop();
    void onEnd();

    @NotNull List<P> getPlayers();

    default <G extends Game<P>> G as(Class<G> clazz) {
        return clazz.cast(this);
    }

    default void executeAllPlayers(Consumer<P> consumer) {
        getPlayers().forEach(consumer);
    }

    default Optional<P> getGamePlayer(long id) {
        return getPlayers().stream().filter(p -> p.getId() == id).findFirst();
    }
}
