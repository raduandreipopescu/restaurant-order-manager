package com.personal.restaurantordermanager.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public class FileUtils {
    private String filePath;

    public FileUtils(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> putLinesFromFileToList() throws IOException {
        List<String[]> results = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            results.add(readLine.split(Pattern.quote("|")));
        }
        bufferedReader.close();
        return results;
    }
}