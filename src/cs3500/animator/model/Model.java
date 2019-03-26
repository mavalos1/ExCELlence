package cs3500.animator.model;

import cs3500.animator.model.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a single animation. The model stores a list of shapes, each of which
 * contains animation instruction for how they themselves need to animate. The model in turns
 * calls these changes to each shape every tick.
 */
public class Model implements AnimationModel {
  private int currentTick;
  private List<Shape> shapes;

  /**
   * Initialize the model.
   */
  public Model() {
    currentTick = 0;
    shapes = new ArrayList<>();
  }

  /**
   * Get all the shapes in the model.
   * @return
   */
  public List<Shape> getShapes() {
    return shapes;
  }

  /**
   * Get the shape matching a specified name in the model.
   * @param name the name of the shape
   * @return the shape
   */
  public Shape getShape(String name) throws IllegalArgumentException {
    for (Shape s : shapes) {
      if (s.getName().equals(name)) {
        return s;
      }
    }

    throw new IllegalArgumentException("No shape with such name exists");
  }

  /**
   * Add a new shape to the model.
   * @param sh the shapes
   */
  public void addShape(Shape... sh) {
    for (Shape s : sh) {
      shapes.add(s);
    }
  }

  /**
   * Advance the model to the next tick.
   */
  public void tick() {
    currentTick++;

    for (Shape s : shapes) {
      s.tick(currentTick);
    }
  }

  /**
   * Return whether the model could still animate.
   * @return
   */
  public boolean canTick() {
    for (Shape s : shapes) {
      if (s.canTick(currentTick)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Return the current tick.
   * @return
   */
  public int getCurrentTick() {
    return currentTick;
  }

  /**
   * Reset the model.
   */
  public void reset() {
    currentTick = 0;
    for (Shape s : shapes) {
      s.reset();
    }
  }
}
