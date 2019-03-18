package cs3500.nguyenmayeux.model;

import cs3500.nguyenmayeux.model.helper.Transition;
import cs3500.nguyenmayeux.model.shapes.Shape;

import java.util.List;

/**
 * This interface specifies the operation of a single 2D animation.
 * <p>
 *   A 2D animation is characterized by a shape of size(a,b), at a position(x,y), and
 *   a transition of such shape over a period of time.
 *   The model allows user to add a shape to the model addShape(). The shape should have the
 *   desired list of animations, if any is needed, loaded within it.
 *   The model stores these shapes by the order they are put into the model, which can also be
 *   retrieved, modified, removed by the unique name of each shape.
 *   The model allows user to read a string output of timeline of the model with the position and
 *   size of each shape over time by parseAllOutputs()
 *   The
 * </p>
 */
public interface AnimationModel {
  /**
   * Animate the shape contained in the animation model.
   */
  void animate();

  /**
   * Retrieve the current state of a shape in a model by its name.
   */
  Shape getShape(String name);

  /**
   * Add a new shape(s) to the animation model.
   */
  void addShape(Shape... s);

  /**
   * Remove a shape(s) of a name from the animation model.
   */
  void removeShape(String name);

  /**
   * Add a new animation(s) to a shape already in a model.
   */
  void addAnimation(String name, Transition... t);

  /**
   * Remove the last added animation(s) from a shape already in a model.
   */
  void popAnimation(String name);

  /**
   * Get the animation list of a shape already in a model.
   */
  List<Transition> getAnimationList(String name);

  /**
   * Return the string output of all shapes in the animation.
   */
  String parseAllOutputs();
}
