package cs3500.animator.provider.view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SpinnerModel;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;


//This import was changed to accomodate for ReadOnlyExCELenceAnimatorModel location in provider.view
import cs3500.animator.provider.view.ReadOnlyExCELenceAnimatorModel;
import cs3500.animator.provider.view.IShape;
import cs3500.animator.model.shapes.Shape2D;

/**
 * This class represents a concrete implementation of an editor view of an animation. An editor view
 * allows users to edit an animation.
 */
public class EditorView extends JFrame implements IActionableView {

  public static final String DECLARE_SHAPE_ACTION = "Declare Shape";
  public static final String PLAY_PAUSE_ACTION = "Play Pause";
  public static final String SELECT_SHAPE_ACTION = "Select Shape";
  public static final String DELETE_SHAPE_ACTION = "Delete Shape";
  public static final String SELECT_KEYTICK_ACTION = "Select Keytick";
  public static final String ADD_KEYFRAME_ACTION = "Add Keyframe";
  public static final String DELETE_KEYFRAME_ACTION = "Delete Keyframe";
  public static final String EDIT_KEYFRAME_ACTION = "Edit Keyframe";


  PlaybackRenderer animation;
  ReadOnlyExCELenceAnimatorModel model;
  boolean playing;

  JPanel controlsPanel;
  JPanel loopPanel;
  JButton playpauseButton;
  JButton restartButton;
  JRadioButton loopButoon;
  JSpinner tickSpinner;

  JPanel declarePanel;
  JTextField newShapeName;
  JComboBox<Shape2D.ShapeType> typeDropdown;
  JButton declareShapeButton;

  JPanel shapesPanel;
  JComboBox<String> namesDropdown;
  JButton deleteShapeButton;

  JPanel keyframesPanel;
  JComboBox<Integer> keyTicksDropdown;
  JSpinner newKeytick;
  JButton createKeytickButton;
  JButton deleteKeytickButton;

  ShapeEditorView shapeEditor;
  JButton editKeyframeButton;

  public EditorView(ReadOnlyExCELenceAnimatorModel model) {
    super();
    this.model = model;
    this.playing = false;
    this.animation = new PlaybackRenderer(model);

    JLabel loopLabel = new JLabel("Loop");
    this.loopButoon = new JRadioButton("on", false);
    this.loopPanel = new JPanel();
    loopPanel.setLayout(new BoxLayout(loopPanel, BoxLayout.Y_AXIS));
    loopPanel.add(loopLabel);
    loopPanel.add(loopButoon);

    this.controlsPanel = new JPanel(new FlowLayout());
    this.playpauseButton = new JButton(this.getPlayPauseText());
    this.restartButton = new JButton("Restart");
    SpinnerModel tickRateModel = new SpinnerNumberModel(1, 1, 120, 1);
    this.tickSpinner = new JSpinner(tickRateModel);

    this.declarePanel = new JPanel(new FlowLayout());
    this.newShapeName = new JFormattedTextField();
    this.declareShapeButton = new JButton("Declare Shape");
    this.typeDropdown = new JComboBox<>();
    this.typeDropdown.addItem(Shape2D.ShapeType.RECTANGLE);
    this.typeDropdown.addItem(Shape2D.ShapeType.ELLIPSE);

    this.shapesPanel = new JPanel(new FlowLayout());
    this.namesDropdown = new JComboBox<>();
    this.namesDropdown.setActionCommand(SELECT_SHAPE_ACTION);
    this.deleteShapeButton = new JButton("Delete Shape");
    this.deleteShapeButton.setActionCommand(DELETE_SHAPE_ACTION);

    this.keyframesPanel = new JPanel(new FlowLayout());
    SpinnerModel keyTickModel = new SpinnerNumberModel(0, 0, null, 1);
    this.newKeytick = new JSpinner(keyTickModel);
    this.newKeytick.setPreferredSize(new Dimension(60, 20));

    this.createKeytickButton = new JButton("Add Keyframe");
    this.createKeytickButton.setActionCommand(ADD_KEYFRAME_ACTION);

    this.keyTicksDropdown = new JComboBox<>();
    this.keyTicksDropdown.setActionCommand(SELECT_KEYTICK_ACTION);

    this.deleteKeytickButton = new JButton("Delete Keyframe");
    this.deleteKeytickButton.setActionCommand(DELETE_KEYFRAME_ACTION);

    this.shapeEditor = new ShapeEditorView();
    this.editKeyframeButton = new JButton("Set keyframe");
    this.editKeyframeButton.setActionCommand(EDIT_KEYFRAME_ACTION);
  }

