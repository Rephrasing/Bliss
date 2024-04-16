package io.github.rephrasing.bliss.game;

import io.github.rephrasing.bliss.user.GamePlayer;
import lombok.Getter;

@Getter
@SuppressWarnings("all")
public final class GameEndContext<P extends GamePlayer> {

    private final Game<P> game;
    private final P winner;

    protected GameEndContext(Game<P> game, P winner) {
        this.game = game;
        this.winner = winner;
    }
}
