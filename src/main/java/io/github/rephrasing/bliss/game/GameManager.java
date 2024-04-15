package io.github.rephrasing.bliss.game;

import io.github.rephrasing.bliss.async.BlissScheduler;

import io.github.rephrasing.bliss.user.GamePlayer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface GameManager<P extends GamePlayer> {

    List<Game<P>> getGames();
    Optional<Game<P>> getGameById(long id);
    Game<P> getGameByUser(P player);

    Game<P> createGame();

    default ScheduledFuture<?> startGameLoop(Game<P> game, long rate, TimeUnit seconds) {
        return BlissScheduler.runTaskAtFixedRateAsync(() -> {
            if (game.isRunning()) game.gameLoop();
        }, 0, rate, seconds);
    }

}