  @Override
  public void showView() {
    this.setTitle("ExCELence Animator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.playpauseButton.setActionCommand(PLAY_PAUSE_ACTION);
    this.playpauseButton.addActionListener((ActionEvent e) -> {
      if (this.playing) {
        this.animation.pauseAnimation();
      } else {
        this.animation.playAnimation();
      }
      this.playing = !this.playing;
      this.playpauseButton.setText(this.getPlayPauseText());
    });
    this.playpauseButton.setPreferredSize(new Dimension(60, 20));

    this.restartButton.addActionListener((ActionEvent e) -> {
      this.animation.restartAnimation();
    });

    this.loopButoon.addActionListener((ActionEvent e) -> {
      if(loopButoon.isSelected()) {
        this.animation.setLoop(true);
      } else {
        this.animation.setLoop(false);
      }
    });

    this.tickSpinner.addChangeListener((ChangeEvent cl) -> {
      SpinnerModel numberModel = this.tickSpinner.getModel();
      if (numberModel instanceof SpinnerNumberModel) {
        Integer ticksPerSecond = ((SpinnerNumberModel) numberModel).getNumber().intValue();
        this.setSpeed(ticksPerSecond);
      }
    });

    this.newShapeName.setPreferredSize(new Dimension(60, 20));

    this.declareShapeButton.setActionCommand(DECLARE_SHAPE_ACTION);

    this.controlsPanel.add(this.playpauseButton);
    this.controlsPanel.add(this.restartButton);
    this.controlsPanel.add(this.loopPanel);
    this.controlsPanel.add(this.tickSpinner);

    this.declarePanel.setBorder(BorderFactory.createTitledBorder("New Shape"));
    this.declarePanel.add(this.newShapeName);
    this.declarePanel.add(this.declareShapeButton);
    this.declarePanel.add(this.typeDropdown);

    this.shapesPanel.setBorder(BorderFactory.createTitledBorder("Existing Shapes"));
    this.shapesPanel.add(this.namesDropdown);
    this.shapesPanel.add(this.deleteShapeButton);

    this.keyframesPanel.setBorder(BorderFactory.createTitledBorder("Keyframes"));
    this.keyframesPanel.add(this.keyTicksDropdown);
    this.keyframesPanel.add(this.deleteKeytickButton);
    this.keyframesPanel.add(this.createKeytickButton);
    this.keyframesPanel.add(this.newKeytick);

    JPanel leftSide = new JPanel();
    leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
    leftSide.add(animation);
    leftSide.add(controlsPanel);

    JPanel rightSide = new JPanel();
    rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
    rightSide.add(declarePanel);
    rightSide.add(shapesPanel);
    rightSide.add(keyframesPanel);
    rightSide.add(this.shapeEditor);
    rightSide.add(this.editKeyframeButton);

    JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    container.add(leftSide);
    container.add(rightSide);

    JScrollPane scrollPane = new JScrollPane(container);
    this.add(scrollPane);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void setModel(ReadOnlyExCELenceAnimatorModel model) {
    this.model = model;
    this.animation.setModel(model);
  }

  @Override
  public void setSpeed(Integer ticksPerSecond) {
    if (ticksPerSecond < 1) {
      throw new IllegalArgumentException("ticks per second cannot be less than 1");
    } else {
      this.animation.setSpeed(ticksPerSecond);
      this.tickSpinner.setValue(ticksPerSecond);
    }
  }

  @Override
  public void setListener(ActionListener listener) {
    this.playpauseButton.addActionListener(listener);
    this.restartButton.addActionListener(listener);
    this.loopButoon.addActionListener(listener);
    this.declareShapeButton.addActionListener(listener);
    this.createKeytickButton.addActionListener(listener);
    this.deleteKeytickButton.addActionListener(listener);
    this.deleteShapeButton.addActionListener(listener);
    this.editKeyframeButton.addActionListener(listener);
    this.keyTicksDropdown.addActionListener(listener);
    this.namesDropdown.addActionListener(listener);
  }

  /**
   * Side effect: clears the text input
   * @return The String name of the new shape to create
   */
  public String pullNewShapeName() {
    String result = this.newShapeName.getText();
    this.newShapeName.setText("");
    return result;
  }

  /**
   * @return The ShapeType of the new shape to create
   */
  public Shape2D.ShapeType pullNewShapeType() {
    int selectedIndex = this.typeDropdown.getSelectedIndex();
    return this.typeDropdown.getItemAt(selectedIndex);
  }

  /**
   * @return Get the tick of the new keyframe to create
   */
  public Integer getNewKeyframeTick() {
    SpinnerModel numberModel = this.newKeytick.getModel();
    if (numberModel instanceof SpinnerNumberModel) {
      Integer newTick = ((SpinnerNumberModel) numberModel).getNumber().intValue();
      return newTick;
    }
    throw new IllegalStateException("New Keyframe value was not an integer");
  }

  /**
   * @param names Set the displayed list of shape names
   */
  public void setShapeNames(ArrayList<String> names) {
    ComboBoxModel cbm = new DefaultComboBoxModel(names.toArray());
    this.namesDropdown.setModel(cbm);
  }

  /**
   * @return the currently selected String name
   */
  public String getSelectedShapeName() {
    int selectedIndex = this.namesDropdown.getSelectedIndex();
    return this.namesDropdown.getItemAt(selectedIndex);
  }

  /**
   * To see which shape this tick applies to, use getSelectedShapeName()
   * @return the currently selected tick for a particular shape
   */
  public Integer getSelectedKeytick() {
    int selectedIndex = this.keyTicksDropdown.getSelectedIndex();
    return this.keyTicksDropdown.getItemAt(selectedIndex);
  }

  /**
   * The ticks displayed should match with the shape displayed
   * @param keyticks the list of ticks to display
   */
  public void setKeyTicks(ArrayList<Integer> keyticks) {
    ComboBoxModel cbm = new DefaultComboBoxModel(keyticks.toArray());
    this.keyTicksDropdown.setModel(cbm);
  }

  /**
   * @return the shape constructed in the view
   */
  public IShape getShapeValue() {
    return this.shapeEditor.getValue();
  }

  /**
   * @param shape the shape to update the view to
   */
  public void setShapeEditorValue(IShape shape) {
    this.shapeEditor.setValue(shape);
  }

  /**
   * @param enabled true to disable shape edits, false otherwise
   */
  public void setShapeEditsEnabled(boolean enabled) {
    this.shapeEditor.setEnabled(enabled);
    this.editKeyframeButton.setEnabled(enabled);
  }

  private String getPlayPauseText() {
    return this.playing ? "️Pause" : "Play";
  }

}