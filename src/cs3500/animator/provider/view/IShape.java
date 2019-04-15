package cs3500.animator.provider.view;

import cs3500.animator.model.shapes.attribute.RBGcolor;
import cs3500.animator.model.shapes.attribute.Dimension2D;
import cs3500.animator.model.shapes.attribute.Position2D;

/**
 * This interface represents the operations offered by a IShape.
 */
public interface IShape {


  /**
   * Gets the position of a shape.
   *
   * @return returns the position
   */
  Position2D getPosition();

  /**
   * Gets the dimension of a shape.
   *
   * @return returns the dimension
   */
  Dimension2D getDimension();

  /**
   * Gets the color of a shape.
   *
   * @return returns the color
   */
  RBGcolor getColor();

  /**
   * Sets the position of a shape.
   *
   * @param p the position
   */
  void setPosition(Position2D p);

  /**
   * Sets the dimension of a shape.
   *
   * @param d the dimensiom
   */
  void setDimension(Dimension2D d);

  /**
   * Sets the color of a shape.
   *
   * @param c the color
   */
  void setColor(RBGcolor c);

  Shape2D.ShapeType getType();

  /**
   * Returns a string representation of a IShape.
   *
   * @return the position, dimension, and color of a shape as a string
   */
  String toString();

  /**
   * Creates a copy of a shape.
   *
   * @return a copy of the shape
   */
  IShape copy();

}
