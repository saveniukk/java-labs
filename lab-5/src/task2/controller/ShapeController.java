package task2.controller;

import task2.model.*;
import task2.view.ShapeView;

import java.util.Arrays;
import java.util.Scanner;
import task2.util.ShapeFileManager;


public class ShapeController {

    private Shape[] shapes;
    private final ShapeView view;
    private final Scanner scanner;

    public ShapeController(Shape[] shapes, ShapeView view, Scanner scanner) {
        this.shapes = shapes;
        this.view = view;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            view.printLine("");
            view.printLine(">>> Меню Task 2 <<<");
            view.printLine("1. Показати всі фігури");
            view.printLine("2. Обробка (сортування, площі)");
            view.printLine("3. Зберегти у файл");
            view.printLine("4. Завантажити з файлу");
            view.printLine("0. Вихід");
            view.print("Ваш вибір: ");

            String cmd = scanner.nextLine();

            switch (cmd) {
                case "1" -> view.showShapes(shapes);
                case "2" -> processShapes();
                case "3" -> saveShapes();
                case "4" -> loadShapes();
                case "0" -> {
                    view.printLine("Вихід з Task 2...");
                    return;
                }
                default -> view.printLine("Невідома команда.");
            }
        }
    }

    private void processShapes() {
        if (shapes == null || shapes.length == 0) {
            view.printLine("Немає фігур для обробки.");
            return;
        }

        view.printLine("\n=== Початкові фігури ===");
        view.showShapes(shapes);

        double totalArea = Arrays.stream(shapes)
                .mapToDouble(Shape::calcArea)
                .sum();
        view.printLine("Загальна площа: " + String.format("%.2f", totalArea));

        view.printLine("\n=== Площа за типами ===");
        calcAreaByType(Rectangle.class);
        calcAreaByType(Circle.class);
        calcAreaByType(Triangle.class);

        Shape[] sortedByArea = shapes.clone();
        Arrays.sort(sortedByArea, new ShapeAreaComparator());
        view.printLine("\n=== Сортування за площею ===");
        view.showShapes(sortedByArea);

        Shape[] sortedByColor = shapes.clone();
        Arrays.sort(sortedByColor, new ShapeColorComparator());
        view.printLine("\n=== Сортування за кольором ===");
        view.showShapes(sortedByColor);
    }

    private void calcAreaByType(Class<?> type) {
        double area = Arrays.stream(shapes)
                .filter(type::isInstance)
                .mapToDouble(Shape::calcArea)
                .sum();
        view.printLine(type.getSimpleName() + ": " + String.format("%.2f", area));
    }

    private void saveShapes() {
        view.print("Введіть ім'я файлу: ");
        String path = scanner.nextLine();
        ShapeFileManager.saveShapes(path, shapes);
    }

    private void loadShapes() {
        view.print("Введіть ім'я файлу: ");
        String path = scanner.nextLine();

        Shape[] loaded = ShapeFileManager.loadShapes(path);
        if (loaded == null) {
            view.printLine("Не вдалося завантажити фігури.");
            return;
        }

        shapes = loaded;
        view.printLine("Фігури успішно завантажено!");
        view.showShapes(shapes);
    }
}
