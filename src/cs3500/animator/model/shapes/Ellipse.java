package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Transition;

/**
 * This class represents the implementation of a class that could be rendered as an ellipse to the
 * view. The paramaters of the shape is similar to the rectangle, aside from the method of
 * interpreting to SVG and Swing.
 */
public class Ellipse extends Rectangle {
  /**
   * Initialize the ellipse model.
   * @param name the name of the shape
   * @param x the x pos
   * @param y the y pos
   * @param w the width
   * @param h the height
   * @param r red
   * @param g green
   * @param b blue
   */
  public Ellipse(String name, int x, int y, int w, int h, int r, int g, int b) {
    super(name, x, y, w, h, r, g, b);
  }

  /**
   * Initialize the ellipse with a name.
   * @param name the name of the shape
   */
  public Ellipse(String name) {
    super(name);
  }

  /**
   * Provide the method to render the shape into an SVG-style code.
   * @param tickMS ticks per millisecond
   * @return a string of the svg
   */
  @Override
  public String toSVG(int tickMS) {
    StringBuilder toSVG = new StringBuilder();
    toSVG.append(String.format("\n<ellipse id=\"%s\" cx=\"%.2f\" cy=\"%.2f\" rx=\"%.2f\" "
                    + "ry=\"%.2f\" fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >",
        name, position.getXCoord(), position.getYCoord(),
        size.getW() / 2, size.getH() / 2, color.getR(), color.getG(), color.getB()));

    if (!transitions.isEmpty()) {
      Transition t0 = transitions.get(0);
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"cx\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.x1));
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"cy\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.y1));
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"rx\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.w1));
      toSVG.append(String.format("\n\t<set attributeType=\"xml\" attributeName=\"ry\" " +
              "begin=\"%dms\" to=\"%d\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.h1));
      toSVG.append(String.format("\n\t<set attributeName=\"fill\" begin=\"%dms\" " +
              "to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />",
          t0.beginTime * tickMS, t0.r1, t0.g1, t0.b1));

      for (Transition t : transitions) {
        toSVG.append(transitionToSVG(t, tickMS));
      }
    }

    toSVG.append("\n</ellipse>");

    return toSVG.toString();
  }

  /**
   * Provide the method to render the shape's transitions to an SVG-style code.
   * @param t the transition
   * @param tickMS the ticker per millisecond
   * @return
   */
  @Override
  protected String transitionToSVG(Transition t, int tickMS) {
    StringBuilder toSVG = new StringBuilder();

    if (t.x1 != t.x2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"cx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t.beginTime * tickMS, t.duration * tickMS, t.x1, t.x2));
    }

    if (t.y1 != t.y2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"cy\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t.beginTime * tickMS, t.duration * tickMS, t.y1, t.y2));
    }

    if (t.w1 != t.w2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"rx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t.beginTime * tickMS, t.duration * tickMS, t.w1, t.w2));
    }

    if (t.h1 != t.h2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"ry\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
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

  @Override
  public String getShapeType() {
    return "ellipse";
  }
}
