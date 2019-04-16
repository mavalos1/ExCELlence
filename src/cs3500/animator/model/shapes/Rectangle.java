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
  protected int currentTransition;
  protected List<Transition> transitions;

  /**
   * Initialize the rectangle model.
   * @param name the name of the shape
   * @param x the x pos
   * @param y the y pos
   * @param w the width
   * @param h the height
   * @param r red
   * @param g green
   * @param b blue
   */
  public Rectangle(String name, int x, int y, int w, int h, int r, int g, int b) {
    this.name = name;
    this.position = new Position2D(x, y);
    this.size = new Size(w, h);
    this.color = new Color(r, g, b);
    this.transitions = new ArrayList<>();
    this.currentTransition = 0;
  }

  /**
   * Initialize the rectangle model with a name.
   * @param name the name of the shape
   */
  public Rectangle(String name) {
    this(name, 0, 0, 0, 0, 0, 0, 0);
  }

  /**
   * Copy constructor.
   * @param s the shape to copy from
   */
  public Rectangle(Shape s) {
    this(s.getName(),
        (int) s.getPosition().getX(), (int) s.getPosition().getY(),
        (int) s.getSize().getW(), (int) s.getSize().getH(),
        (int) s.getColor().getR(), (int) s.getColor().getG(), (int) s.getColor().getB()
    );
  }

  /**
   * Get the shape name.
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * Get the shape's position.
   * @return
   */
  public Position2D getPosition() {
    return position;
  }

  /**
   * Get the model's size.
   * @return
   */
  public Size getSize() {
    return size;
  }

  /**
   * Get the model's color.
   * @return
   */
  public Color getColor() {
    return color;
  }

  /**
   * Add new transitions(s) to the shape.
   * @param tr the transitions to add
   */
  public void addTransition(Transition... tr) {
    Objects.requireNonNull(tr, "Must have a valid transition list to add");

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

      if (t.beginTime == 0) {
        position = new Position2D(t.x1, t.y1);
        size = new Size(t.w1, t.h1);
        color = new Color(t.r1, t.g1, t.b1);
      }

      transitions.add(t);
    }
  }

  /**
   * Advance the shape's state to the next tick.
   * @param currentTick the current tick
   */
  public void tick(int currentTick) {
    if (!canTick(currentTick)) {
      return;
    }

    Transition t = transitions.get(currentTransition);

    if (currentTick == t.beginTime || (t.beginTime == 0 && t.duration == 0)) {
      position = new Position2D(t.x1, t.y1);
      size = new Size(t.w1, t.h1);
      color = new Color(t.r1, t.g1, t.b1);

      if (t.duration == 0) {
        currentTransition++;
        tick(currentTick);
      }
    } else if (currentTick > t.beginTime) {
      position = new Position2D(
          position.getX() + (t.x2 - t.x1) / (double) t.duration,
          position.getY() + (t.y2 - t.y1) / (double) t.duration
      );
      size = new Size(
          size.getW() + (t.w2 - t.w1) / (double) t.duration,
          size.getH() + (t.h2 - t.h1) / (double) t.duration
      );
      color = new Color(
          color.getR() + (t.r2 - t.r1) / (double) t.duration,
          color.getG() + (t.g2 - t.g1) / (double) t.duration,
          color.getB() + (t.b2 - t.b1) / (double) t.duration
      );

      if (currentTick == t.endTime) {
        currentTransition++;
        tick(currentTick);
      }
    }
  }

  /**
   * Returns whether the shape can still animate.
   * @param currentTick the current tick
   * @return
   */
  public boolean canTick(int currentTick) {
    if (transitions.isEmpty()) {
      return false;
    }

    if (currentTransition >= transitions.size()) {
      return false;
    }

    return currentTick <= transitions.get(transitions.size() - 1).endTime;
  }

  /**
   * Provide the method to render the shape into an SVG-style code.
   * @param tickMS ticks per millisecond
   * @return
   */
  public String toSVG(int tickMS) {
    StringBuilder toSVG = new StringBuilder();
    toSVG.append(String.format(
        "\n<rect id=\"%s\" x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" " +
            "height=\"%.2f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >",
        name, position.getX(), position.getY(),
        size.getW(), size.getH(), color.getR(), color.getG(), color.getB())
    );

    if (!transitions.isEmpty()) {
      Transition t0 = transitions.get(0);
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"x\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.x1));
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"y\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.y1));
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"width\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.w1));
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"height\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.h1));
      toSVG.append(String.format("\n\t<set attributeName=\"fill\" begin=\"%dms\" " +
              "to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.r1, t0.g1, t0.b1));

      for (Transition t : transitions) {
        toSVG.append(transitionToSVG(t, tickMS));
      }
    }

    toSVG.append("\n</rect>");

    return toSVG.toString();
  }

  /**
   * Provide the method to render the shape's transitions to an SVG-style code.
   * @param t the transition
   * @param tickMS ticks per millisecond
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
      toSVG.append(
              String.format("\n\t<animate attributeName=\"fill\" begin=\"%dms\" dur=\"%dms\" " +
              "from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\"  fill=\"freeze\"/>",
          t.beginTime * tickMS, t.duration * tickMS, t.r1, t.g1, t.b1, t.r2, t.g2, t.b2));
    }

    return toSVG.toString();
  }

  /**
   * Reset the transition list.
   */
  public void reset() {
    currentTransition = 0;
    if (transitions.isEmpty()) {
      return;
    }

    Transition t0 = transitions.get(0);

    if (t0.beginTime > 1) {
      position = new Position2D();
      size = new Size();
      color = new Color();
    } else {
      position = new Position2D(t0.x1, t0.y1);
      size = new Size(t0.w1, t0.h1);
      color = new Color(t0.r1, t0.g1, t0.b1);
    }
  }

  /**
   * Add a new key frame to the transition list.
   * <p>
   *   If new keyframe timestamp is earlier than the shape's first keyframe or later than the
   *   last keyframe. A new transition is simply added to the front/end of the list, respectively.
   *
   *   If new keyframe timestamp is splitting in the middle of the animation, the transitions is
   *   splitted into 2 smaller transitions with the new keyframe changes accounted for.
   *
   *   if new keyframe is directly overlapping on another keyframe, the old keyframe will be
   *   removed.
   * </p>
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   */
  public void addKeyFrame(int t, int x, int y, int w, int h, int r, int g, int b) {
    if (transitions.isEmpty()) {
      transitions.add(new Transition(
          t, t,
          x, y, w, h, r, g, b,
          x, y, w, h, r, g, b
      ));

      return;
    }

    Transition firstTr = transitions.get(0);
    if (t < firstTr.beginTime) {
      transitions.add(0, new Transition(
          t, firstTr.beginTime,
          x, y, w, h, r, g, b,
          firstTr.x1, firstTr.y1, firstTr.w1, firstTr.h1, firstTr.r1, firstTr.g1, firstTr.b1
      ));

      return;
    }

    Transition lastTr = transitions.get(transitions.size() - 1);
    if (t > lastTr.endTime) {
      transitions.add(new Transition(
          lastTr.endTime, t,
          lastTr.x2, lastTr.y2, lastTr.w2, lastTr.h2, lastTr.r2, lastTr.g2, lastTr.b2,
          x, y, w, h, r, g, b
      ));

      return;
    }

    for (int i = 0; i < transitions.size(); i++) {
      Transition tr = transitions.get(i);
      if (tr.beginTime == t) {
        tr.x1 = x;
        tr.y1 = y;
        tr.w1 = w;
        tr.h1 = h;
        tr.r1 = r;
        tr.g1 = g;
        tr.b1 = h;

        if (i > 0) {
          Transition prevTr = transitions.get(i - 1);
          prevTr.x2 = x;
          prevTr.y2 = y;
          prevTr.w2 = w;
          prevTr.h2 = h;
          prevTr.r2 = r;
          prevTr.g2 = g;
          prevTr.b2 = b;
        }

        return;
      }

      if (tr.endTime > t && t > tr.beginTime) {
        Transition newTr0 = new Transition(
            tr.beginTime, t,
            tr.x1, tr.y1, tr.w1, tr.h1, tr.r1, tr.g1, tr.b1,
            x, y, w, h, r, g, b);
        Transition newTr1 = new Transition(
            t, tr.endTime,
            x, y, w, h, r, g, b,
            tr.x2, tr.y2, tr.w2, tr.h2, tr.r2, tr.g2, tr.b2
        );

        transitions.set(i, newTr0);
        transitions.add(i + 1, newTr1);

        return;
      }
    }
  }

  /**
   * Delete a keyframe from the shape's transition.
   * <p>
   *   If the keyframe timestamp is the begin time of the first transition, the whole first
   *   transition is deleted. Similarly for the last transition if the keyframe timestamp is the
   *   last end time.
   *
   *   If the keyframe timestamp is an intermediate keyframe, the two adjacent transitions before
   *   and after that keyframe timestamp is merged.
   * </p>
   * @param t    The time for this keyframe
   * @throws IllegalArgumentException if the keyframe timestamp does not exist
   */
  public void deleteKeyFrame(int t) {
    Transition firstTr = transitions.get(0);
    if (t == transitions.get(0).beginTime) {
      transitions.remove(firstTr);
      return;
    }

    Transition lastTr = transitions.get(transitions.size() - 1);
    if (t == lastTr.endTime) {
      transitions.remove(lastTr);
      return;
    }

    for (int i = 0; i < transitions.size(); i++) {
      Transition tr = transitions.get(i);

      if (tr.endTime == t) {
        Transition nextTr = transitions.get(i + 1);
        Transition mergedTr = new Transition(
            tr.beginTime, nextTr.endTime,
            tr.x1, tr.y1, tr.w1, tr.h1, tr.r1, tr.g1, tr.b1,
            nextTr.x2, nextTr.y2, nextTr.w2, nextTr.h2, nextTr.r2, nextTr.g2, nextTr.b2
        );

        transitions.set(i, mergedTr);
        transitions.remove(i + 1);

        return;
      }
    }

    throw new IllegalArgumentException("No valid key frame associated with such timestamp");
  }

  @Override
  public String getShapeType() {
    return "rectangle";
  }
}
