package aoc;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {
    
    public static List<String> splitLines(String input) {
        return Arrays.asList(input.split(System.lineSeparator()));
    }

    public static List<Integer> parseLineToInts(String input, String separator) {
        String[] elements = input.split(separator);

        List<Integer> results = new ArrayList<>();
        for (String item : elements) {
            results.add(Integer.parseInt(item));
        }

        return results;
    }

}
