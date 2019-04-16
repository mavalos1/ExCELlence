package cs3500.animator.provider.adaptedModel;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shapes.Ellipse;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.Shape2D;
import cs3500.animator.provider.view.ExCELenceAnimatorModel;
import cs3500.animator.provider.view.IShape;

/**
 * Editable version of the animator model.
 */
public class ProviderModelAdapter extends ReadOnlyProviderModelAdapter implements ExCELenceAnimatorModel {
	/**
	 * Adapter constructor to adapt AnimationModel to provider's ExCELenceAnimatorModel.
	 * @param model
	 */
	public ProviderModelAdapter(AnimationModel model) {
		super(model);
	}

	/**
	 * Adds an shape to the animation based on its name.
	 *
	 * @param name The name identifier of the shape to add to the animation
	 * @param type The type of the shape
	 * @throws IllegalArgumentException if a shape has already been declared with this name
	 */
	public void declareShape(String name, Shape2D.ShapeType type) {
	  Shape s;
		if (type == Shape2D.ShapeType.RECTANGLE) {
			s = new Rectangle(name);
		} else {
		  s = new Ellipse(name);
		}
		model.addShape(s);
		model.addKeyFrame(name, 0, (int)s.getPosition().getXCoord(), (int)s.getPosition().getYCoord(),
						(int)s.getSize().getW(), (int)s.getSize().getH(),
						(int)s.getColor().getR(),	(int)s.getColor().getG(), (int)s.getColor().getB());

		renderToMap();
	}

	/**
	 * Removes the shape matching the given name.
	 *
	 * @param name The name of the entity to remove
	 * @throws IllegalArgumentException if the the shape does not exist.
	 */
	public void removeShape(String name) {
		if (name.isEmpty()) return;

		model.removeShape(name);
		renderToMap();
	}

	/**
	 * Adds a motion to an animation based on the given name of the shape the motion is associated
	 * with.
	 *
	 * @param name   name of the shape
	 * @param motion motion that will be applied to a shape
	 * @throws IllegalArgumentException if the shape does no exist or if the given motion overlaps
	 *                                  with the time interval of a different motion
	 */
	public void addMotion(String name, Motion motion) {
		//TODO:
	}

	/**
	 * Adds a keyframe to an animation with a given name, at a given tick.
	 * Attempts to tween the shape at the tick.
	 * If no tweening is possible, shape is initialized with dummy values.
	 * @param name  the name of the keyframe
	 * @param tick  the tick of the keyframe
	 */
	public void addKeyframe(String name, Integer tick) {
		if (name.isEmpty()) return;

		addKeyframe(name, tick, getShapeAtTick(name, tick));
	}

	/**
	 * Adds a keyframe to an animation with a given name, at a given tick.
	 *
	 * @param name  the name of the keyframe
	 * @param tick  the tick of the keyframe
	 * @param shape the shape to define for this keyframe
	 */
	public void addKeyframe(String name, Integer tick, IShape shape) {
		if (name.isEmpty()) return;

		model.addKeyFrame(
			name, tick,
			shape.getPosition().getX().intValue(),
			shape.getPosition().getY().intValue(),
			shape.getDimension().getWidth().intValue(),
			shape.getDimension().getHeight().intValue(),
			shape.getColor().getRedValue(), shape.getColor().getGreenValue(),
			shape.getColor().getBlueValue()
		);

		renderToMap();
	}

	/**
	 * Removes a keyframe from an animation for the shape with a given name at a given tick.
	 *
	 * @param name  the name of the keyframe
	 * @param tick  the tick of the keyframe
	 */
	public void removeKeyframe(String name, Integer tick) {
		if (name.isEmpty()) return;

		model.deleteKeyFrame(name, tick);
		renderToMap();
	}

	/**
	 * Sets the x-coordinate of the top-left corner of the canvas.
	 *
	 * @param canvasOffsetX the x-coordinate of the top-left corner of the canvas
	 */
	public void setCanvasOffsetX(Integer canvasOffsetX) {
		this.x = canvasOffsetX;
	}

	/**
	 * Sets the y-coordinate of the top-left corner of the canvas.
	 *
	 * @param canvasOffsetY the y-coordinate of the top-left corner of the canvas
	 */
	public void setCanvasOffsetY(Integer canvasOffsetY) {
		this.y = canvasOffsetY;
	}

	/**
	 * Sets the width of the canvas.
	 *
	 * @param canvasWidth the width
	 */
	public void setCanvasWidth(Integer canvasWidth) {
		this.w = canvasWidth;
	}

	/**
	 * Sets the height of the canvas.
	 *
	 * @param canvasHeight the height
	 */
	public void setCanvasHeight(Integer canvasHeight) {
		this.h = canvasHeight;
	}
}
