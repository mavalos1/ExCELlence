package cs3500.animator.model;

import cs3500.animator.model.motion.Motion;
import cs3500.animator.model.shapes.Shape;
import cs3500.animator.model.shapes.Shape2D;
import cs3500.animator.provider.view.IShape;
import cs3500.animator.provider.view.ReadOnlyExCELenceAnimatorModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProviderModelAdapter implements ReadOnlyExCELenceAnimatorModel {
	private int x = 0;
	private int y = 0;
	private int w = 400;
	private int h = 400;

	private AnimationModel model;
	private HashMap<Integer, ArrayList<Shape2D>> renderedShapeMap;

	public ProviderModelAdapter(AnimationModel model) {
		this.model = model;
		renderedShapeMap = new HashMap<>();

		int i = 0;
		List<Shape> shapes = model.getShapes();
		while (model.canTick()) {
			ArrayList<Shape2D> sList = new ArrayList<>();

			for (Shape s : shapes) {
				sList.add(new Shape2D(s));
			}

			renderedShapeMap.put(i, sList);
			model.tick();
			i++;
		}
	}

	/**
	 * Returns the names of all the shapes in the animation.
	 *
	 * @return an ArrayList of IShape names
	 */
	@Override
	public ArrayList<String> getShapes() {
		List<Shape> shapes = model.getShapes();
		ArrayList<String> sNames = new ArrayList<>();

		for (Shape s : shapes) {
			sNames.add(s.getName());
		}

		if (sNames.size() < 2) sNames.add(1, "");

		return sNames;
	}

	/**
	 * Returns a shape at the given time based on the given name.
	 *
	 * @param name the name of the shape
	 * @param tick the time at which the shape will be retrieved
	 * @return an IShape at the specified tick
	 * @throws IllegalArgumentException if specified tick does not exist
	 */
	public IShape getShapeAtTick(String name, Integer tick) {
		ArrayList<Shape2D> sList = renderedShapeMap.get(tick);

		for (Shape2D s : sList) {
			if (s.getName().equals(name)) {
				return s;
			}
		}

		throw new IllegalArgumentException("No shape with such name found");
	}

	/**
	 * Gets all the shapes that exist at the given tick.
	 *
	 * @param tick the time
	 * @return an ArrayList of IShapes at the specified tick
	 */
	public ArrayList<IShape> getShapesAtTick(Integer tick) {
		ArrayList<Shape2D> sList = renderedShapeMap.get(tick);

		if (sList == null) {
			int lastTick = getKeyticksForShape("").get(0);
			sList = renderedShapeMap.get(lastTick);
		}

		ArrayList<IShape> rList = new ArrayList<>();
		for (Shape2D s : sList) {
			rList.add(s);
		}

		return rList;
	}

	/**
	 * Gets all the keyframe ticks for a specific shape given its name.
	 *
	 * @param name the name of the shape
	 * @return an ArrayList of ticks that are keyframes of a shape
	 */
	public ArrayList<Integer> getKeyticksForShape(String name) {
		int i = 0;
		while (renderedShapeMap.get(i) != null) {
			i++;
		}

		ArrayList<Integer> kList = new ArrayList<>();
		kList.add(i - 1);

		return kList;
	}

	/**
	 * Gets all the motions for a specific shape based on the given name of the shape.
	 *
	 * @param name the name of the shape
	 * @return an ArrayList of motions that belong to a specified shape
	 */
	public ArrayList<Motion> getMotionsForShape(String name) {
		throw new UnsupportedOperationException("No motion get supported");
	}

	/**
	 * Gets the shape type (rectangle, ellipse, etc) of a shape.
	 *
	 * @param name the name of the shape
	 * @return an Enum representing the shape type
	 */
	public Shape2D.ShapeType getShapeType(String name) {
		throw new UnsupportedOperationException("No shape type get supported");
	}

	/**
	 * Gets the width of the canvas.
	 *
	 * @return an Integer representing the width
	 */
	public Integer getCanvasWidth() {
		return w;
	}

	/**
	 * Gets the height of the canvas.
	 *
	 * @return an Integer representing the height
	 */
	public Integer getCanvasHeight() {
		return h;
	}

	/**
	 * Gets the x-coordinate of the top-left corner of the canvas.
	 *
	 * @return an Integer representing the x-coordinate
	 */
	public Integer getCanvasOffsetX() {
		return x;
	}


	/**
	 * Gets the y-coordinate of the top-left corner of the canvas.
	 *
	 * @return an Integer representing the y-coordinate
	 */
	public Integer getCanvasOffsetY() {
		return y;
	}

	/**
	 * Determine how many ticks long the animation.
	 * This is essentially the tick of the last keyframe.
	 * @return the length of this animation, in ticks
	 */
	public Integer getDuration() {
		throw new UnsupportedOperationException("No duration get supported");
	}
}
