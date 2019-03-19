package cs3500.animator.util;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.Controller;

public class AnimationBuilderImpl implements AnimationBuilder {
  private AnimationController controller;

  public AnimationBuilderImpl(String type, int speed, String outFile) {
    controller = new Controller(type, speed, outFile);
  }

  public AnimationController build() {
    controller.animate();

    return controller;
  }

  public AnimationBuilder<AnimationController> setBounds(int x, int y, int width, int height) {
    controller.setBounds(x, y, width, height);

    return this;
  }

  public AnimationBuilder<AnimationController> declareShape(String name, String type) {
    controller.addShape(name, type);

    return this;
  }

  public AnimationBuilder<AnimationController> addMotion(
      String name,
      int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    controller.addTransition(name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);

    return this;
  }

  public AnimationBuilder<AnimationController> addKeyframe(
      String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    // Our model does not allow to add an individual key frame, yet.
    // controller.addFrame(name, t, x, y, w, h, r, g, b);

    return this;
  }
}
