package it.unibo.mvc.model;


/**
 * Encapsulates the concept of configuration.
 */
public final class Configuration {

    private final int max; 
    private final int min;
    private final int attempts;

    private Configuration(final int max, final int min, final int attempts) {
        this.max = max;
        this.min = min;
        this.attempts = attempts;
    }

    /**
     * Returns the maximum value.
     *
     * @return the maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns the minimum value.
     *
     * @return the minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the number of attempts.
     *
     * @return the number of attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Checks the consistency of the configuration.
     *
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return attempts > 0 && min < max;
    }

    /**
     * Pattern <i>builder</i> used here because:
     * <ul>
     * <li>
     *     all the parameters of the {@code Configuration} class have a default value, which
     *     means that we would like to have all the possible combinations of
     *     constructors (one with three parameters, three with two parameters, three
     *     with a single parameter), which are way too many and confusing to use
     * </li>
     * <li>
     *      moreover, it would be impossible to provide all of them, because they are
     *      all of the same type, and only a single constructor can exist with a given
     *      list of parameter types.
     * </li>
     * <li>
     *     the {@code Configuration} class has three parameters of the same type, and it is
     *     unclear to understand, in a call to its contructor, which is which. By using
     *     the builder, we emulate the so-called "named arguments".
     * </li>
     * </ul>
     */
    public static class Builder {

        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int ATTEMPTS = 10;

        private int min = MIN;
        private int max = MAX;
        private int attempts = ATTEMPTS;
        private boolean consumed;

        /**
         * Sets the minimum value.
         *
         * @param min the minimum value
         * @return this builder, for method chaining
         */
        public Builder withMin(final int min) {
            this.min = min;
            return this;
        }

        /**
         * Sets the maximum value.
         *
         * @param max the maximum value
         * @return this builder, for method chaining
         */
        public Builder withMax(final int max) {
            this.max = max;
            return this;
        }

        /**
         * Sets the minimum number of attempts.
         *
         * @param attempts the attempts count
         * @return this builder, for method chaining
         */
        public Builder withMaxAttempts(final int attempts) {
            this.attempts = attempts;
            return this;
        }

        /**
         * Builds the {@link Configuration}.
         *
         * @return a {@code Configuration}
         */
        public final Configuration build() {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            return new Configuration(max, min, attempts);
        }
    }
}
