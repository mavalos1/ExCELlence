package cs3500.animator.model.helper;

import java.util.Objects;

/**
 * This class represents a RGB-coded color. Three code in the range of (0, 255) uniquely
 * identify each color code.
 */
public class Color {
  private double r;
  private double g;
  private double b;

  /**
   * Initialize the color to the code.
   * @param r red
   * @param g green
   * @param b blue
   */
  public Color(double r, double g, double b) {
    this.setR(r);
    this.setG(g);
    this.setB(b);
  }

  /**
   * Initialize the color to the default code of (0, 0, 0).
   */
  public Color() {
    this(0, 0, 0);
  }

  /**
   * Copy constructor.
   * @param v the color
   */
  public Color(Color v)  {
    this(v.r, v.g, v.b);
  }

  /**
   * Get the r-code of this color.
   *
   * @return r
   */
  public double getR() {
    return r;
  }

  /**
   * Set the r-code of this color.
   *
   * @param r the red componenet
   */
  private void setR(double r) {
    if (r < 0) {
      r += 256;
    }
    this.r = r % 256;
  }

  /**
   * Get the g-code of this color.
   *
   * @return g green
   */
  public double getG() {
    return g;
  }

  /**
   * Set the g-code of this color.
   *
   * @param g green
   */
  private void setG(double g) {
    if (g < 0) {
      g += 256;
    }
    this.g = g % 256;
  }

  /**
   * Get the b-code of this color.
   *
   * @return b blue
   */
  public double getB() {
    return b;
  }

  /**
   * Set the b-code of this color.
   *
   * @param b blue
   */
  private void setB(double b) {
    if (b < 0) {
      b += 256;
    }
    this.b = b % 256;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a)  {
      return true;
    }
    if (!(a instanceof Color)) {
      return false;
    }

    Color that = (Color) a;

    return ((Math.abs(this.r - that.r) < 0.01)
        && (Math.abs(this.g - that.g) < 0.01)
        && (Math.abs(this.b - that.b) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }
}
