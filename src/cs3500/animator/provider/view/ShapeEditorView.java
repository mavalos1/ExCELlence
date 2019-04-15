package cs3500.animator.provider.view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.SpinnerModel;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.Shape2D;
import cs3500.animator.model.shapes.attribute.RBGcolor;

/**
 * A view which can display the attributes of an IShape.
 * The attributes (position, size, and color) can be modified using this view.
 * Note: making modifications in this view will NOT actually change the IShape reference provided.
 * To get the updated IShape, use getValue().
 */
public class ShapeEditorView extends JPanel {

  private Color colorValue;
  private Shape2D.ShapeType shapeType;

  private JSpinner shapeWidth;
  private JSpinner shapeHeight;
  private JSpinner shapeXPos;
  private JSpinner shapeYPos;
  private JButton shapeColorButton;

  public ShapeEditorView() {
    this(null);
  }

  public ShapeEditorView(IShape value) {
    super(new FlowLayout());

    SpinnerNumberModel widthModel =
            new SpinnerNumberModel(0, 0, null, 1);
    SpinnerNumberModel heightModel =
            new SpinnerNumberModel(0, 0, null, 1);
    SpinnerNumberModel posXModel =
            new SpinnerNumberModel(0, 0, null, 1);
    SpinnerNumberModel posYModel =
            new SpinnerNumberModel(0, 0, null, 1);

    this.shapeWidth = new JSpinner(widthModel);
    this.shapeWidth.setPreferredSize(new Dimension(60, 20));
    this.shapeHeight = new JSpinner(heightModel);
    this.shapeHeight.setPreferredSize(new Dimension(60, 20));
    this.shapeXPos = new JSpinner(posXModel);
    this.shapeXPos.setPreferredSize(new Dimension(60, 20));
    this.shapeYPos = new JSpinner(posYModel);
    this.shapeYPos.setPreferredSize(new Dimension(60, 20));
    this.shapeColorButton = new JButton("Select Color");

    this.shapeColorButton.addActionListener((ActionEvent e) -> {
      this.colorValue = JColorChooser.showDialog(
              this,
              "Choose Color",
              this.colorValue);

      this.setColorButtonColor(this.colorValue);
    });

    JPanel dimensions = new JPanel(new FlowLayout());
    dimensions.setBorder(BorderFactory.createTitledBorder("Size (w, h)"));
    dimensions.add(this.shapeWidth, BorderLayout.EAST);
    dimensions.add(this.shapeHeight, BorderLayout.EAST);

    JPanel coord = new JPanel(new FlowLayout());
    coord.setBorder(BorderFactory.createTitledBorder("Pos (x, y)"));
    coord.add(this.shapeXPos);
    coord.add(this.shapeYPos);

    this.add(dimensions, BorderLayout.NORTH);
    this.add(coord, BorderLayout.NORTH);
    this.add(this.shapeColorButton, BorderLayout.NORTH);

    this.setBorder(BorderFactory.createTitledBorder("Shape Editor"));

    this.setValue(value);
  }

  public void setValue(IShape value) {
    if (value == null) {
      this.shapeWidth.setValue(0);
      this.shapeHeight.setValue(0);

      this.shapeXPos.setValue(0);
      this.shapeYPos.setValue(0);

      this.colorValue = Color.WHITE;
      this.shapeType = Shape2D.ShapeType.RECTANGLE;

    } else {

      this.shapeWidth.setValue(value.getDimension().getWidth());
      this.shapeHeight.setValue(value.getDimension().getHeight());

      this.shapeXPos.setValue(value.getPosition().getX());
      this.shapeYPos.setValue(value.getPosition().getY());

      int redValue = value.getColor().getRedValue();
      int greenValue = value.getColor().getGreenValue();
      int blueValue = value.getColor().getBlueValue();
      this.colorValue = new Color(redValue, greenValue, blueValue);
      this.setColorButtonColor(this.colorValue);

      this.setShapeType(value.getType());
    }

  }

  public IShape getValue() {
    Integer width = this.getSpinnerValue(this.shapeWidth);
    Integer height = this.getSpinnerValue(this.shapeHeight);
    Integer xPos = this.getSpinnerValue(this.shapeXPos);
    Integer yPos = this.getSpinnerValue(this.shapeYPos);
    RBGcolor col = new RBGcolor(
            this.colorValue.getRed(),
            this.colorValue.getBlue(),
            this.colorValue.getGreen());

    return new Shape2D(
            xPos,
            yPos,
            width,
            height,
            col.getRedValue(),
            col.getBlueValue(),
            col.getGreenValue(),
            this.shapeType);
  }

  public void setShapeType(Shape2D.ShapeType type) {
    this.shapeType = type;
  }

  private void setColorButtonColor(Color color) {
    this.shapeColorButton.setBorder(BorderFactory.createLineBorder(color));
  }

  private Integer getSpinnerValue(JSpinner spinner) {
    SpinnerModel numberModel = spinner.getModel();
    if (numberModel instanceof SpinnerNumberModel) {
      return((SpinnerNumberModel) numberModel).getNumber().intValue();
    }
    throw new IllegalStateException("Spinner is not numeric.");
  }

}
