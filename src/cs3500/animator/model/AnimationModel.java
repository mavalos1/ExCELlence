package cs3500.animator.model;

import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Shape;

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
 *   The transitions added to the model are added on top of a list, thus overlapping transitions
 *   are not a concern. The animations of a particular shape could be retrieved, added, and
 *   popped accordingly.
 * </p>
 */
public interface AnimationModel {
  /**
   * Advance the model to the next tick
   */
  void tick();

  /**
   * Retrieve the current state of a shape in a model by its name.
   * @param name the name of the shape to be retrieved
   * @return a shape with the name requested
   */
  Shape getShape(String name);

  /**
   * Retrieve the current state of all shapes in the model.
   * @return all shapes in the model
   */
  List<Shape> getAllShapes();


  /**
   * Add a new shape(s) to the animation model.
   * @param s the shapes to be added
   */
  void addShape(Shape... s);

  /**
   * Remove a shape(s) of a name from the animation model.
   * @param name the name of the shape to be removed
   */
  void removeShape(String name);

  /**
   * Add a new animation(s) to a shape already in a model.
   * @param name the name of the shape to add the animation to
   * @param t the transitions to add
   */
  void addAnimation(String name, Transition... t);

  /**
   * Remove the last added animation(s) from a shape already in a model.
   * @param name the name of the shape to remove the animation from
   */
  void popAnimation(String name);

  /**
   * Get the animation list of a shape already in a model.
   * @param name the name of the shape to get the animation list
   * @return the list of animations of a shape
   */
  List<Transition> getAnimationList(String name);

  /**
   * Get the current tick number
   * @return
   */
  int getCurrentTick();
}
