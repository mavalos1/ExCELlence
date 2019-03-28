package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * This class represents the implementation of the animation controller.
 * <p>
 *   The controller renders the shapes contained within the model to the view depends on the type
 *   of view selected.
 *   The added shapes and animations are rendered by the order of them being added.
 * </p>
 */
public class Controller implements AnimationController, ActionListener {
  private AnimationView view;
  private AnimationModel model;
  private boolean shouldPlay = true;
  private boolean loop = false;
  private boolean looping = false;

  /**
   * Initialize the controller.
   * @param model the model
   * @param view the view
   */
  public Controller(AnimationModel model, AnimationView view, int speed) {
    Objects.requireNonNull(model, "Must have a valid model");
    Objects.requireNonNull(view, "Must have a valid view");

    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    this.model = model;
    this.view = view;
    setSpeed(speed);
  }

  /**
   * Initialize the controller with a type, speed, and an output destination.
   * @param type the type of view
   * @param speed the ticks per second
   * @param outFile the file to write to
   */
  public Controller(String type, int speed, String outFile) {
    Objects.requireNonNull(speed, "Must have non-null speed");
    Objects.requireNonNull(type, "Must have non-null view type");
    Objects.requireNonNull(outFile, "Must have non-null output file name");

    if (speed <= 0) {
      throw new IllegalArgumentException("Invalid animation speed");
    }

    switch (type) {
      case "text":
        this.view = new TextualView();
        this.view.setOutputFile(outFile);
        break;
      case "svg":
        this.view = new SVGView();
        this.view.setOutputFile(outFile);
        break;
      case "visual":
        this.view = new VisualView();
        break;
      case "edit":
        this.view = new EditorView();
        this.view.setListener(this);
        break;
      default:
        throw new IllegalArgumentException("Invalid view type");
    }

    this.model = new Model();
    setSpeed(speed);
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
  public void start() {
    while (true) {
      looping = true;
      if (!shouldPlay) {
        System.out.println("should not play");
        continue;
      } else {
        System.out.println("should play");
      }

      renderView();
      nextTick();

      if (!model.canTick()) {
        shouldPlay = false;
        if (loop) {
          if (view instanceof TextualView || view instanceof SVGView) {
            return;
          }

          restart();
        }
      }
    }
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
   * @param name the name of the shape
   * @param type the type of shape
   */
  public void addShape(String name, String type) {
    Objects.requireNonNull(name, "Must have a valid shape name");
    Objects.requireNonNull(name, "Must have a valid shape type");

    if (name.isEmpty() || type.isEmpty()) {
      throw new IllegalArgumentException("Empty shape name or type");
    }

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
    model.addTransition(name, t);
  }

  /**
   * Adds a keyframe to the animation.
   * @param name The name of the shape
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
  public void addKeyFrame(
      String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    model.addKeyFrame(name, t, x, y, w, h, r, g, b);
  }

  /**
   * Delete a keyframe from the animation.
   * @param name The name of the shape
   * @param t    The time for this keyframe
   * @return
   */
  public void deleteKeyFrame(String name, int t) {
    model.deleteKeyFrame(name, t);
  }

  /**
   * Pause the animation.
   */
  public void togglePause() {
    shouldPlay = !shouldPlay;
    //if (!looping) {
    //  start();
    //}
  }

  /**
   * Restart the animation.
   */
  public void restart() {
    shouldPlay = false;
    model.reset();
    renderView();
    //if (!looping) {
    //  start();
    //}
  }

  /**
   * Toggle the looping option.
   */
  public void toggleLoop() {
    loop = !loop;
  }

  /**
   * Set the speed of the animation.
   * @param speed the number of ticks per second
   */
  public void setSpeed(int speed) {
    if (view instanceof TextualView) {
      return;
    }

    view.setSpeed(speed);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "start/pause":
        togglePause();
        break;
      case "restart":
        restart();
        break;
      case "loop":
        toggleLoop();
        break;
      case "speed":
        setSpeed(view.getSpeed());
        break;
      case "rectangle":
        PopUpOptionPanel pR = (PopUpOptionPanel) e.getSource();

        Rectangle r = new Rectangle(pR.name, pR.x, pR.y, pR.w, pR.h, pR.r, pR.g, pR.b);
        model.addShape(r);
        break;
      case "ellipse":
        PopUpOptionPanel pE = (PopUpOptionPanel) e.getSource();

        Ellipse el = new Ellipse(pE.name, pE.x, pE.y, pE.w, pE.h, pE.r, pE.g, pE.b);
        model.addShape(el);
        break;
      case "removeShape":
        PopUpOptionPanel pS = (PopUpOptionPanel) e.getSource();

        model.removeShape(pS.name);
        break;
      case "addKeyFrame":
        PopUpOptionPanel pK = (PopUpOptionPanel) e.getSource();

        addKeyFrame(pK.name, pK.t, pK.x, pK.y, pK.w, pK.h, pK.r, pK.g, pK.b);
        break;
      case "removeKeyFrame":
        PopUpOptionPanel pF = (PopUpOptionPanel) e.getSource();

        deleteKeyFrame(pF.name, pF.t);
        break;
      default:
        throw new IllegalArgumentException("Unsupported action command");
    }
  }
}
