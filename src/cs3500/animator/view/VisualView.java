package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class VisualView extends ViewImpl{
  private JFrame viewFrame;
  private AnimationPanel animationPanel;
  private JScrollPane scrollPane;

  public VisualView(int x, int y, int h, int w, int tickPerSecond, AnimationModel model) {
    super(x, y, h, w, tickPerSecond, model);

    this.viewFrame = new JFrame();
    this.viewFrame.setTitle("Animation Visual View");
    this.viewFrame.setSize(400, 400);
    this.viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a border layout with drawing panel in center and button panel in south
    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setModel(model);
    animationPanel.setPreferredSize(new Dimension(500, 500));
    animationPanel.setLayout(null);
    scrollPane = new JScrollPane(animationPanel);
    this.viewFrame.add(scrollPane, BorderLayout.CENTER);
    this.viewFrame.pack();
    viewFrame.setVisible(true);
  }

  public void render() {

    viewFrame.revalidate();
    viewFrame.repaint();

    long tickMS = 1000 / this.tPs;
    boolean shouldPlay = true;

    while (shouldPlay) {
      shouldPlay = false;

      for (Shape s : model.getAllShapes()) {
        if (s.hasTransition()) {
          shouldPlay = true;
        }
      }

      model.tick();
      try {
        Thread.sleep(tickMS);
      } catch (Exception e) {
        System.out.println("Cant sleep");
      }
      viewFrame.revalidate();
      viewFrame.repaint();
    }
  }

  class AnimationPanel extends JPanel {

    private AnimationModel model;

    public void setModel(AnimationModel model) {
      this.model = model;
    }

    public void paintComponent(Graphics g) {
      int x, y, w, h;
      for (Shape s : model.getAllShapes()) {
        x = (int)Math.round(s.getPosition().getX());
        y = (int)Math.round(s.getPosition().getY());
        w = (int)Math.round(s.getSize().getW());
        h = (int)Math.round(s.getSize().getH());

        g.setColor(new Color(s.getColor().getR(), s.getColor().getG(), s.getColor().getB()));

        switch (s.getShapeType()) {
          case "rectangle":
            g.fillRect(x, y, w, h);
            break;
          case "triangle":
            int[] xpoints = {x, x+w, (x+w)/2};
            int[] ypoints = {y+h, y+h, y};
            g.fillPolygon(xpoints, ypoints, 3);
            break;
          case "ellipse":
            g.fillOval(x, y, w, h);
            break;
        }
      }
    }
  }
}
