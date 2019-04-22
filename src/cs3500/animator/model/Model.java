package cs3500.animator.model;

import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Shape;

import java.util.*;

/**
 * A class representing a single animation. The model stores a list of shapes, each of which
 * contains animation instruction for how they themselves need to animate. The model in turns
 * calls these changes to each shape every tick.
 */
public class Model implements AnimationModel {
  private int currentTick;
  private TreeMap<Integer, List<Shape>> shapes;

  /**
   * Initialize the model.
   */
  public Model() {
    currentTick = 0;
    shapes = new TreeMap<>();
  }

  /**
   * Get all the shapes in the model.
   * @return
   */
  public List<Shape> getShapes() {
    List<Shape> sList = new ArrayList<>();
    for (int k : shapes.keySet()) {
      sList.addAll(shapes.get(k));
    }

    return sList;
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

    List<Shape> sList = getShapes();
    for (Shape s : sList) {
      if (s.getName().equals(name)) {
        return s;
      }
    }

    throw new IllegalArgumentException("No shape with such name exists");
  }

  /**
   * Add a new shape to the model.
   * @param layer the layer order
   * @param sh the shapes
   */
  public void addShape(Integer layer, Shape... sh) {
    List<Shape> sList = shapes.get(layer);
    if (sList == null || sList.isEmpty()) {
      sList = new ArrayList<>();
      shapes.put(layer, sList);
    }

    for (Shape s : sh) {
      for (Integer k : shapes.keySet()) {
        List<Shape> iList = shapes.get(k);
        for (Shape iS : iList) {
          if (iS.getName().equals(s.getName())) {
            throw new IllegalArgumentException("Another shape already exists with the same name");
          }
        }
      }

      sList.add(s);
    }
  }

  /**
   * Add a new shape to the model to the bottom layer.
   * @param sh the shapes
   */
  public void addShape(Shape... sh) {
    addShape(0, sh);
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

    List<Shape> sList = getShapes();
    for (Shape s : sList) {
      s.tick(currentTick);
    }
  }

  /**
   * Return whether the model could still animate.
   * @return
   */
  public boolean canTick() {
    List<Shape> sList = getShapes();
    for (Shape s : sList) {
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
    List<Shape> sList = getShapes();
    for (Shape s : sList) {
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
    for (Integer k : shapes.keySet()) {
      shapes.get(k).remove(s);
    }
  }

  /**
   * Advance the model to a certain percentage of frames.
   * @param pct the percent to jump to
   */
  public void jumpToPercent(int pct) {
    int maxTick = 0;
    List<Shape> sList = getShapes();
    for (Shape s : sList) {
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

  /**
   * Remove all shapes in a specific layer.
   * @param layer the layer to remove
   */
  public void removeLayer(Integer layer) {
    shapes.remove(layer);
  }

  /**
   * Add a new empty layer.
   * @param layer the layer to add
   */
  public void addLayer(Integer layer) {
    if (shapes.get(layer) == null) shapes.put(layer, new ArrayList<>());
    else throw new IllegalArgumentException("Layer already exists");
  }

  /**
   * Reorder the layer to a new order.
   * @param oldLayer the old value of the layer
   * @param newLayer the new layer order
   */
  public void reorderLayer(Integer oldLayer, Integer newLayer) {
    List<Shape> sList = shapes.get(oldLayer);
    if (sList == null) {
      throw new IllegalArgumentException("No layer exists with such order");
    }

    if (shapes.get(newLayer) != null) {
      throw new IllegalArgumentException("A layer already exists with such order");
    }

    shapes.remove(oldLayer);
    shapes.put(newLayer, sList);
  }
}
