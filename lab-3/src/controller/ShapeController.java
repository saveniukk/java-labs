package controller;
import model.*;
import view.ShapeView;
import java.util.Arrays;

public class ShapeController {
    private Shape[] model;
    private ShapeView view;

    public ShapeController(Shape[] model, ShapeView view) {
        this.model = model;
        this.view = view;
    }

    public void processShapes() {
        view.printMessage(">>> Data set: ");
        view.printShapes(model);

        calculateAndShowTotalArea();
        calculateAndShowTotalAreaForType(Rectangle.class);
        calculateAndShowTotalAreaForType(Triangle.class);
        calculateAndShowTotalAreaForType(Circle.class);

        sortByArea();
        view.printMessage(">>> Calculated data sorted by area: ");
        view.printShapes(model);

        sortByColor();
        view.printMessage(">>> Calculated data sorted by color: ");
        view.printShapes(model);
    }

    private void calculateAndShowTotalArea() {
        double totalArea = 0;
        for (Shape s : model) {
            totalArea += s.calcArea();
        }
        view.printTotalArea(totalArea);
    }

    private void calculateAndShowTotalAreaForType(Class<?> shapeType) {
        double totalArea = 0;
        for (Shape s : model) {
            if(shapeType.isInstance(s)) {
                totalArea += s.calcArea();
            }
        }
        view.printTotalAreaByType(shapeType.getSimpleName(), totalArea);
    }

    private void sortByArea() {
        Arrays.sort(model, new ShapeAreaComparator());
    }

    private void sortByColor() {
        Arrays.sort(model, new ShapeColorComparator());
    }
}
