package com.example.orderservice.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordFrequencyOriginalService {

    public void readAndWrite() throws IOException {
        String file1 = "C:\\Users\\POReddy\\sample.txt";
        String file2 = "C:\\Users\\POReddy\\sample2.txt";
        String[] files = {file1, file2};


        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\POReddy\\output5.txt"))){
            for (String file : files) {
                Map<String, Long> map = Files.lines(Paths.get(file)).flatMap(line -> Arrays.stream(line.split("\\s+")))
                        .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
                writer.println("Word:"+file+":");
                map.forEach((word, count) -> writer.println(word + ":" + count));
                writer.println();
            }
        } catch (Exception e) {

        }



/*        for (String file : files) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Map<String, Integer> frequencyMap = new HashMap<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }

            writer.println("Word Frequencies for " + file + ":");
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                writer.println(entry.getKey() + ": " + entry.getValue());
            }
            writer.println();
            reader.close();
        }

        writer.close();
    }*/
    }
}
