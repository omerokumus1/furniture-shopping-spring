package com.omerokumus.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pair <K, V> {
    private K key;
    private V value;
}
