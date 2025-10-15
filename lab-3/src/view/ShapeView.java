package view;
import model.Shape;

public class ShapeView {
    public void printMessage (String message) {
        System.out.println(message);
    }

    public void printShapes (Shape[] shapes) {
        if (shapes == null || shapes.length == 0) {
            System.out.println("Shapes are empty");
            return;
        }
        for (Shape shape : shapes) {
            shape.draw();
        }
        System.out.println("<<< \n");
    }

    public void printTotalArea (double totalArea) {
        System.out.println("Total area is " + String.format( "%.2f", totalArea) + "\n");
    }

    public void printTotalAreaByType (String shapeType, double totalArea) {
        System.out.println("Total area for " + shapeType + " is: " + String.format( "%.2f", totalArea) + "\n");
    }
}
