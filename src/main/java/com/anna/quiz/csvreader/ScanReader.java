package com.anna.quiz.csvreader;

import com.anna.quiz.conf.LocaleManager;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;

public interface ScanReader {
    Reader readCsv(String source);
    String getStringFromBundle(LocaleManager localeManager, CSVRecord record, String key);
}
