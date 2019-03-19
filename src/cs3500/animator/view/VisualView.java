package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VisualView implements AnimationView {
  private JFrame viewFrame;
  private AnimationPanel animationPanel;
  private JScrollPane scrollPane;

  public void setBounds(int x, int y, int w, int h) {
    viewFrame.setBounds(x,y,w,h);
  }

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
      int x, y, w, h;

      for (Shape s : shapes) {
        x = (int) Math.round(s.getPosition().getX());
        y = (int) Math.round(s.getPosition().getY());
        w = (int) Math.round(s.getSize().getW());
        h = (int) Math.round(s.getSize().getH());

        g.setColor(new Color(s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));

        switch(s.getShapeType()) {
          case "ellipse":
            g.fillOval(x, y, w, h);
            break;
          case "rectangle":
            g.fillRect(x, y, w, h);
            break;
        }

      }
    }
  }
}
