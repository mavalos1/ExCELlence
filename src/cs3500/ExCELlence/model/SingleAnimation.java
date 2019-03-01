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

public class SingleAnimation implements AnimationModel {
  private Map<String, StringBuilder> outputLog;
  private int currentTick;
  private Shape[] shapes;

  public SingleAnimation(Shape[] shapes) {
    outputLog = new HashMap<>();
    currentTick = 0;
    this.shapes = shapes;
  }

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

  public void parseTickOutput(Shape s) {
    StringBuilder output = new StringBuilder();
    output.append("motion " + s.getName() + "\t");
    output.append(currentTick + " " + s.getPosition().getX() + " " + s.getPosition().getY() + " ");
    output.append(s.getWidth() + " " + s.getHeight() + " ");
    output.append(s.getColor().getR() + " " + s.getColor().getG() + " " + s.getColor().getB());

    outputLog.get(s.getName()).append(output);
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

    output.append(outputLog.get(s.getName()));

    return output.toString();
  }
}
