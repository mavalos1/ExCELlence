package cs3500.nguyenmayeux.model.helper;

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
   * @throws IllegalArgumentException when code is out of (0, 255) range
   */
  public Color(int r, int g, int b) throws IllegalArgumentException {
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
  public Color(Color v) throws IllegalArgumentException {
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
  public void setR(int r) throws IllegalArgumentException {
    if (r < 0 || r > 255) {
      throw new IllegalArgumentException("Invalid color R-component arguments");
    }
    this.r = r;
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
  public void setB(int b) throws IllegalArgumentException {
    if (b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid color B-component arguments");
    }
    this.b = b;
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
  public void setG(int g) throws IllegalArgumentException {
    if (g < 0 || g > 255) {
      throw new IllegalArgumentException("Invalid color G-component arguments");
    }
    this.g = g;
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
