import task1.MaxWordsFinder;
import task2.controller.ShapeController;
import task2.model.*;
import task2.view.ShapeView;
import task3.CipherTask;
import task4.HtmlTagCounter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n>>> Оберіть завдання: <<<");
            System.out.println("1. Максимальна кількість слів у рядку");
            System.out.println("2. OOP Task (Shapes + Serialization)");
            System.out.println("3. Шифрування файлів");
            System.out.println("4. Аналіз HTML тегів");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            String cmd = scanner.nextLine();

            switch (cmd) {
                case "1":
                    MaxWordsFinder.findMaxWordsLine(scanner);
                    break;

                case "2":
                    runTask2(scanner);
                    break;

                case "3":
                    CipherTask.runTask(scanner);
                    break;

                case "4":
                    HtmlTagCounter.analyzeTags(scanner);
                    break;

                case "0":
                    System.out.println("Роботу завершено.");
                    return;

                default:
                    System.out.println("Невідома команда. Спробуйте ще раз.");
            }
        }
    }

    private static void runTask2(Scanner scanner) {
        System.out.println("\n--- Запуск Task 2 (Shapes) ---");

        Shape[] shapes = {
                new Rectangle("Red", 10.0, 5.0),
                new Circle("Blue", 12.0),
                new Triangle("Green", 6.0, 8.0),
                new Rectangle("Yellow", 20.0, 10.0),
                new Circle("Red", 5.5)
        };

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(shapes, view, scanner);

        controller.run();
    }
}
