package cs3500.animator.model.shapes;

public class Oval extends ShapeImpl implements Shape {
  public Oval(String name) {
    super(name);
    shapeType = "ellipse";
  }

  public void draw() {
    // Implementation-dependent of oval shape, not yet clear of how to draw the shape

  }
}
