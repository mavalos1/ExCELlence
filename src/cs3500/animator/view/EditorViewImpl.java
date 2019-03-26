package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditorViewImpl implements AnimationView {
  private JFrame viewFrame;
  private AnimationPanel animationPanel;
  private JPanel buttonPanel;
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton speedButton;

  /**
   * Initialize the view to a default JPanel, ready for rendering.
   */
  public EditorViewImpl() {
    JScrollPane scrollPane;
    viewFrame = new JFrame();
    viewFrame.setTitle("Animation Editor View");
    viewFrame.setSize(400, 400);
    viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.viewFrame.setLayout(new BorderLayout());
    animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(500, 500));
    animationPanel.setLayout(null);
    scrollPane = new JScrollPane(animationPanel);
    this.viewFrame.add(scrollPane, BorderLayout.CENTER);

    //button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    viewFrame.add(buttonPanel, BorderLayout.SOUTH);

    startButton = new JButton("Start");
    pauseButton = new JButton("Pause");
    restartButton = new JButton("Restart");
    loopButton = new JButton("Loop");
    speedButton = new JButton("Speed");
    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(speedButton);

    this.viewFrame.pack();
    viewFrame.setVisible(true);
  }

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
   * Render the shapes provided at the current tick.
   * @param tick the current tick of the model
   * @param shapes the list of shape to be rendered
   */
  public void render(int tick, List<Shape> shapes) {
    animationPanel.setShapes(shapes);
    viewFrame.revalidate();
    viewFrame.repaint();
  }
}
