package cs3500.animator.model.shapes.attribute;

import cs3500.animator.model.helper.Size;

/**
 * This is the implementation of the wrapper of Size helper to adapt to provider.
 */
public class Dimension2D extends Size {
  /**
   * Constructor to copy implemented Size helper.
   * @param s the size to copy from
   */
  public Dimension2D(Size s) {
    super(s);
  }

  /**
   * Get the width of the dimension.
   * @return width as Double.
   */
  public Double getWidth() {
    return getW();
  }

  /**
   * Get the height of the dimension.
   * @return height as Double.
   */
  public Double getHeight() {
    return getH();
  }
}
