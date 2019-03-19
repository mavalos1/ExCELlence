package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;

import java.util.Objects;

/**
 * This class represents the implementation of the animation controller.
 * <p>
 *   The controller renders the shapes contained within the model to the view depends on the type
 *   of view selected.
 *   The added shapes and animations are rendered by the order of them being added.
 * </p>
 */
public class Controller implements AnimationController{
  private AnimationView view;
  private AnimationModel model;
  private int speed;

  /**
   * Initialize the controller.
   * @param model
   * @param view
   */
  public Controller(AnimationModel model, AnimationView view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
  }

  /**
   * Initialize the controller with a type, speed, and an output destination.
   * @param type
   * @param speed
   * @param outFile
   */
  public Controller(String type, int speed, String outFile) {
    Objects.requireNonNull(speed, "Must have non-null speed");
    Objects.requireNonNull(type, "Must have non-null view type");
    Objects.requireNonNull(outFile, "Must have non-null output file name");

    this.speed = speed;
    this.model = new Model();

    switch (type) {
      case "text":
        this.view = new TextualView(outFile);
        break;
      case "svg":
        this.view = new SVGView(speed, outFile);
        break;
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }

  /**
   * Render the model the the view once.
   */
  public void renderView() {
    view.render(model.getCurrentTick(), model.getShapes());
  }

  /**
   * Advance the model to the next tick.
   */
  public void nextTick() {
    model.tick();
  }

  /**
   * Animate the model till the end.
   */
  public void animate() {
    while (model.canTick()) {
      this.renderView();
      this.nextTick();
      try {
        Thread.sleep(1000 / speed);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    this.renderView();
  }

  /**
   * Set the bounds of the view.
   * @param x the x-coordinate of the top left point of the view
   * @param y the y-coordinate of the top left point of the view
   * @param w the width of the view
   * @param h the height of the view
   */
  public void setBounds(int x, int y, int w, int h) {
    view.setBounds(x, y, w, h);
  }

  /**
   * Add a new shape to the model.
   * @param name
   * @param type
   */
  public void addShape(String name, String type) {
    Objects.requireNonNull(name, "Must have a valid shape name");
    Objects.requireNonNull(name, "Must have a valid shape type");

    Shape s = null;
    if (type.equals("rectangle")) {
      s = new Rectangle(name);
    } else if (type.equals("ellipse")) {
      s = new Ellipse(name);
    } else {
      throw new IllegalArgumentException("Unsupported shape types");
    }

    model.addShape(s);
  }

  /**
   * Add a new transition to the model.
   * @param name the name of the shape to add the transition to
   * @param t1 the beginning time of the transition
   * @param x1 the beginning x-coordinate of the transition
   * @param y1 the beginning y-coordinate of the transition
   * @param w1 the beginning width of the transition
   * @param h1 the beginning height of the transition
   * @param r1 the beginning R-color code of the transition
   * @param g1 the beginning G-color code of the transition
   * @param b1 the beginning B-color code of the transition
   * @param t2 the ending time of the transition
   * @param x2 the ending x-coordinate of the transition
   * @param y2 the ending y-coordinate of the transition
   * @param w2 the ending width of the transition
   * @param h2 the ending height of the transition
   * @param r2 the ending R-color code of the transition
   * @param g2 the ending G-color code of the transition
   * @param b2 the ending B-color code of the transition
   */
  public void addTransition(String name,
                            int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                            int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    Objects.requireNonNull(name, "Must have a valid shape name");
    Transition t = new Transition(t1, t2, x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);
    model.getShape(name).addTransition(t);
  }
}
