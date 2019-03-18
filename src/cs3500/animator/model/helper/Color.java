package cs3500.animator.model.helper;

import java.util.Objects;

/**
 * This class represents a RGB-coded color. Three integer code in the range of (0, 255) uniquely
 * identify each color code.
 */
public class Color {
  private int r, g, b;

  /**
   * Initialize the color to the code.
   * @param r
   * @param g
   * @param b
   */
  public Color(int r, int g, int b) {
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
   * @param v
   */
  public Color(Color v)  {
    this(v.r, v.g, v.b);
  }

  /**
   * Get the r-code of this color.
   *
   * @return r
   */
  public int getR() {
    return r;
  }

  /**
   * Set the r-code of this color.
   *
   * @param r
   */
  public void setR(int r) {
    this.r = r % 256;
  }

  /**
   * Get the g-code of this color.
   *
   * @return g
   */
  public int getG() {
    return g;
  }

  /**
   * Set the g-code of this color.
   *
   * @param g
   */
  public void setG(int g) {
    this.g = g % 256;
  }

  /**
   * Get the b-code of this color.
   *
   * @return b
   */
  public int getB() {
    return b;
  }

  /**
   * Set the b-code of this color.
   *
   * @param b
   */
  public void setB(int b) {
    this.b = b % 256;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a)  { return true; }
    if (!(a instanceof Color)) { return false; }

    Color that = (Color) a;

    return (this.r == that.r && this.g == that.g && this.b == that.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }
}
