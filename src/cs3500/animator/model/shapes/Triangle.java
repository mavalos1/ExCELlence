package cs3500.animator.model.shapes;

public class Triangle extends ShapeImpl implements Shape {
  public Triangle(String name) {
    super(name);
    shapeType = "triangle";
  }

  public void draw() {
    // Implementation-dependent of rectangle shape, not yet clear of how to draw the shape

  }
}
