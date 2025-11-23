package task3;

import java.io.*;
import java.util.Scanner;

public class CipherTask {

    public static void runTask(Scanner scanner) {

        String sourceFile = "lab-5/resources/source_cipher.txt";
        String encryptedFile = "encrypted.dat";
        String decryptedFile = "decrypted.txt";

        System.out.println(">>> Завдання 3. Шифрування <<<");
        File file = new File(sourceFile);
        if (!file.exists()) {
            System.out.println("Помилка. Файл " + sourceFile + " не знайдено.");
            System.out.println("Створіть папку 'resources' і файл 'source_cipher.txt' всередині.");
            return;
        }

        System.out.print("Введіть ключ шифрування (ціле число, напр. 3): ");
        int key = 1;
        try {
            String line = scanner.nextLine();
            key = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Невірне число. Використовую ключ за замовчуванням: 1");
        }

        encrypt(sourceFile, encryptedFile, key);
        decrypt(encryptedFile, decryptedFile, key);
    }

    private static void encrypt(String source, String dest, int key) {
        System.out.print("Шифрування... ");
        try (FileInputStream fis = new FileInputStream(source);
             EncryptionFilterOutputStream efos = new EncryptionFilterOutputStream(new FileOutputStream(dest), key)) {

            int b;
            while ((b = fis.read()) != -1) {
                efos.write(b);
            }
            System.out.println("Виконано. Записано у " + dest);
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void decrypt(String source, String dest, int key) {
        System.out.print("Дешифрування... ");
        try (DecryptionFilterInputStream dfis = new DecryptionFilterInputStream(new FileInputStream(source), key);
             FileOutputStream fos = new FileOutputStream(dest)) {

            int b;
            while ((b = dfis.read()) != -1) {
                fos.write(b);
            }
            System.out.println("Виконано. Розшифровано у " + dest);

            System.out.println("\n>>> Вміст розшифрованого файлу:");
            printFileContent(dest);

        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void printFileContent(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Не вдалося прочитати результат.");
        }
    }
}
