package model;

public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return (0.5 * height * base);
    }

    @Override
    public String toString() {
        return super.toString() + ", base=" + base + ", height=" + height;
    }
}
