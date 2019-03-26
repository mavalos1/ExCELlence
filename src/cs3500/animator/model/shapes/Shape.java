package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;

/**
 * This interface specifies the operation of a single 2D shape.
 *
 * <p>
 *   A shape has a name, an initial position, a size of width and height, a color, and a rotation.
 *   A shape can have a list of transitions stored within to tell the model how to animate it.
 *   A shape should have an unique name to avoid being overwritten when added to model.
 * </p>
 * <p>
 *   The animations could only be added on top of each other, thus no overlapping animations,
 *   meaning occuring at the same tick, could happen. Inconsistent transitions, therefore, are
 *   not possible. If a shape want to stay unchanged for an amount of time, it needs to add a
 *   default transition of such amount of time.
 * </p>
 */
public interface Shape {

  /**
   * Get the type of shape.
   * @return the type of shape
   */
  String getShapeType();

  /**
   * Get the shape name.
   * @return the name of the shape
   */
  String getName();

  /**
   * Get the shape's position.
   * @return
   */
  Position2D getPosition();

  /**
   * Get the model's size.
   * @return
   */
  Size getSize();

  /**
   * Get the model's color.
   * @return
   */
  Color getColor();

  /**
   * Add new transitions(s) to the shape.
   * @param tr the transitions
   */
  void addTransition(Transition... tr);

  /**
   * Advance the shape's state to the next tick.
   * @param currentTick the current tick
   */
  void tick(int currentTick);

  /**
   * Returns whether the shape can still animate.
   * @param currentTick the current tick
   * @return
   */
  boolean canTick(int currentTick);

  /**
   * Provide the method to render the shape into an SVG-style code.
   * @param tickMS the ticks per millisecond
   * @return
   */
  String toSVG(int tickMS);

  /**
   * Reset the transition list.
   */
  void reset();
}
