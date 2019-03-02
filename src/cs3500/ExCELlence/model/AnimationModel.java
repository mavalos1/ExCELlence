package cs3500.ExCELlence.model;

import cs3500.ExCELlence.model.shapes.Shape;
import cs3500.ExCELlence.model.transitions.Transition;import java.util.Stack;

/**
 * This interface specifies the operation of a single 2D animation.
 * <p>
 *   A 2D animation is characterized by a shape of size(a,b), at a position(x,y), and
 *   a transition of such shape over a period of time
 * </p>
 */
public interface AnimationModel {
  /**
   * Animate the shape contained in the animation model.
   */
  public void animate();

  /**
   * Return the string output of all shapes in the animation.
   */
  public String parseAllOutputs();
}
