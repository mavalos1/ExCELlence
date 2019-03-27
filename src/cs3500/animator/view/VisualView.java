package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class represents the implementation of the visual view.
 * <p>
 *   The visual view will render the shapes passed to it into a Javax Swing JPanel.
 *   The visual view allows to set custom bounds and initiate render of one frame.
 * </p>
 */
public class VisualView implements AnimationView {
  private int x;
  private int y;
  private int w;
  private int h;
  private JFrame viewFrame;
  private AnimationPanel animationPanel;

  /**
   * Set the view bounds.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   */
  public void setBounds(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    viewFrame.setBounds(x,y,w,h);
  }

  /**
   * Initialize the view to a default JPanel, ready for rendering.
   */
  public VisualView() {
    JScrollPane scrollPane;
    viewFrame = new JFrame();
    viewFrame.setTitle("Animation Visual View");
    viewFrame.setSize(w, h);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(w, h));
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

  public void setListener(ActionListener l) {
    throw new UnsupportedOperationException("Visual view does not support action listener");
  }
}