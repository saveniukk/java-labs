import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CuratorJournalRecord> journal = new ArrayList<>();

        while (true) {
            System.out.println("\n" + "Меню журналу куратора");
            System.out.println("1. Додати новий запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вихід");
            System.out.print("Виберіть номер опції: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewRecord(scanner, journal);
                    break;
                case "2":
                    showAllRecords(journal);
                    break;
                case "3":
                    System.out.println("Завершення роботи програми");
                    return;
                default:
                    System.out.println("Такої опції не існує. Спробуйте ще раз");
                    break;
            }
        }
    }

    public static void addNewRecord(Scanner scanner, List<CuratorJournalRecord> journal) {
        System.out.println("\n" + " >>> Додавання нового запису <<< ");
        String lastName = getString(scanner, "прізвище", "[а-яА-ЯІіЇїЄєҐґ'-]+");
        String firstName = getString(scanner, "ім'я", "[а-яА-ЯІіЇїЄєҐґ'-]+");
        LocalDate dateOfBirth = getDateOfBirth(scanner);
        String phoneNumber = getPhoneNumber(scanner);
        Address address = getAddress(scanner);

        CuratorJournalRecord newRecord = new CuratorJournalRecord(lastName, firstName, dateOfBirth, phoneNumber, address);
        journal.add(newRecord);
        System.out.println("\n" + ">>> Новий запис успішно додано <<<");
    }

    public static void showAllRecords(List<CuratorJournalRecord> journal) {
        if (journal.isEmpty()) {
            System.out.println("\nЖурнал порожній");
            return;
        }

        System.out.println("\n" + " >>> Усі записи журналу <<< ");
        for (CuratorJournalRecord record : journal) {
            System.out.println(record);
        }
    }

    private static String getString(Scanner scanner, String fieldName, String regex) {
        String input;
        while (true) {
            System.out.print("Введіть " + fieldName + " студента: ");
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Помилка: " + fieldName + " не може бути порожнім");
            } else if (!input.matches(regex)) {
                System.out.println("Помилка: " + fieldName + " містить некоректні символи");
            } else {
                return input;
            }
        }
    }

    private static LocalDate getDateOfBirth(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true) {
            System.out.print("Введіть дату народження (у форматі ДД.ММ.РРРР): ");
            String dateInput = scanner.nextLine();
            try {
                LocalDate birthDate = LocalDate.parse(dateInput, formatter);
                if (birthDate.isAfter(LocalDate.now())) {
                    System.out.println("Помилка: дата народження не може бути в майбутньому");
                } else {
                    return birthDate;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Помилка: неправильний формат дати. Спробуйте ще раз");
            }
        }
    }

    private static String getPhoneNumber(Scanner scanner) {
        String regex = "^\\+380\\d{9}$";
        while (true) {
            System.out.print("Введіть номер телефону (у форматі +380XXXXXXXXX): ");
            String phoneInput = scanner.nextLine();
            if (phoneInput.matches(regex)) {
                return phoneInput;
            } else {
                System.out.println("Помилка: неправильний формат номеру. Спробуйте ще раз");
            }
        }
    }

    private static Address getAddress(Scanner scanner) {
        System.out.println(" >>> Введення адреси <<<");
        String street = getString(scanner, "назву вулиці", "[а-яА-ЯІіЇїЄєҐґ'\\s\\.-]+");
        String building = getBuilding(scanner);
        int apartment = getApartment(scanner);
        return new Address(street, building, apartment);
    }

    private static String getBuilding(Scanner scanner) {
        while (true) {
            System.out.print("Введіть номер будинку: ");
            String building = scanner.nextLine();
            if (!building.isBlank()) {
                return building;
            } else {
                System.out.println("Помилка: номер будинку не може бути порожнім");
            }
        }
    }

    private static int getApartment(Scanner scanner) {
        while (true) {
            System.out.print("Введіть номер квартири: ");
            String apartmentInput = scanner.nextLine();
            try {
                int apartmentNumber = Integer.parseInt(apartmentInput);
                if (apartmentNumber > 0) {
                    return apartmentNumber;
                } else {
                    System.out.println("Помилка: номер квартири має бути додатним числом");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть, будь ласка, число");
            }
        }
    }
}
