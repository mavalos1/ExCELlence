package cs3500.animator.model.shapes;

import cs3500.animator.model.shapes.attribute.Dimension2D;
import cs3500.animator.model.shapes.attribute.Position2D;
import cs3500.animator.model.shapes.attribute.RBGcolor;
import cs3500.animator.provider.view.IShape;

/**
 * This is the implementation of the wrapper of Shape interface to provider.
 */
public class Shape2D extends Rectangle implements IShape {
  private ShapeType t;
  private Position2D p = new Position2D(this.position);
  private Dimension2D d = new Dimension2D(this.size);
  private RBGcolor c = new RBGcolor(this.color);

  /**
   * Implement provider Shape Type. Limited to: Rectangle and Ellipse.
   */
  public enum ShapeType {
    RECTANGLE, ELLIPSE
  }

  /**
   * Constructor to copy the implemented Shape interface.
   * @param s the shape to copy from
   */
  public Shape2D(Shape s) {
    super(s);
    t = s.getShapeType().equals("ellipse") ? ShapeType.ELLIPSE : ShapeType.RECTANGLE;
  }

  /**
   * Implement the provider's constructor to create the shape.
   * @param x the x-position
   * @param y the y-position
   * @param w the width
   * @param h the height
   * @param r the r-code
   * @param b the b-code
   * @param g the g-code
   * @param t the type of shape
   */
  public Shape2D(int x, int y, int w, int h, int r, int b, int g, ShapeType t) {
    super("", x, y, w, h, r, b, g);
    this.t = t;
  }

  /**
   * Get the dimension of the shape.
   * @return the dimension
   */
  @Override
  public Dimension2D getDimension() {
    return d;
  }

  /**
   * Set the dimension of the shape.
   * @param d the dimensiom
   */
  @Override
  public void setDimension(Dimension2D d) {
    this.d = d;
  }

  /**
   * Get the position of the shape.
   * @return the shape's position
   */
  @Override
  public Position2D getPosition() {
    return p;
  }

  /**
   * Set the position of the shape.
   * @param p the position
   */
  @Override
  public void setPosition(Position2D p) {
    this.p = p;
  }

  /**
   * Get the color of the shape.
   * @return the color
   */
  public RBGcolor getColor() {
    return c;
  }

  /**
   * Set the color of the shape.
   * @param c the color
   */
  @Override
  public void setColor(RBGcolor c) {
    this.c = c;
  }

  /**
   * Get the shape type.
   * @return the shape type enumeration, either Rectangle or Ellipse
   */
  @Override
  public ShapeType getType() {
    return t;
  }

  /**
   * Copy function into IShape.
   * @return an IShape of identical measurements
   */
  @Override
  public IShape copy() {
    return new Shape2D(
        (int)this.p.getXCoord(), (int)this.p.getYCoord(),
        this.d.getWidth().intValue(), this.d.getHeight().intValue(),
        this.c.getRedValue(), this.c.getBlueValue(), this.c.getGreenValue(),
        this.t
    );
  }
}

