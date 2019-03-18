package cs3500.animator.model.shapes;

import cs3500.animator.model.helper.Color;
import cs3500.animator.model.helper.Position2D;
import cs3500.animator.model.helper.Size;
import cs3500.animator.model.helper.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the shape class, abstract shape used by other shapes.
 *
 * <p>
 *   A shape has a name, an initial position, a size of width and height, a color, and a rotation.
 *   A shape can have a list of transitions stored within to tell the model how to animate it.
 *   A shape should have an unique name to avoid being overwritten when added to model.
 * </p>
 * <p>
 *   The animations could only be added on top of each other, thus no overlapping animations,
 *   meaning occuring at the same tick, could happen. Inconsistent transitions, therefore, are
 *   not possible. If a shape want to stay unchanged for an amount of time, it needs to add a
 *   default transition of such amount of time.
 * </p>
 */
public class ShapeImpl {
  protected String name;
  protected String shapeType;
  protected Position2D p;
  protected Size s;
  protected Color c;
  protected double r;
  protected List<Transition> transitions;

  /**
   * Initialize the shape with the specified params
   * @param name the name of the shape
   * @param p the initial position of the shape
   * @param s the size of the shape
   * @param c the color of the shape
   * @param r the rotation of the shape
   */
  public ShapeImpl(String name, Position2D p, Size s, Color c, double r)
      throws IllegalArgumentException {
    this.setName(name);
    this.setPosition(p);
    this.setSize(s);
    this.setColor(c);
    this.setRotation(r);
    this.transitions = new ArrayList<>();
  }

  /**
   * Initialize a shape with zero size, default color, and no rotation
   */
  public ShapeImpl(String name) throws IllegalArgumentException {
    this(name, new Position2D(), new Size(), new Color(),0);
  }

  /**
   * Copy constructor
   * @param v
   */
  public ShapeImpl(ShapeImpl v) throws IllegalArgumentException {
    this(v.name, v.p, v.s, v.c, v.r);
  }

  /**
   * Increments the state of the shape by one tick, and changes the shape according to its
   * transitions.
   */
  public void tick() {
    Transition t = transitions.get(0);

    p.setX(p.getX() + t.getDeltaX());
    p.setY(p.getY() + t.getDeltaY());

    s.setW(s.getW() + t.getDeltaWidth());
    s.setH(s.getH() + t.getDeltaHeight());

    c.setR(c.getR() + t.getDeltaR());
    c.setG(c.getG() + t.getDeltaG());
    c.setB(c.getB() + t.getDeltaB());

    r += t.getDeltaRotation();

    if (t.getTimeToLive() > 1) {
      t.slowKill();
    } else {
      transitions.remove(0);
    }
  }

  /**
   * Return whether the stack still has more transitions.
   */
  public boolean hasTransition() {
    return !transitions.isEmpty();
  }

  /**
   * Add a new transition to the shape model.
   */
  public void addTransition(Transition t) {
    transitions.add(t);
  }

  /**
   * Pop the last added transition from the shape model.
   */
  public void popTransition() {
    transitions.remove(transitions.size() - 1);
  }

  /**
   * Get the shape's transition list.
   * @return r
   */
  public List<Transition> getTransitionList() {
    return this.transitions;
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
   * @throws IllegalArgumentException when name is invalid
   */
  public void setName(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Invalid shape name");
    }
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
   * Get the shape's size.
   * @return s
   */
  public Size getSize() {
    return s;
  }

  /**
   * Set the shape's size.
   * @param s
   */
  public void setSize(Size s) {
    this.s = s;
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
   * Get the shape type
   * @return
   */
  public String getShapeType() {
    return this.shapeType;
  }
}
