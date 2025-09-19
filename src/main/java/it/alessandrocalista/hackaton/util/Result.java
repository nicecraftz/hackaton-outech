package it.alessandrocalista.hackaton.util;

public interface Result<T> {

    T value();

    String error();

    default boolean success() {
        return false;
    }

    record Ok<T>(T value) implements Result<T> {

        @Override
        public String error() {
            return "";
        }

        @Override
        public boolean success() {
            return true;
        }
    }

    record Error<T>(String error) implements Result<T> {
        @Override
        public T value() {
            return null;
        }
    }


}
