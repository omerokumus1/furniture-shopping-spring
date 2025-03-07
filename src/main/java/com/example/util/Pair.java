package com.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pair <K, V> {
    private K key;
    private V value;
}
