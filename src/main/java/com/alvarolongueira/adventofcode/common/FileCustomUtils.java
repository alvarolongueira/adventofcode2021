package com.alvarolongueira.adventofcode.common;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

public class FileCustomUtils {

    private final String fileName;
    private final Queue<String> queue = new LinkedList<>();

    public FileCustomUtils(String fileName) {
        this.fileName = fileName;
        this.readFile();
    }

    public Optional<String> readLine() {
        Optional<String> nextLine = Optional.ofNullable(this.queue.poll());
        if (!nextLine.isPresent() || nextLine.get().isEmpty()) {
            return Optional.empty();
        }
        return nextLine;
    }

    public List<String> readAllNoLineBreaks() {
        return this.queue.stream()
                .filter(line -> (line != null) && !line.isEmpty())
                .collect(Collectors.toList());
    }

    public List<String> readAll() {
        return this.queue.stream().collect(Collectors.toList());
    }

    private void readFile() {
        try {
            URL url = this.getClass().getClassLoader().getResource(this.fileName);
            File file = new File(url.toURI());
            List<String> lines = Files.readAllLines(Paths.get(file.getCanonicalPath()));
            this.queue.addAll(lines);

        } catch (URISyntaxException | IOException e) {
            System.err.println("Error reading file: " + this.fileName);
            e.printStackTrace();
        }
    }

}
