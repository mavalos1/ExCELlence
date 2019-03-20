package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of the visual view.
 * <p>
 *   The visual view will render the shapes passed to it into a Javax Swing JPanel.
 *   The visual view allows to set custom bounds and initiate render of one frame.
 * </p>
 */
public class VisualView implements AnimationView {
  private JFrame viewFrame;
  private AnimationPanel animationPanel;
  private JScrollPane scrollPane;

  /**
   * Set the view bounds.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   */
  public void setBounds(int x, int y, int w, int h) {
    viewFrame.setBounds(x,y,w,h);
  }

  /**
   * Initialize the view to a default JPanel, ready for rendering.
   */
  public VisualView() {
    viewFrame = new JFrame();
    viewFrame.setTitle("Animation Visual View");
    viewFrame.setSize(400, 400);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(500, 500));
    animationPanel.setLayout(null);
    scrollPane = new JScrollPane(animationPanel);
    this.viewFrame.add(scrollPane, BorderLayout.CENTER);
    this.viewFrame.pack();
    viewFrame.setVisible(true);
  }

  /**
   * Render the shapes provided at the current tick.
   * @param tick the current tick of the model
   * @param shapes the list of shape to be rendered
   */
  public void render(int tick, List<Shape> shapes) {
    animationPanel.setShapes(shapes);
    viewFrame.revalidate();
    viewFrame.repaint();
  }

  class AnimationPanel extends JPanel {

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
            break;
        }

      }
    }
  }
}
