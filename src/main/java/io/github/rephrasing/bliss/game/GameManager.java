package io.github.rephrasing.bliss.game;

import io.github.rephrasing.bliss.async.BlissScheduler;
import io.github.rephrasing.bliss.user.GamePlayer;

import java.util.List;
import java.util.Optional;

public interface GameManager<P extends GamePlayer> {

    List<Game<P>> getGames();
    Optional<Game<P>> getGameById(long id);
    Game<P> getGameByUser(P player);

    default boolean attemptStartGame(long gameId) {
        Optional<Game<P>> gameOptional = getGameById(gameId);
        if (gameOptional.isEmpty()) return false;

        Game<P> game = gameOptional.get();
        if (game.getPlayers().size() < game.getMinSize()) return false;

        game.onStart();
        game.setRunning(true);
        BlissScheduler.runTaskAsync(() -> {
            long lastDelta = System.nanoTime();
            long nanoSec = 1_000_000_000;
            while (game.isRunning()) {
                long nowDelta = System.nanoTime();
                double timeSinceLastDelta = nowDelta - lastDelta;
                double delta = timeSinceLastDelta / nanoSec;
                game.getGameLoop().run(delta);
            }
        });
        return true;
    }

    default Optional<GameEndContext<P>> endGame(long gameId, P winner) {
        Optional<Game<P>> gameOptional = getGameById(gameId);
        if (gameOptional.isEmpty()) return Optional.empty();

        Game<P> game = gameOptional.get();
        game.setRunning(false);

        game.onEnd();
        return Optional.of(new GameEndContext<>(game, winner));
    }
}
