package io.github.rephrasing.bliss.async;

import java.util.concurrent.*;

public class BlissScheduler {

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void runTaskAsync(BlissTask task) {
        executorService.execute(task);
    }

    public static void runScheduledTaskAsync(BlissTask task, long delay, TimeUnit timeUnit) {
        scheduledExecutorService.schedule(task, delay, timeUnit);
    }

    public static ScheduledFuture<?> runTaskAtFixedRateAsync(BlissTask task, long initDelay, long rate, TimeUnit timeUnit) {
        return scheduledExecutorService.scheduleAtFixedRate(task, initDelay, rate, timeUnit);
    }
}
