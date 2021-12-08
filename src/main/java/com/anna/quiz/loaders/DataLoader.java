package com.anna.quiz.loaders;

import java.util.Map;

public interface DataLoader <K, V>  {
    Map<K, V> loadData();
}
