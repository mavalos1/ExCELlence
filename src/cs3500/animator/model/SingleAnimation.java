package cs3500.animator.model;

import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing a single animation. The model stores a list of shapes, each of which
 * contains animation instruction for how they themselves need to animate. The model in turns
 * calls these changes to each shape every tick and store the result into an outputLog.
 */
public class SingleAnimation implements AnimationModel {
  private int currentTick;
  private List<Shape> shapes;

  /**
   * Initialize the animation object with a number of shapes to animate
   * @param shapes
   * @throws IllegalArgumentException when shape list to add is null or empty
   */
  public SingleAnimation(Shape... shapes) throws IllegalArgumentException {
    currentTick = 0;
    this.shapes = new ArrayList<>();
    this.addShape(shapes);
  }

  /**
   * Initialize the animation object with a number of shapes to animate
   * @param shapes
   * @throws IllegalArgumentException when shape list to add is null or empty
   */
  public SingleAnimation(List<Shape> shapes) throws IllegalArgumentException {
    if (shapes == null || shapes.isEmpty()) {
      throw new IllegalArgumentException("Invalid initial shape list");
    }

    currentTick = 0;
    this.shapes = shapes;
  }

  /**
   * Advance the model to the next tick
   */
  public void tick() {
    for (Shape shape : shapes) {
      if (shape.hasTransition()) {
        shape.tick();
      }
    }

    currentTick++;
  }

  /**
   * Add a new shape(s) to the animation model.
   * @param moreShapes the shapes to be added
   * @throws IllegalArgumentException when shape list to add is invalid or a shape with the same
   * name already exists
   */
  public void addShape(Shape... moreShapes) throws IllegalArgumentException {
    if (moreShapes == null || moreShapes.length <= 0) {
      throw new IllegalArgumentException("Invalid shapes to add to animation");
    }

    for (Shape s : moreShapes) {
      if (s == null) {
        throw new IllegalArgumentException("Invalid null shape");
      }

      if (this.shapes != null && !this.shapes.isEmpty()) {
        for (Shape inS : this.shapes) {
          if (inS.getName().equals(s.getName())) {
            throw new IllegalArgumentException("Shape with the same name already exists");
          }
        }
      }

      this.shapes.add(s);
    }
  }

  /**
   * Retrieve the current state of a shape in a model by its name.
   * @param name
   * @throws IllegalArgumentException when the name is invalid or no shape of such name exists
   */
  public Shape getShape(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Invalid null shape name");
    }

    for (Shape inS : this.shapes) {
      if (inS.getName().equals(name)) {
        return inS;
      }
    }

    throw new IllegalArgumentException("No shape with such name exists");
  }

  /**
   * Retrieve the current state of all shapes in the model.
   * @return all shapes in the model
   */
  public List<Shape> getAllShapes() {
    return this.shapes;
  }

  /**
   * Remove a shape(s) of a name from the animation model.
   * @param name the name of the shape to be removed
   * @throws IllegalArgumentException when the name is invalid or no shape of such name exists
   */
  public void removeShape(String name) throws IllegalArgumentException {
    Shape rmvShape = this.getShape(name);
    this.shapes.remove(rmvShape);
  }

  /**
   * Add a new animation(s) to a shape already in a model.
   * @param name the name of the shape to add the animation to
   * @param trList the transitions to add
   * @throws IllegalArgumentException when the name is invalid or not found, or when the
   * transition list is invalid or empty
   */
  public void addAnimation(String name, Transition... trList) throws IllegalArgumentException{
    if (trList == null && trList.length <= 0) {
      throw new IllegalArgumentException("Invalid transition list to add");
    }

    Shape inS = this.getShape(name);

    for (Transition t : trList) {
      if (t == null) {
        throw new IllegalArgumentException("Invalid null transition");
      }

      inS.addTransition(t);
    }
  }

  /**
   * Remove the last added animation(s) from a shape already in a model.
   * @param name the name of the shape to remove the animation from
   * @throws IllegalArgumentException when the name is invalid or not found
   */
  public void popAnimation(String name) throws IllegalArgumentException{
    Shape inS = this.getShape(name);
    inS.popTransition();
  }

  /**
   * Get the animation list of a shape already in a model.
   * @param name the name of the shape to get the animation list
   * @throws IllegalArgumentException when the name is invalid or not found
   */
  public List<Transition> getAnimationList(String name) throws IllegalArgumentException {
    return this.getShape(name).getTransitionList();
  }

  /**
   * Get the current tick number
   * @return
   */
  public int getCurrentTick() {
    return this.currentTick;
  }
}
