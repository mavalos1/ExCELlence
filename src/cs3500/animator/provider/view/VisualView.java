package cs3500.animator.provider.view;

//This import was changed to accomodate for ReadOnlyExCELenceAnimatorModel location in provider.view
import cs3500.animator.provider.view.ReadOnlyExCELenceAnimatorModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * This class represents a concrete implementation of a visual view of an animation. A visual view
 * allows users to view their animation as if it were a video.
 */
public class VisualView extends JFrame implements IView {

  private ReadOnlyExCELenceAnimatorModel model;

  private PlaybackRenderer animation;
  private Container cp;
  private JPanel statusBar;
  private JLabel statusLabel;
  private JScrollPane scrollPane;
  private int delay;


  /**
   * Creates a visual view with the given animation model
   *
   * @param model the animation model
   */
  public VisualView(ReadOnlyExCELenceAnimatorModel model) {
    this.cp = new Container();
    this.model = model;
  }

  private JPanel createStatusBar() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    statusLabel = new JLabel("Ready");
    panel.setBackground(Color.white);
    panel.add(statusLabel, BorderLayout.CENTER);

    panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    return panel;
  }


  @Override
  public void showView() {
    if (model == null) {
      statusLabel.setText("Cannot have a null model");
      throw new IllegalArgumentException("Cannot have a null model");
    }

    setTitle("ExCELence Animator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(model.getCanvasWidth(), model.getCanvasHeight());
    this.animation = new PlaybackRenderer(model, delay);
    animation.setPreferredSize(new Dimension(model.getCanvasWidth(), model.getCanvasHeight()));
    scrollPane = new JScrollPane(animation);
    this.add(scrollPane, BorderLayout.CENTER);

    statusBar = createStatusBar();

    cp.add(statusBar, BorderLayout.SOUTH);
    this.pack();

    this.setVisible(true);
    animation.playAnimation();
  }

  @Override
  public void setModel(ReadOnlyExCELenceAnimatorModel model) {
    this.model = model;
  }


  @Override
  public void setSpeed(Integer ticksPerSecond) {
    this.delay = 1000 / ticksPerSecond;
  }


}