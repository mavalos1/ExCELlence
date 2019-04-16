package cs3500.animator.provider.view;

import java.util.ArrayList;

import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shapes.Shape2D;

/**
 * This interface represents an animation model whose data can only be accessed but not changed.
 * This model type provides all the "get" methods from the original model interface in order to be
 * used with the views.
 */
public interface ReadOnlyExCELenceAnimatorModel {

  /**
   * Returns the names of all the shapes in the animation.
   *
   * @return an ArrayList of IShape names
   */
  ArrayList<String> getShapes();

  /**
   * Returns a shape at the given time based on the given name.
   *
   * @param name the name of the shape
   * @param tick the time at which the shape will be retrieved
   * @return an IShape at the specified tick
   * @throws IllegalArgumentException if specified tick does not exist
   */
  IShape getShapeAtTick(String name, Integer tick);

  /**
   * Gets all the shapes that exist at the given tick.
   *
   * @param tick the time
   * @return an ArrayList of IShapes at the specified tick
   */
  ArrayList<IShape> getShapesAtTick(Integer tick);

  /**
   * Gets all the keyframe ticks for a specific shape given its name.
   *
   * @param name the name of the shape
   * @return an ArrayList of ticks that are keyframes of a shape
   */
  ArrayList<Integer> getKeyticksForShape(String name);

  /**
   * Gets all the motions for a specific shape based on the given name of the shape.
   *
   * @param name the name of the shape
   * @return an ArrayList of motions that belong to a specified shape
   */
  ArrayList<Motion> getMotionsForShape(String name);

  /**
   * Gets the shape type (rectangle, ellipse, etc) of a shape.
   *
   * @param name the name of the shape
   * @return an Enum representing the shape type
   */
  Shape2D.ShapeType getShapeType(String name);

  /**
   * Gets the width of the canvas.
   *
   * @return an Integer representing the width
   */
  Integer getCanvasWidth();

  /**
   * Gets the height of the canvas.
   *
   * @return an Integer representing the height
   */
  Integer getCanvasHeight();

  /**
   * Gets the x-coordinate of the top-left corner of the canvas.
   *
   * @return an Integer representing the x-coordinate
   */
  Integer getCanvasOffsetX();


  /**
   * Gets the y-coordinate of the top-left corner of the canvas.
   *
   * @return an Integer representing the y-coordinate
   */
  Integer getCanvasOffsetY();

  /**
   * Determine how many ticks long the animation. This is essentially the tick of the last
   * keyframe.
   *
   * @return the length of this animation, in ticks
   */
  Integer getDuration();

}
