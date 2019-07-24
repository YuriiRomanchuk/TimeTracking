package com.time.tracking.converter;

public interface Converter<T, V> {
    V convert(T object) throws Exception;
}