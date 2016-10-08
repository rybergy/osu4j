package com.github.oopsjpeg.osu4j.abstractbackend;

/**
 * A builder class for arguments. This class should follow the builder-pattern
 * 
 * @author WorldSEnder
 *
 * @param <A>
 *            the argument class being built
 */
public interface ArgumentBuilder<A> {
    /**
     * Builds the arguments from the input provided so far.
     * 
     * @return a new argument instance
     * @throws IllegalStateException
     *             when parts are missing or misformatted (out-of-range, etc.)
     */
    A build();
}
