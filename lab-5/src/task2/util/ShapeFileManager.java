package task2.util;

import task2.model.Shape;
import java.io.*;

public class ShapeFileManager {

    public static void saveShapes(String filename, Shape[] shapes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapes);
            System.out.println(">>> Дані збережено у файл: " + filename);
        } catch (IOException e) {
            System.err.println("Помилка збереження: " + e.getMessage());
        }
    }

    public static Shape[] loadShapes(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Shape[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не знайдено: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Помилка читання: " + e.getMessage());
        }
        return null;
    }
}
