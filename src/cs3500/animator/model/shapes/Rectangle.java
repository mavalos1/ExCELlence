package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;

import java.util.List;

public class Rectangle extends ShapeImpl implements Shape {
  public Rectangle(String name, Position2D p, Size s, Color c, double r) {
    super(name, p, s, c, r);
    shapeType = "rectangle";
  }

  public Rectangle(String name) {
    super(name);
    shapeType = "rectangle";
  }

  public String SVGHeader() {
    return String.format(
      "<rect id=\"%s\" x=\"%.0f\" y=\"%.0f\" width=\"%.0f\" height=\"%.0f\" " +
          "fill=\"rgb(%d, %d, %d)\" visibility=\"visible\" >\n",
      name, p.getX(), p.getY(),
      s.getW(), s.getH(),
      c.getR(), c.getG(), c.getB()
    );
  }

  public String SVGFooter() {
    return "\n</rect>\n";
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
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"x\" by=\"%.0f\"/>\n",
              duration, t.getDeltaX() * t.getTimeToLive()
          )
      );
    }
    if ((Math.abs(t.getDeltaY() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"y\" by=\"%.0f\"/>\n",
              duration, t.getDeltaY() * t.getTimeToLive()
          )
      );
    }
    if ((Math.abs(t.getDeltaWidth() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"width\" by=\"%.0f\"/>\n",
              duration, t.getDeltaWidth() * t.getTimeToLive()
          )
      );
    }

    if ((Math.abs(t.getDeltaHeight() - 0) >= 0.01)) {
      out.append(
          String.format(
              "\t<animate attributeType=\"xml\" dur=\"%dms\" attributeName=\"height\" by=\"%.0f\"/>\n",
              duration, t.getDeltaHeight() * t.getTimeToLive()
          )
      );
    }

    if (t.getDeltaR() != 0 || t.getDeltaG() != 0 || t.getDeltaB() != 0) {
      out.append(
          String.format(
              "\t<animate attributeName=\"fill\" dur=\"%dms\" by=\"rgb(%d,%d,%d)\"/>\n",
              duration, t.getDeltaR() * t.getTimeToLive(), t.getDeltaG() * t.getTimeToLive(),
              t.getDeltaB() * t.getTimeToLive()
          )
      );
    }

    return out.toString();
  }
}
