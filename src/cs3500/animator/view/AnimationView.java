package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import java.util.List;

/**
 * This interface represents the operation of a 2D animation view.
 * <p>
 *   The view allows to render a model once at the current tick with the list of shapes provided.
 *   The view also allows for setting the position(x,y) of the top-left corner of the bound, as
 *   well as the width and height of such bound.
 * </p>
 */
public interface AnimationView {
  /**
   * Render the shapes provided at the current tick.
   * @param currentTick
   * @param shapeList
   */
  void render(int currentTick, List<Shape> shapeList);

  /**
   * Set the paramters of the view bound.
   * @param x
   * @param y
   * @param w
   * @param h
   */
  void setBounds(int x, int y, int w, int h);
}
