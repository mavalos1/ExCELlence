package cs3500.nguyenmayeux.model;

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
  void animate();

  /**
   * Return the string output of all shapes in the animation.
   */
  String parseAllOutputs();
}
