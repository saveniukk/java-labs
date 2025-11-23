package task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTagCounter {

    public static void analyzeTags(Scanner scanner) {
        System.out.println(">>> Завдання 4. Аналіз тегів <<<");
        System.out.println("Введіть URL (або натисніть Enter, щоб використати https://xkcd.com/): ");
        System.out.print("> ");

        String input = scanner.nextLine().trim();
        String urlString;

        if (input.isEmpty()) {
            urlString = "https://xkcd.com/";
            System.out.println("URL не введено. Використовуємо за замовчуванням: " + urlString);
        } else {
            urlString = input;
        }

        if (!urlString.startsWith("http")) {
            urlString = "https://" + urlString;
            System.out.println("Додано префікс протоколу: " + urlString);
        }

        Map<String, Integer> tagCounts = new HashMap<>();
        Pattern tagPattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>");

        try {
            URL url = new URL(urlString);
            System.out.println("З'єднання з " + urlString + "...");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = tagPattern.matcher(line);
                    while (matcher.find()) {
                        String tagName = matcher.group(1).toLowerCase();
                        tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);
                    }
                }
            }

            if (tagCounts.isEmpty()) {
                System.out.println("Помилка: тегів не знайдено або сторінка недоступна.");
                return;
            }

            System.out.println("Знайдено унікальних тегів: " + tagCounts.size());

            // a. Сортування за назвою
            System.out.println("\n>>> Сортування за назвою:");
            TreeMap<String, Integer> sortedByName = new TreeMap<>(tagCounts);
            sortedByName.forEach((k, v) -> System.out.println(k + ": " + v));

            // b. Сортування за частотою (Зростання)
            System.out.println("\n>>> Сортування за частотою (Зростання):");
            tagCounts.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (Exception e) {
            System.err.println("Помилка при роботі з URL: " + e.getMessage());
        }
    }
}