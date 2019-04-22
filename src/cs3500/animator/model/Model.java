package cs3500.animator.model;

import cs3500.animator.model.helper.Transition;
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
    if (name == null) {
      throw new  IllegalArgumentException("Invalid null shape name");
    }

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
   * Add a new animation to the end of the shape's transition list.
   * @param name the name of the shape to add the transition to
   * @param t the new transition
   */
  public void addTransition(String name, Transition t) {
    Shape s = getShape(name);
    s.addTransition(t);
  }

  /**
   * Advance the model to the next tick.
   */
  public void tick() {
    if (shapes.isEmpty()) {
      throw new IllegalStateException("No valid shape to tick");
    }

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

  /**
   * Adds a keyframe to the animation.
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   * @return
   */
  public void addKeyFrame(
      String name, int t, int x, int y, int w, int h, int r, int g, int b, int rt) {
    Shape s = getShape(name);
    s.addKeyFrame(t, x, y, w, h, r, g, b, rt);
  }

  /**
   * Delete a keyframe from the animation.
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @return
   */
  public void deleteKeyFrame(String name, int t) {
    Shape s = getShape(name);
    s.deleteKeyFrame(t);
  }

  /**
   * Remove the shape with such name from the model.
   * @param name the name of the shape
   */
  public void removeShape(String name) {
    Shape s = getShape(name);
    shapes.remove(s);
  }

  /**
   * Advance the model to a certain percentage of frames.
   * @param pct the percent to jump to
   */
  public void jumpToPercent(int pct) {
    int maxTick = 0;
    for (Shape s : shapes) {
      while (s.canTick(maxTick)) maxTick++;
    }

    int newTick = maxTick * pct / 100;

    if (currentTick > newTick) {
      reset();
    } else if (currentTick == newTick) {
      return;
    }

    while (currentTick < newTick) tick();
  }
}
