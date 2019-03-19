package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the implementation of a class that could be rendered as a rectangle to the
 * view. The shape allows to add new Transition(s) to the shape, advance itself to the next tick,
 * and provide method how to render it.
 */
public class Rectangle implements Shape {
  protected String name;
  protected Position2D position;
  protected Size size;
  protected Color color;
  protected List<Transition> transitions;

  /**
   * Initialize the rectangle model.
   * @param name
   * @param x
   * @param y
   * @param w
   * @param h
   * @param r
   * @param g
   * @param b
   */
  public Rectangle(String name, int x, int y, int w, int h, int r, int g, int b) {
    this.name = name;
    this.position = new Position2D(x, y);
    this.size = new Size(w, h);
    this.color = new Color(r, g, b);
    this.transitions = new ArrayList<>();
  }

  /**
   * Initialize the rectangle model with a name.
   * @param name
   */
  public Rectangle(String name) {
    this(name, 0, 0, 0, 0, 0, 0, 0);
  }

  /**
   * Get the shape name.
   * @return
   */
  public String getName() { return name; }

  /**
   * Get the shape's position.
   * @return
   */
  public Position2D getPosition() { return position; }

  /**
   * Get the model's size.
   * @return
   */
  public Size getSize() { return size; }

  /**
   * Get the model's color.
   * @return
   */
  public Color getColor() { return color; }

  /**
   * Add new transitions(s) to the shape.
   * @param tr
   */
  public void addTransition(Transition... tr) {
    Objects.requireNonNull(tr, "Must have a valid transition list to add");

    if (transitions.isEmpty()) {
      this.position = new Position2D(tr[0].x1, tr[0].y1);
      this.size = new Size(tr[0].w1, tr[0].h1);
      this.color = new Color(tr[0].r1, tr[0].g1, tr[0].b1);
    }

    for (Transition t : tr) {
      for (Transition inT : transitions) {
        if ((inT.beginTime > t.beginTime && inT.beginTime < t.endTime) ||
            (inT.endTime > t.beginTime && inT.endTime < t.endTime)) {
          throw new IllegalArgumentException("Invalid transition time frame");
        }
      }

      if (!transitions.isEmpty()) {
        Transition latestTr = transitions.get(transitions.size() - 1);
        if ((Math.abs(latestTr.x2 - t.x1) > 0.01) || (Math.abs(latestTr.y2 - t.y1) > 0.01)
            || (Math.abs(latestTr.w2 - t.w1) > 0.01) || (Math.abs(latestTr.h2 - t.h1) > 0.01)
            || (Math.abs(latestTr.r2 - t.r1) > 0.01) || (Math.abs(latestTr.g2 - t.g1) > 0.01)
            || (Math.abs(latestTr.b2 - t.b1) > 0.01)) {
          throw new IllegalArgumentException("Invalid transition state");
        }

        if (latestTr.endTime != t.beginTime) {
          throw new IllegalArgumentException("Invalid transition begin-end time");
        }
      }

      transitions.add(t);
    }
  }

  /**
   * Advance the shape's state to the next tick.
   * @param currentTick
   */
  public void tick(int currentTick) {
    if (transitions.isEmpty()) {
      return;
    }

    Transition t = transitions.get(0);

    if (currentTick < t.beginTime || t.duration == 0) {
      return;
    }

    if (currentTick == t.beginTime) {
      position = new Position2D(t.x1, t.y1);
      size = new Size(t.w1, t.h1);
      color = new Color(t.r1, t.g1, t.b1);
    } else {
      position = new Position2D(
          position.getX() + (t.x2 - t.x1) / (double) t.duration,
          position.getY() + (t.y2 - t.y1) / (double) t.duration
      );
      size = new Size(
          size.getW() + (t.w2 - t.w1) / (double) t.duration,
          size.getH() + (t.h2 - t.h1) / (double) t.duration
      );
      color = new Color(
          color.getR() + (t.r2 - t.r1) / t.duration,
          color.getG() + (t.g2 - t.g1) / t.duration,
          color.getB() +(t.b2 - t.b1) / t.duration
      );

      if (currentTick >= t.endTime) {
        transitions.remove(0);
      }
    }
  }

  /**
   * Returns whether the shape can still animate.
   * @param currentTick
   * @return
   */
  public boolean canTick(int currentTick) {
    if (transitions.isEmpty()) {
      return false;
    }

    return true;
  }

  /**
   * Provide the method to render the shape into an SVG-style code.
   * @param tickMS
   * @return
   */
  public String toSVG(int tickMS) {
    StringBuilder toSVG = new StringBuilder();
    toSVG.append(String.format("\n<rect id=\"%s\" x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" " +
            "height=\"%.2f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >",
        name, position.getX(), position.getY(),
        size.getW(), size.getH(), color.getR(), color.getG(), color.getB()));

    for (Transition t : transitions) {
      toSVG.append(transitionToSVG(t, tickMS));
    }

    toSVG.append("\n</rect>");

    return toSVG.toString();
  }

  /**
   * Provide the method to render the shape's transitions to an SVG-style code.
   * @param t
   * @param tickMS
   * @return
   */
  protected String transitionToSVG(Transition t, int tickMS) {
    StringBuilder toSVG = new StringBuilder();

    if (t.x1 != t.x2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" />", t.beginTime * tickMS,
          t.duration * tickMS, t.x1, t.x2));
    }

    if (t.y1 != t.y2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" />", t.beginTime * tickMS,
          t.duration * tickMS, t.y1, t.y2));
    }

    if (t.w1 != t.w2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"width\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t.beginTime * tickMS, t.duration * tickMS, t.w1, t.w2));
    }

    if (t.h1 != t.h2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"height\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t.beginTime * tickMS, t.duration * tickMS, t.h1, t.h2));
    }

    if (t.r1 != t.r2 || t.g1 != t.g2 || t.b1 != t.b2) {
      toSVG.append(String.format("\n\t<animate attributeName=\"fill\" begin=\"%dms\" dur=\"%dms\" " +
              "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\"/>",
          t.beginTime * tickMS, t.duration * tickMS, t.r1, t.g1, t.b1, t.r2, t.g2, t.b2));
    }

    return toSVG.toString();
  }

  @Override
  public String getShapeType() {
    return "rectangle";
  }
}
