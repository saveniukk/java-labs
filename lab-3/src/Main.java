import controller.ShapeController;
import model.*;
import view.ShapeView;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = createSampleShapes();
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(shapes, view);

        controller.processShapes();
    }

    private static Shape[] createSampleShapes() {
        return new Shape[]{
                new Rectangle("Red", 10.0, 5.0),
                new Circle("Blue", 12.0),
                new Triangle("Green", 6.0, 8.0),
                new Rectangle("Blue", 7.0, 7.0),
                new Circle("Red", 5.5),
                new Triangle("Yellow", 10.0, 4.0),
                new Rectangle("Green", 3.0, 12.0),
                new Circle("Yellow", 2.0),
                new Triangle("Red", 5.0, 5.0),
                new Rectangle("Blue", 8.0, 6.0)
        };
    }
}