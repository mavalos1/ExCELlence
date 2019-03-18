package cs3500.nguyenmayeux.model;

import cs3500.nguyenmayeux.model.helper.Transition;
import cs3500.nguyenmayeux.model.shapes.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a single animation. The model stores a list of shapes, each of which
 * contains animation instruction for how they themselves need to animate. The model in turns
 * calls these changes to each shape every tick and store the result into an outputLog.
 */
public class SingleAnimation implements AnimationModel {
  private List<Shape> shapes;
  private Map<String, StringBuilder> outputLog;
  private int currentTick;

  /**
   * Initialize the animation object with a number of shapes to animate
   * @param shapes
   * @throws IllegalArgumentException when shape list to add is null or empty
   */
  public SingleAnimation(Shape... shapes) throws IllegalArgumentException {
    outputLog = new HashMap<>();
    currentTick = 0;
    this.addShape(shapes);
  }

  /**
   * Initialize the animation object with a number of shapes to animate
   * @param shapes
   * @throws IllegalArgumentException when shape list to add is null or empty
   */
  public SingleAnimation(List<Shape> shapes) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("Invalid initial shape list");
    }
    outputLog = new HashMap<>();
    currentTick = 0;
    this.shapes = shapes;
  }

  /**
   * Animate the shape contained in the animation model.
   * The shape themselves contains transition instruction on how to animate each shape.
   * The animation model calls to draw each shape every tick and advances to the next tick.
   * The output log is appended for each specific shape.
   */
  public void animate() {
    boolean shouldPlay = true;

    while (shouldPlay) {
      shouldPlay = false;

      for (Shape shape : shapes) {
        shape.draw();
        shape.tick();
        parseTickOutput(shape);

        if (shape.hasTransition()) {
          shouldPlay = true;
        }
      }

      currentTick++;
    }

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

      for (Shape inS : this.shapes) {
        if (inS.getName().equals(s.getName())) {
          throw new IllegalArgumentException("Shape with the same name already exists");
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
   * Print to log the animation of one shape at a tick
   * @param s
   */
  private void parseTickOutput(Shape s) {
    StringBuilder output = new StringBuilder();
    output.append("motion " + s.getName() + "\t");
    output.append(currentTick + " " + s.getPosition().getX() + " " + s.getPosition().getY() + " ");
    output.append(s.getWidth() + " " + s.getHeight() + " ");
    output.append(s.getColor().getR() + " " + s.getColor().getG() + " " + s.getColor().getB() + "\n");

    if (outputLog.get(s.getName()) == null) {
      outputLog.put(s.getName(), output);
    } else {
      outputLog.get(s.getName()).append(output);
    }
  }

  /**
   * Return the string output of one single shape in the animation.
   */
  public String parseAnimationOutput(Shape s) {
    StringBuilder output = new StringBuilder();
    output.append("shape " + s.getName());

    String sType = "";
    if (s instanceof Rectangle) {
      sType = "rectangle";
    } else if (s instanceof Oval) {
      sType = "oval";
    } else if (s instanceof Triangle) {
      sType = "triangle";
    } else {
      sType = "";
    }

    output.append(" " + sType + "\n");

    output.append(outputLog.get(s.getName()));

    return output.toString();
  }

  /**
   * Return the string output of all shapes in the animation.
   */
  public String parseAllOutputs() {
    StringBuilder output = new StringBuilder();

    for (Shape s : shapes) {
      output.append(parseAnimationOutput(s) + "\n");
    }

    return output.toString();
  }
}
