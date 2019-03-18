package cs3500.animator.model.shapes;

import java.awt.*;

public class Rectangle extends ShapeImpl implements Shape {
  public Rectangle(String name) {
    super(name);
    shapeType = "rectangle";
  }

  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.drawRect(30, 50, 420, 120);
  }
}
