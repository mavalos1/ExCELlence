package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;

public class Triangle extends ShapeImpl implements Shape {
  public Triangle(String name, Position2D p, Size s, Color c, double r) {
    super(name, p, s, c, r);
    shapeType = "triangle";
  }


  public String SVGHeader() {
    return "";
  }


  public String SVGFooter() {
    return "";
  }

  public String SVGTransition(int tickMS) {
    return "";
  }
}
