package task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MaxWordsFinder {

    public static void findMaxWordsLine(Scanner scanner) {
        System.out.println(">>> Завдання 1. Пошук рядка з максимальною кількістю слів <<<");
        System.out.println("Введіть шлях до файлу (або натисніть Enter, щоб використати 'lab-5/resources/input_text.txt'): ");
        System.out.print("> ");

        String input = scanner.nextLine().trim();
        String filePath;

        if (input.isEmpty()) {
            filePath = "lab-5/resources/input_text.txt";
            System.out.println("Шлях не введено. Використовуємо файл за замовчуванням: " + filePath);
        } else {
            filePath = input;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Помилка: Файл не знайдено за шляхом: " + filePath);
            System.out.println("Переконайтеся, що файл існує.");
            return;
        }

        String maxLine = null;
        int maxCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;

                int wordCount = trimmed.split("\\s+").length;

                if (wordCount > maxCount) {
                    maxCount = wordCount;
                    maxLine = line;
                }
            }

            if (maxLine == null) {
                System.out.println("Файл порожній або не містить тексту.");
            } else {
                System.out.println("Знайдено рядок з максимальною кількістю слів (" + maxCount + "):");
                System.out.println("\"" + maxLine + "\"");
            }

        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}