package com.anna.quiz.csvreader;

import com.anna.quiz.conf.LocaleManager;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@Service
public class ScanReaderImpl implements ScanReader{
    @Override
    public Reader readCsv(String source) {
        Reader data = null;
        try {
            data = new FileReader(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getStringFromBundle(LocaleManager localeManager, CSVRecord record, String key) {
        var resourceBundle = localeManager.getBundle();
        return resourceBundle.containsKey(record.get(key))
                ? resourceBundle.getString(record.get(key))
                : "no information available";
    }
}
