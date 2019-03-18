package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;

public class Ellipse extends ShapeImpl implements Shape {
  public Ellipse(String name, Position2D p, Size s, Color c, double r) {
    super(name, p, s, c, r);
    shapeType = "ellipse";
  }

  public Ellipse(String name) {
    super(name);
    shapeType = "ellipse";
  }

  public String SVGHeader() {
    return String.format(
      "<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\" " +
          "fill=\"rgb(%d, %d, %d)\" visibility=\"visible\">\n",
      name, p.getX(), p.getY(),
      s.getW() / 2, s.getH() / 2,
      c.getR(), c.getG(), c.getB()
    );
  }


  public String SVGFooter() {
    return "\n</ellipse>\n";
  }


  public String SVGTransition(int tickMS) {
    StringBuilder transitionOutput = new StringBuilder();

    for (Transition t : transitions) {
      transitionOutput.append(deconstructToSVG(t, tickMS));
    }

    return transitionOutput.toString();
  }


  private String deconstructToSVG(Transition t, int tickMS) {
    int duration = tickMS * t.getTimeToLive();
    StringBuilder out = new StringBuilder();

    if ((Math.abs(t.getDeltaX() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"cx\" by=\"%.0f\"/>\n",
              duration, t.getDeltaX() * t.getTimeToLive()
          )
      );
    }
    if ((Math.abs(t.getDeltaY() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"cy\" by=\"%.0f\"/>\n",
              duration, t.getDeltaY() * t.getTimeToLive()
          )
      );
    }
    if ((Math.abs(t.getDeltaWidth() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"rx\" by=\"%.0f\"/>\n",
              duration, t.getDeltaWidth() * t.getTimeToLive()
          )
      );
    }

    if ((Math.abs(t.getDeltaHeight() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"ry\" by=\"%.0f\"/>\n",
              duration, t.getDeltaHeight() * t.getTimeToLive()
          )
      );
    }

    if (t.getDeltaR() != 0 || t.getDeltaR() != 0 || t.getDeltaR() != 0) {
      out.append(
          String.format(
              "\t<animate attributeName=\"fill\" dur=\"%dms\" by=\"rgb(%d,%d,%d)\"/>\n",
              duration, t.getDeltaR() * t.getTimeToLive(), t.getDeltaR() * t.getTimeToLive(), t.getDeltaR() * t.getTimeToLive()
          )
      );
    }

    return out.toString();
  }
}
