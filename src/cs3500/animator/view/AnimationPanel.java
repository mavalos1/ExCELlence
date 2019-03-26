package cs3500.animator.view;


import cs3500.animator.model.shapes.Shape;


import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List;

public class AnimationPanel extends JPanel {
  private List<Shape> shapes;

  public AnimationPanel() {
    super();
    this.shapes = new ArrayList<>();
  }

  public void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  public void paintComponent(Graphics g) {
    int x;
    int y;
    int w;
    int h;

    for (Shape s : shapes) {
      x = (int) Math.round(s.getPosition().getX());
      y = (int) Math.round(s.getPosition().getY());
      w = (int) Math.round(s.getSize().getW());
      h = (int) Math.round(s.getSize().getH());

      g.setColor(new Color(s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));

      if (s.getShapeType().equals("ellipse")) {
        g.fillOval(x, y, w, h);
      } else if (s.getShapeType().equals("rectangle")) {
        g.fillRect(x, y, w, h);
      }
    }
  }
}
