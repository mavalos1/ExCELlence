package cs3500.ExCELlence.model.shapes;

import cs3500.ExCELlence.model.Color;
import cs3500.ExCELlence.model.Position2D;

/**
 * This interface specifies the operation of a single 2D shape.
 * <p>
 *   A 2D shape is characterized by a boundary box of size (w,h)
 *   at a r degree rotation
 * </p>
 */
public interface Shape {
  /**
   * Get the shape's position.
   * @return p
   */
  Position2D getPosition();

  /**
   * Set the shape's position.
   * @param p
   */
  void setPosition(Position2D p);

  /**
   * Get the shape's width.
   * @return w
   */
  double getWidth();

  /**
   * Set the shape's height.
   * @param w
   */
  void setWidth(double w);

  /**
   * Get the shape's height.
   * @return h
   */
  double getHeight();

  /**
   * Set the shape's height.
   * @param h
   */
  void setHeight(double h);

  /**
   * Get the shape's color.
   * @return c
   */
  Color getColor();

  /**
   * Set the shape's color.
   * @param c
   */
  void setColor(Color c);

  /**
   * Get the shape's rotation.
   * @return r
   */
  double getRotation();

  /**
   * Set the shape's color.
   * @param rotation
   */
  void setRotation(double rotation);

  void draw();
}
