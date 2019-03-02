package cs3500.ExCELlence.model;

import cs3500.ExCELlence.model.shapes.Oval;
import cs3500.ExCELlence.model.shapes.Rectangle;
import cs3500.ExCELlence.model.shapes.Shape;
import cs3500.ExCELlence.model.shapes.Triangle;
import cs3500.ExCELlence.model.transitions.Transition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

public class SingleAnimation implements AnimationModel {
  private Map<UUID, StringBuilder> outputLog;
  private int currentTick;
  private List<Shape> shapes;

  /**
   * Initialize the animation object with a list of shapes to animate
   * @param shapes
   */
  public SingleAnimation(List<Shape> shapes) {
    if (shapes.isEmpty() || shapes == null) {
      throw new IllegalArgumentException("Invalid animation's shape list");
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

  private void parseTickOutput(Shape s) {
    StringBuilder output = new StringBuilder();
    output.append("motion " + s.getName() + "\t");
    output.append(currentTick + " " + s.getPosition().getX() + " " + s.getPosition().getY() + " ");
    output.append(s.getWidth() + " " + s.getHeight() + " ");
    output.append(s.getColor().getR() + " " + s.getColor().getG() + " " + s.getColor().getB() + "\n");

    if (outputLog.get(s.getId()) == null) {
      outputLog.put(s.getId(), output);
    } else {
      outputLog.get(s.getId()).append(output);
    }
  }

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

    output.append(outputLog.get(s.getId()));

    return output.toString();
  }

  public String parseAllOutputs() {
    StringBuilder output = new StringBuilder();

    for (Shape s : shapes) {
      output.append(parseAnimationOutput(s) + "\n");
    }

    return output.toString();
  }
}
