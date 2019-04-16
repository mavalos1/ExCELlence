package cs3500.animator.model.shapes.attribute;

import cs3500.animator.model.helper.Color;

/**
 * This is the implementation of the wrapper of Color helper to adapt to provider.
 */
public class RBGcolor extends Color {
  /**
   * Constructor of the new RBGColor.
   * @param r the r-code
   * @param g the g-code
   * @param b the b-code
   */
  public RBGcolor(int r, int g, int b) {
    super(new Double(r), new Double(g), new Double(b));
  }

  /**
   * Constructor to copy the implemented Color helper.
   * @param c the color to copy from
   */
  public RBGcolor(Color c) {
    super(c);
  }

  /**
   * Get the red code of the color.
   * @return red code as integer.
   */
  public int getRedValue() {
    return (int) getR();
  }

  /**
   * Get the green code of the color.
   * @return green code as integer.
   */
  public int getGreenValue() {
    return (int) getG();
  }

  /**
   * Get the blue code of the color.
   * @return blue code as integer.
   */
  public int getBlueValue() {
    return (int) getB();
  }
}
