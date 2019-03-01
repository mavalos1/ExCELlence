package cs3500.ExCELlence.model;

import cs3500.ExCELlence.model.shapes.Shape;
import cs3500.ExCELlence.model.transitions.Transition;

import java.util.Stack;

public class SingleAnimation implements AnimationModel {
  private Stack<Transition> transitionStack;
  private Stack<Shape> shapeStack;

  public SingleAnimation(Stack<Shape> s, Stack<Transition> t) {
    this.transitionStack = new Stack<>();
    this.shapeStack = new Stack<>();

    this.transitionStack = t;
    this.shapeMap.put(0, s);
  }

  /**
   * Get the current position of the animation's shape at a specific time.
   */
  public Position2D getPosition(int time) {
    return shapeMap.get(time).getPosition();
  }

  /**
   * Get the animation's shape at a specific time.
   */
  public Shape getShape(int time) {
    return this.shapeMap.get(time);
  }

  /**
   * Get the the shape's color at a specific time.
   */
  public Color getColor(int time) {
    return shapeMap.get(time).getColor();
  }

  /**
   * Get the shape's width at a specific time.
   */
  public double getWidth(int time) {
    return shapeMap.get(time).getWidth();
  }

  /**
   * Get the shape's height at a specific time.
   */
  public double getHeight(int time) {
    return shapeMap.get(time).getHeight();
  }

  /**
   * Get the shape's rotation at a specific time.
   */
  public double getRotation(int time) {
    return shapeMap.get(time).getRotation();
  }

  /**
   * Set the position of the animation's shape at a specific time.
   */
  public void setPosition(Position2D p, int time) {
    this.shapeMap.get(time).setPosition(p);
  }

  /**
   * Set the animation's shape at a specific time.
   */
  public void setShape(Shape s, int time) {
    this.shapeMap.replace(time, s);
  }

  /**
   * Set the shape's color at a specific time.
   */
  public void setColor(Color c, int time) {
    this.shapeMap.get(time).setColor(c);
  }

  /**
   * Set the shape's width at a specific time.
   */
  public void setWidth(double w, int time) {
    this.shapeMap.get(time).setWidth(w);
  }

  /**
   * Set the shape's height at a specific time.
   */
  public void setHeight(double h, int time) {
    this.shapeMap.get(time).setHeight(h);
  }

  /**
   * Set the shape's rotation at a specific time.
   */
  public void setRotation(double r, int time) {
    this.shapeMap.get(time).setRotation(r);
  }

  /**
   * Get the animation transition list.
   */
  public Stack<Transition> getTransitionList() {
    return this.transitionStack;
  }

  /**
   * Add an animation transition to the list.
   */
  public void addTransition(Transition t) {
    this.transitionStack.add(t);
  }

  /**
   * Undo the last transition from the list.
   */
  public void popTransition() {
    this.transitionStack.pop();
  }
}
