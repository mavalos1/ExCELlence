package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;

import java.awt.*;
import java.util.List;

/**
 * This interface specifies the operation of a single 2D shape.
 * <p>
 *   A 2D shape is characterized by a boundary box of size (w,h)
 *   at a r degree rotation
 * </p>
 */
public interface Shape {

  /**
   * Increment the state of the shape by one tick.
   */
  void tick();

  /**
   * Get whether or not the shape has any transitions left to be played.
   * @return whether or not the shape has any transitions left to be played.
   */
  boolean hasTransition();

  /**
   * Add a new transition to the shape model.
   */
  void addTransition(Transition t);

  /**
   * Pop the last added transition from the shape model.
   */
  void popTransition();

  /**
   * Get the shape's transition list.
   * @return r
   */
  List<Transition> getTransitionList();

  /**
   * Get the shape's name.
   * @return p
   */
  String getName();

  /**
   * Set the shape's name.
   * @param name
   */
  void setName(String name);

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
   * Get the shape's size.
   * @return s
   */
  Size getSize();

  /**
   * Set the shape's size.
   * @param s
   */
  void setSize(Size s);

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

  /**
   * Get the shape type
   * @return
   */
  String getShapeType();
}
