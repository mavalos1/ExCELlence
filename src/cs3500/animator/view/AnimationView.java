package cs3500.animator.view;

import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.shapes.Shape;

import java.util.List;

/**
 * This interface specifies the operation of a 2D animation view.
 * <p>
 *   The view is characterized and responsible for rendering the data in an animation model in a
 *   meaningful way (text, animation, svg, etc.).
 *
 *   The view allows to set and get its canvas size and animation speed.
 * </p>
 */
public interface AnimationView {
  /**
   * Render the animation.
   */
  void render();

  /**
   * Get the ticking speed to a number of tick per second.
   * @return the number of ticks per seconds being used
   */
  int getAnimationSpeed();

  /**
   * Set the ticking speed to a number of tick per second.
   * @param tickPerSec
   */
  void setAnimationSpeed(int tickPerSec);

  /**
   * Get the canvas size.
   * @return canvas size
   */
  Size getCanvasSize();

  /**
   * Set the canvas size.
   * @param s
   */
  void setCanvasSize(Size s);

  /**
   * Get the top left corner position of the canvas.
   * @return the top-left corner position
   */
  Position2D getCanvasPosition();

  /**
   * Set the top left position of the canvas.
   * @param topLeft
   */
  void setCanvasPosition(Position2D topLeft);

}
