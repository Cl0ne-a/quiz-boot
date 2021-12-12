package com.anna.quiz.loaders;

import java.util.Map;

public interface DataLoader <K, V>  {
    String receiveDataSource();
    Map<K, V> loadData();
}
