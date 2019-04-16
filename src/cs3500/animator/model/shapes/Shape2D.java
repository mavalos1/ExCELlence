package cs3500.animator.model.shapes;

import cs3500.animator.model.shapes.attribute.Dimension2D;
import cs3500.animator.model.shapes.attribute.Position2D;
import cs3500.animator.model.shapes.attribute.RBGcolor;
import cs3500.animator.provider.view.IShape;

public class Shape2D extends Rectangle implements IShape {
	private ShapeType t = ShapeType.RECTANGLE;
	private Position2D p = new Position2D(this.position);
	private Dimension2D d = new Dimension2D(this.size);
	private RBGcolor c = new RBGcolor(this.color);

	public enum ShapeType {
		RECTANGLE, ELLIPSE
	}

	public Shape2D(Shape s) {
		super(s);
	}

	public Shape2D(int x, int y, int w, int h, int r, int b, int g, ShapeType t) {
		super("", x, y, w, h, r, b, g);
		this.t = t;
	}

	@Override
	public Dimension2D getDimension() {
		return d;
	}

	@Override
	public void setDimension(Dimension2D d) {
		this.d = d;
	}

	@Override
	public Position2D getPosition() {
		return p;
	}

	@Override
	public void setPosition(Position2D p) {
		this.p = p;
	}

	public RBGcolor getColor() {
		return c;
	}

	@Override
	public void setColor(RBGcolor c) {
		this.c = c;
	}

	@Override
	public ShapeType getType() {
		return t;
	}

	@Override
	public IShape copy() {
		return new Shape2D(
			this.p.getXCoord().intValue(), this.p.getYCoord().intValue(),
			this.d.getWidth().intValue(), this.d.getHeight().intValue(),
			this.c.getRedValue(), this.c.getBlueValue(), this.c.getGreenValue(),
			this.t
		);
	}
}

