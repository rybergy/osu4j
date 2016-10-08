package com.github.oopsjpeg.osu4j.backend;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.oopsjpeg.osu4j.exception.OsuRateLimitException;

public class RateLimiter {
    private int freeTickets;
    private final int rateLimit;

    public RateLimiter(int rateLimitPerMinute) {
        freeTickets = rateLimitPerMinute;
        rateLimit = rateLimitPerMinute;
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                freeTickets = rateLimitPerMinute;
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    private void checkOkay() throws OsuRateLimitException {
        if (freeTickets == 0) {
            throw new OsuRateLimitException(rateLimit);
        }
    }

    public void getOrWaitForTicket() throws OsuRateLimitException {
        // FIXME: actually don't throw, but wait until the request is okay
        checkOkay();
        synchronized (this) {
            checkOkay();
            freeTickets--;
        }
    }

}
