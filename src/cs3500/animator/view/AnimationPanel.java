package cs3500.animator.view;


import cs3500.animator.model.shapes.Shape;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.List;

/**
 * This class represents the implementation of the visual animation view panel.
 * <p>
 *   The shape will be painted to the panel using JavaSwing. The supported types of shapes are
 *   retangle and ellipse.
 * </p>
 */
public class AnimationPanel extends JPanel {
  private List<Shape> shapes;

  public AnimationPanel() {
    super();
    this.shapes = new ArrayList<>();
  }

  /**
   * Setting the panel shapes.
   * @param shapes the shapes to draw
   */
  public void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Paint the component.
   * @param gr the graphic component to draw
   */
  public void paintComponent(Graphics gr) {
    Graphics2D g2 = (Graphics2D) gr;

    for (Shape s : shapes) {
      int x = (int) Math.round(s.getPosition().getX());
      int y = (int) Math.round(s.getPosition().getY());
      int w = (int) Math.round(s.getSize().getW());
      int h = (int) Math.round(s.getSize().getH());
      int r = (int) Math.round(s.getColor().getR());
      int g = (int) Math.round(s.getColor().getG());
      int b = (int) Math.round(s.getColor().getB());

      AffineTransform tx = new AffineTransform();
      tx.rotate(Math.toRadians(s.getRotation()), x + w / 2, y + h / 2);
      g2.setColor(new Color(r, g, b));

      if (s.getShapeType().equals("ellipse")) {
        Ellipse2D ell = new Ellipse2D.Float(x, y, w, h);
        g2.fill(tx.createTransformedShape(ell));
      } else if (s.getShapeType().equals("rectangle")) {
        Rectangle rect = new Rectangle(x, y, w, h);
        g2.fill(tx.createTransformedShape(rect));
      }
    }
  }
}
