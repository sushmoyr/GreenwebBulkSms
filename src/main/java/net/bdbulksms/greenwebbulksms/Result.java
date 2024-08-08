package net.bdbulksms.greenwebbulksms;

import java.util.function.Consumer;

public sealed class Result<T> permits Result.Success, Result.Failure {

    // when method to call the appropriate function based on the type of the result
    public final void when(Consumer<T> successConsumer, Consumer<String> failureConsumer) {
        if (this instanceof Success<T> success) {
            successConsumer.accept(success.getValue());
        } else if (this instanceof Failure<T> failure) {
            failureConsumer.accept(failure.getMessage());
        } else {
            throw new IllegalStateException("Unknown result type.");
        }
    }

    public static final class Success<T> extends Result<T> {
        private final T value;

        public Success(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    public static final class Failure<T> extends Result<T> {
        private final String message;

        public Failure(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static <T> Failure<T> failure(String message) {
        return new Failure<>(message);
    }

    public static <T> Success<T> success(T value) {
        return new Success<>(value);
    }

}
