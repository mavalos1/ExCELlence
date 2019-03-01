package cs3500.ExCELlence.model.shapes;

import cs3500.ExCELlence.model.Color;
import cs3500.ExCELlence.model.Position2D;
import cs3500.ExCELlence.model.transitions.Transition;

import java.util.List;

public class ShapeImpl {
  protected String name;
  protected Position2D p;
  protected double w;
  protected double h;
  protected Color c;
  protected double r;
  protected List<Transition> transitions;

  private void buildShape(String name, Position2D p, double w, double h, Color c, double r) {
    this.setName(name);
    this.setPosition(p);
    this.setWidth(w);
    this.setHeight(h);
    this.setColor(c);
    this.setRotation(r);
  }

  /**
   * Initialize a shape with zero size, default color, and no rotation
   */
  public ShapeImpl() {
    this.buildShape("", new Position2D(0, 0), 0, 0, new Color(),0);
  }

  /**
   * Increments the state of the shape by one tick.
   */
  public void tick() {
    Transition t = transitions.get(0);

    p.setX(p.getX() + t.getDeltaPosition().getX());
    p.setY(p.getY() + t.getDeltaPosition().getY());

    w += t.getDeltaWidth();
    h += t.getDeltaHeight();

    c.setR(c.getR() + t.getDeltaColor().getR());
    c.setG(c.getG() + t.getDeltaColor().getG());
    c.setB(c.getB() + t.getDeltaColor().getB());

    r += t.getDeltaRotation();

    if (t.getTimeToLive() <= 0) {
      transitions.remove(0);
    } else {
      t.slowKill();
    }
  }

  /**
   * Return whether the stack still has more transitions.
   */
  public boolean hasTransition() {
    return !transitions.isEmpty();
  }

  public void addTransition(Transition t) {
    transitions.add(t);
  }

  /**
   * Initialize the shape to a position, width, height, color, and rotation
   * @param name
   * @param p
   * @param w
   * @param h
   * @param c
   * @param r
   */
  public ShapeImpl(String name, Position2D p, double w, double h, Color c, double r) {
    this.buildShape(name, p, w, h, c, r);
  }

  /**
   * Initialize the shape to a position, width, height, color, and no rotation
   * @param name
   * @param p
   * @param w
   * @param h
   * @param c
   */
  public ShapeImpl(String name, Position2D p, double w, double h, Color c) {
    this.buildShape(name, p, w, h, c, 0);
  }

  /**
   * Initialize the shape to a position, width, height, default color, and no rotation
   * @param name
   * @param p
   * @param w
   * @param h
   */
  public ShapeImpl(String name, Position2D p, double w, double h) {
    this.buildShape(name, p, w, h, new Color(), 0);
  }

  /**
   * Copy constructor
   * @param v
   */
  public ShapeImpl(ShapeImpl v) {
    this.buildShape(v.name, v.p, v.w, v.h, v.c, v.r);
  }

  /**
   * Get the shape's name.
   * @return p
   */
  public String getName() {
    return this.name;
  }

  /**
   * Set the shape's name.
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the shape's position.
   * @return p
   */
  public Position2D getPosition() {
    return p;
  }

  /**
   * Set the shape's position.
   * @param p
   */
  public void setPosition(Position2D p) {
    this.p = p;
  }

  /**
   * Get the shape's width.
   * @return w
   */
  public double getWidth() {
    return w;
  }

  /**
   * Set the shape's width.
   * @param w
   */
  public void setWidth(double w) {
    this.w = w;
  }

  /**
   * Get the shape's height.
   * @return h
   */
  public double getHeight() {
    return h;
  }

  /**
   * Set the shape's height.
   * @param h
   */
  public void setHeight(double h) {
    this.h = h;
  }

  /**
   * Get the shape's color.
   * @return c
   */
  public Color getColor() {
    return c;
  }

  /**
   * Set the shape's color.
   * @param c
   */
  public void setColor(Color c) {
    this.c = c;
  }

  /**
   * Get the shape's rotation.
   * @return r
   */
  public double getRotation() {
    return r;
  }

  /**
   * Set the shape's color.
   * @param rotation
   */
  public void setRotation(double rotation) {
    this.r = rotation;
  }

  /**
   * Get the shape's transition list.
   * @return r
   */
  public List<Transition> getTransitionList() {
    return this.transitions;
  }
}
