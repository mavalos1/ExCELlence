package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.shapes.Shape;

import java.util.List;

/**
 * This class represents the implementations of the common functions of the view interface.
 * <p>
 *   The view is directly containing the model right now, which WILL be moved toward a controller
 *   in the future.
 * </p>
 */
public abstract class ViewImpl implements AnimationView {
  protected int tPs, currentTick;
  protected Size canvasSize;
  protected Position2D topLeft;
  protected AnimationModel model;

  /**
   * Initialize the canvas to a size and a position
   * @param x
   * @param y
   * @param w
   * @param h
   * @param tickPerSecond
   * @throws IllegalArgumentException when width or height are negative, or when speed is
   * non-positive
   */
  public ViewImpl(int x, int y, int w, int h, int tickPerSecond, AnimationModel model)
      throws IllegalArgumentException {
    this.currentTick = 0;
    this.model = model;
    this.setCanvasPosition(new Position2D(x, y));
    this.setCanvasSize(new Size(w, h));
    this.setAnimationSpeed(tickPerSecond);
  }

  /**
   * Get the canvas size.
   * @return canvas size
   */
  public Size getCanvasSize() {
    return this.canvasSize;
  }

  /**
   * Set the canvas size.
   * @param s
   */
  public void setCanvasSize(Size s) {
    this.canvasSize = s;
  }

  /**
   * Set the ticking speed to a number of tick per second.
   * @param tickPerSec
   * @throws IllegalArgumentException when tick per second is non-positive
   */
  public void setAnimationSpeed(int tickPerSec) throws IllegalArgumentException{
    if (tickPerSec <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    this.tPs = tickPerSec;
  }

  /**
   * Get the ticking speed to a number of tick per second
   * @return the number of ticks per seconds being used
   */
  public int getAnimationSpeed() {
    return this.tPs;
  }

  /**
   * Get the top left corner position of the canvas.
   * @return the top-left corner position
   */
  public Position2D getCanvasPosition() {
    return this.topLeft;
  }

  /**
   * Set the top left position of the canvas.
   * @param topLeft
   */
  public void setCanvasPosition(Position2D topLeft) {
    this.topLeft = topLeft;
  }

  protected void animate() throws InterruptedException {
    long tickMS = 1000 / this.tPs;
    boolean shouldPlay = true;

    while (shouldPlay) {
      shouldPlay = false;

      List<Shape> shapes = model.getAllShapes();
      for (Shape s : shapes) {
        renderShapeView(s);

        if (s.hasTransition()) {
          shouldPlay = true;
        }
      }

      model.tick();
      Thread.sleep(tickMS);
    }
  }

  abstract void renderShapeView(Shape s);
}
