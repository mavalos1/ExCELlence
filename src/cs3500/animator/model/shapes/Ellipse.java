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
   * @param name
   * @param x
   * @param y
   * @param w
   * @param h
   * @param r
   * @param g
   * @param b
   */
  public Ellipse(String name, int x, int y, int w, int h, int r, int g, int b) {
    super(name, x, y, w, h, r, g, b);
  }

  /**
   * Initialize the ellipse with a name.
   * @param name
   */
  public Ellipse(String name) {
    super(name);
  }

  /**
   * Provide the method to render the shape into an SVG-style code.
   * @param tickMS
   * @return
   */
  @Override
  public String toSVG(int tickMS) {
    StringBuilder toSVG = new StringBuilder();
    toSVG.append(String.format("\n<ellipse id=\"%s\" cx=\"%.2f\" cy=\"%.2f\" rx=\"%.2f\" " +
            "ry=\"%.2f\" fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >",
        name, position.getX(), position.getY(),
        size.getW() / 2, size.getH() / 2, color.getR(), color.getG(), color.getB()));

    for (Transition t : transitions) {
      toSVG.append(transitionToSVG(t, tickMS));
    }

    toSVG.append("\n</ellipse>");

    return toSVG.toString();
  }

  /**
   * Provide the method to render the shape's transitions to an SVG-style code.
   * @param t
   * @param tickMS
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
              " attributeName=\"cx\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t.beginTime * tickMS, t.duration * tickMS, t.w1, t.w2));
    }

    if (t.h1 != t.h2) {
      toSVG.append(String.format("\n\t<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
              " attributeName=\"cy\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
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
    return "ellipse";
  }
}
