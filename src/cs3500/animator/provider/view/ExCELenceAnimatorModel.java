package cs3500.animator.provider.view;

import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shapes.Shape2D;

/**
 * This interface represents all the operations offered by the ExCELenceAnimatorModel. One object of
 * of the model is one animation. An animation contains a canvas which encloses the shapes that move
 * based on the motions that are applied to it.
 */
public interface ExCELenceAnimatorModel extends ReadOnlyExCELenceAnimatorModel {

  /**
   * Adds an shape to the animation based on its name.
   *
   * @param name The name identifier of the shape to add to the animation
   * @param type The type of the shape
   * @throws IllegalArgumentException if a shape has already been declared with this name
   */
  void declareShape(String name, Shape2D.ShapeType type);

  /**
   * Removes the shape matching the given name.
   *
   * @param name The name of the entity to remove
   * @throws IllegalArgumentException if the the shape does not exist.
   */
  void removeShape(String name);

  /**
   * Adds a motion to an animation based on the given name of the shape the motion is associated
   * with.
   *
   * @param name   name of the shape
   * @param motion motion that will be applied to a shape
   * @throws IllegalArgumentException if the shape does no exist or if the given motion overlaps
   *                                  with the time interval of a different motion
   */
  void addMotion(String name, Motion motion);

  /**
   * Adds a keyframe to an animation with a given name, at a given tick.
   * Attempts to tween the shape at the tick.
   * If no tweening is possible, shape is initialized with dummy values.
   * @param name  the name of the keyframe
   * @param tick  the tick of the keyframe
   */
  void addKeyframe(String name, Integer tick);

  /**
   * Adds a keyframe to an animation with a given name, at a given tick.
   *
   * @param name  the name of the keyframe
   * @param tick  the tick of the keyframe
   * @param shape the shape to define for this keyframe
   */
  void addKeyframe(String name, Integer tick, IShape shape);

  /**
   * Removes a keyframe from an animation for the shape with a given name at a given tick.
   *
   * @param name  the name of the keyframe
   * @param tick  the tick of the keyframe
   */
  void removeKeyframe(String name, Integer tick);

  /**
   * Sets the x-coordinate of the top-left corner of the canvas.
   *
   * @param canvasOffsetX the x-coordinate of the top-left corner of the canvas
   */
  void setCanvasOffsetX(Integer canvasOffsetX);

  /**
   * Sets the y-coordinate of the top-left corner of the canvas.
   *
   * @param canvasOffsetY the y-coordinate of the top-left corner of the canvas
   */
  void setCanvasOffsetY(Integer canvasOffsetY);

  /**
   * Sets the width of the canvas.
   *
   * @param canvasWidth the width
   */
  void setCanvasWidth(Integer canvasWidth);

  /**
   * Sets the height of the canvas.
   *
   * @param canvasHeight the height
   */
  void setCanvasHeight(Integer canvasHeight);

}
