package cs3500.animator.model.helper;

import java.util.Objects;

public class Size {
  private double w, h;

  /**
   * Initialize the object to the specified position.
   * @param w
   * @param h
   * @throws IllegalArgumentException when either width or height is negative
   */
  public Size(double w, double h) throws IllegalArgumentException {
    this.setW(w);
    this.setH(h);
  }

  /**
   * Initialize the object to the (0, 0) position.
   */
  public Size() {
    this(0, 0);
  }

  /**
   * Copy constructor.
   * @param v
   */
  public Size(Size v) {
    this(v.w, v.h);
  }

  /**
   * Get the width of this size.
   *
   * @return w
   */
  public double getW() {
    return w;
  }

  /**
   * Get the height of this size.
   *
   * @return h
   */
  public double getH() {
    return h;
  }

  /**
   * Set the width of this size.
   *
   * @param w
   * @throws IllegalArgumentException when width is negative
   */
  public void setW(double w) throws IllegalArgumentException {
    if (w < 0) {
      throw new IllegalArgumentException("Invalid size width");
    }

    this.w = w;
  }

  /**
   * Set the height of this size.
   *
   * @param h
   * @throws IllegalArgumentException when height is negative
   */
  public void setH(double h) throws IllegalArgumentException{
    if (h < 0) {
      throw new IllegalArgumentException("Invalid size height");
    }
    this.h = h;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a)  { return true; }
    if (!(a instanceof Size)) { return false; }

    Size that = (Size) a;

    return ((Math.abs(this.w - that.w) < 0.01)
        && (Math.abs(this.h - that.h) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.w, this.h);
  }
}