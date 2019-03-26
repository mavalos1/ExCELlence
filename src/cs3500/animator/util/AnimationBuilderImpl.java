package cs3500.animator.util;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;

/**
 * A class for creating animation controllers.
 */
public class AnimationBuilderImpl implements AnimationBuilder {
  private AnimationController controller;

  public AnimationBuilderImpl(String type, int speed, String outFile) {
    controller = new Controller(type, speed, outFile);
  }

  /**
   * builds an animation controller.
   * @return an animation controller
   */
  public AnimationController build() {
    controller.start();

    return controller;
  }

  /**
   * sets the bounds of the animation.
   * @param x The leftmost x value
   * @param y The topmost y value
   * @param width The width of the bounding box
   * @param height The height of the bounding box
   * @return
   */
  public AnimationBuilder<AnimationController> setBounds(int x, int y, int width, int height) {
    controller.setBounds(x, y, width, height);

    return this;
  }

  /**
   * declares a shape in the animation.
   * @param name The unique name of the shape to be added.
   *             No shape with this name should already exist.
   * @param type The type of shape (e.g. "ellipse", "rectangle") to be added.
   *             The set of supported shapes is unspecified, but should
   *             include "ellipse" and "rectangle" as a minimum.
   * @return
   */
  public AnimationBuilder<AnimationController> declareShape(String name, String type) {
    controller.addShape(name, type);

    return this;
  }

  /**
   * Adds a transition to the animation.
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @return
   */
  public AnimationBuilder<AnimationController> addMotion(
      String name,
      int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    controller.addTransition(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);

    return this;
  }

  /**
   * Adds a keyframe to the animation.
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t    The time for this keyframe
   * @param x    The x-position of the shape
   * @param y    The y-position of the shape
   * @param w    The width of the shape
   * @param h    The height of the shape
   * @param r    The red color-value of the shape
   * @param g    The green color-value of the shape
   * @param b    The blue color-value of the shape
   * @return
   */
  public AnimationBuilder<AnimationController> addKeyframe(
      String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    // Our model does not allow to add an individual key frame, yet.
    // controller.addFrame(name, t, x, y, w, h, r, g, b);

    return this;
  }
}
