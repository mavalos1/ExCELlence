package cs3500.animator.model.shapes.attribute;
/**
 * This is the implementation of the wrapper of Position2D helper to adapt to provider.
 */
public class Position2D extends cs3500.animator.model.helper.Position2D {
	public Position2D(cs3500.animator.model.helper.Position2D p) {
		super(p);
	}

	/**
	 * Return the x-coordinate of the position.
	 * This method is renamed as it clashes with the same getX() method in implemented Position2D.
	 * @return
	 */
	public Double getX() {
		return getXCoord();
	}

	/**
	 * Return the y-coordinate of the position.
	 * This method is renamed as it clashes with the same getY() method in implemented Position2D.
	 * @return
	 */
	public Double getY() {
		return getYCoord();
	}
}
