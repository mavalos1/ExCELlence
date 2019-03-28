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

  public void paintComponent(Graphics gr) {
    for (Shape s : shapes) {
      int x = (int) Math.round(s.getPosition().getX());
      int y = (int) Math.round(s.getPosition().getY());
      int w = (int) Math.round(s.getSize().getW());
      int h = (int) Math.round(s.getSize().getH());
      int r = (int) Math.round(s.getColor().getR());
      int g = (int) Math.round(s.getColor().getG());
      int b = (int) Math.round(s.getColor().getB());

      gr.setColor(new Color(r, g, b));

      if (s.getShapeType().equals("ellipse")) {
        gr.fillOval(x, y, w, h);
      } else if (s.getShapeType().equals("rectangle")) {
        gr.fillRect(x, y, w, h);
      }
    }
  }
}
