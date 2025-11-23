package task2.view;

import task2.model.Shape;

public class ShapeView {

    public void print(String msg) {
        System.out.print(msg);
    }

    public void printLine(String msg) {
        System.out.println(msg);
    }

    public void showShapes(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) {
            System.out.println("Порожній список фігур.");
            return;
        }

        for (Shape s : shapes) {
            System.out.println(s + ", area=" + String.format("%.2f", s.calcArea()));
        }
        System.out.println();
    }
}
