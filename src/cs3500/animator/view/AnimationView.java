package cs3500.animator.view;

import cs3500.animator.model.shapes.Shape;

import java.awt.event.ActionListener;
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
   * @param currentTick the current tick of the model
   * @param shapeList the list of shape to be rendered
   */
  void render(int currentTick, List<Shape> shapeList);

  /**
   * Set the parameters of the view bound.
   * @param x x-coordinate of the top-left corner of the view
   * @param y y-coordinate of the top-left corner of the view
   * @param w width of the view
   * @param h height of the view
   */
  void setBounds(int x, int y, int w, int h);

  void setListener(ActionListener l);
}
