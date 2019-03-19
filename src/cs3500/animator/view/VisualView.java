package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class VisualView extends ViewImpl{
  private JFrame viewFrame;
  private JPanel animationPanel;
  private JScrollPane scrollPane;

  public VisualView(int x, int y, int h, int w, int tickPerSecond, AnimationModel model) {
    super(x, y, h, w, tickPerSecond, model);

    this.viewFrame = new JFrame();
    this.viewFrame.setTitle("Animation Visual View");
    this.viewFrame.setSize(400, 400);
    this.viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new JPanel();
    animationPanel.setPreferredSize(new Dimension(500, 500));
    scrollPane = new JScrollPane(animationPanel);
    this.viewFrame.add(scrollPane, BorderLayout.CENTER);

    this.viewFrame.pack();
  }

  public void render() {
    this.viewFrame.setVisible(true);
  }

  public void renderShapeView(Shape s) {
  }

}
