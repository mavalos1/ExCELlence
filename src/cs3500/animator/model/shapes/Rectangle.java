package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;

public class Rectangle extends ShapeImpl implements Shape {
  public Rectangle(String name, Position2D p, Size s, Color c, double r) {
    super(name, p, s, c, r);
    shapeType = "rectangle";
  }

  public Rectangle(String name) {
    super(name);
    shapeType = "rectangle";
  }
}
