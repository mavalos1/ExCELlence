package cs3500.ExCELlence.model;

import cs3500.ExCELlence.model.transitions.Transition;import java.util.Stack;

/**
 * This interface specifies the operation of a single 2D animation.
 * <p>
 *   A 2D animation is characterized by a shape of size(a,b), at a position(x,y), and
 *   a transition of such shape over a period of time
 * </p>
 */
public interface AnimationModel {
  /**
   * Get the current position of the animation's shape at a specific time.
   */
  Position2D getPosition(int time);

  /**
   * Get the animation's shape at a specific time.
   */
  Shape getShape(int time);

  /**
   * Get the shape's color at a specific time.
   */
  Color getColor(int time);

  /**
   * Get the shape's width at a specific time.
   */
  double getWidth(int time);

  /**
   * Get the shape's height at a specific time.
   */
  double getHeight(int time);

  /**
   * Get the shape's rotation at a specific time.
   */
  double getRotation(int time);

  /**
   * Set the position of the animation's shape at a specific time.
   */
  void setPosition(Position2D p, int time);

  /**
   * Set the animation's shape at a specific time.
   */
  void setShape(Shape s, int time);

  /**
   * Set the shape's color at a specific time.
   */
  void setColor(Color c, int time);

  /**
   * Set the shape's width at a specific time.
   */
  void setWidth(double w, int time);

  /**
   * Set the shape's height at a specific time.
   */
  void setHeight(double h, int time);

  /**
   * Set the shape's rotation at a specific time.
   */
  void setRotation(double r, int time);

  /**
   * Get the animation transition list.
   */
  Stack<Transition> getTransitionList();

  /**
   * Add an animation transition to the list.
   */
  void addTransition(Transition t);

  /**
   * Undo the last transition from the list.
   */
  void popTransition();
}
