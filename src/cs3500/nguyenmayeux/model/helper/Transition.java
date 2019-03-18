package cs3500.nguyenmayeux.model.helper;

import java.util.Objects;

/**
 * This class specifies the operation of a transition.
 * <p>
 *   A transition is characterized by the difference in position, color, height, width, rotation
 *   that is expected in the animation within a certain time window.
 * </p>
 */
public class Transition {
  private Position2D dp;
  private int dr;
  private int dg;
  private int db;
  private double dh;
  private double dw;
  private double drt;
  private int t;

  /**
   * Initialize the transition to the specified parameters
   * @param deltaPosition the change in position of an animation, (0, 0) if unchanged
   * @param deltaR the change in R-color code of an animation, 0 if unchanged
   * @param deltaG the change in G-color code of an animation, 0 if unchanged
   * @param deltaB the change in B-color code of an animation, 0 if unchanged
   * @param deltaHeight the change in height of an animation, 0 if unchanged
   * @param deltaWidth the change in width of an animation, 0 if unchanged
   * @param deltaRotation the change in rotation of an animation, 0 if unchanged
   * @param timeToLive the amount of time that the animation will occur
   * @throws IllegalArgumentException when time to live is under 1 tick
   */
  public Transition(
      Position2D deltaPosition, int deltaR, int deltaG, int deltaB,
      double deltaHeight, double deltaWidth,
      double deltaRotation, int timeToLive) throws IllegalArgumentException {
    this.setDeltaPosition(deltaPosition);
    this.setDeltaR(deltaR);
    this.setDeltaG(deltaG);
    this.setDeltaB(deltaB);
    this.setDeltaHeight(deltaHeight);
    this.setDeltaWidth(deltaWidth);
    this.setDeltaRotation(deltaRotation);
    this.setTimeToLive(timeToLive);
  }

  /**
   * Initialize a default transition that does nothing to the shape for an amount of time.
   * @param time
   */
  public Transition(int time) throws IllegalArgumentException {
    this(new Position2D(), 0, 0, 0, 0, 0, 0, time);
  }

  /**
   * Copy constructor.
   * @param v
   */
  public Transition(Transition v) throws IllegalArgumentException {
    this(v.dp, v.dr, v.dg, v.db, v.dh, v.dw, v.drt, v.t);
  }

  /**
   * Get the deltaPosition of this transition.
   * @return dp
   */
  public Position2D getDeltaPosition() {
    return this.dp;
  }

  /**
   * Set the deltaPosition of this transition.
   * @param p
   */
  public void setDeltaPosition(Position2D p) {
    this.dp = p;
  }

  /**
   * Get the delta R-code of this transition.
   * @return dr
   */
  public int getDeltaR() {
    return this.dr;
  }

  /**
   * Set the delta R-code of this transition.
   * @param r
   */
  public void setDeltaR(int r) {
    this.dr = r;
  }

  /**
   * Get the delta G-code of this transition.
   * @return dg
   */
  public int getDeltaG() {
    return this.dg;
  }

  /**
   * Set the delta G-code of this transition.
   * @param g
   */
  public void setDeltaG(int g) {
    this.dg = g;
  }

  /**
   * Get the delta B-code of this transition.
   * @return db
   */
  public int getDeltaB() {
    return this.db;
  }

  /**
   * Set the delta B-code of this transition.
   * @param b
   */
  public void setDeltaB(int b) {
    this.db = b;
  }

  /**
   * Get the delta height of this transition.
   * @return dh
   */
  public double getDeltaHeight() {
    return this.dh;
  }

  /**
   * Set the delta height of this transition.
   * @param h
   */
  public void setDeltaHeight(double h) {
    this.dh = h;
  }

  /**
   * Get the delta width of this transition.
   * @return dw
   */
  public double getDeltaWidth() {
    return this.dw;
  }

  /**
   * Set the delta width of this transition.
   * @param w
   */
  public void setDeltaWidth(double w) {
    this.dw = w;
  }

  /**
   * Get the delta rotation of this transition.
   * @return dr
   */
  public double getDeltaRotation() {
    return this.drt;
  }

  /**
   * Set the delta rotation of this transition.
   * @param rt
   */
  public void setDeltaRotation(double rt) {
    this.drt = rt;
  }

  /**
   * Get the time to live of this transition.
   * @return dt
   */
  public int getTimeToLive() {
    return this.t;
  }

  /**
   * Set the time to live of this transition.
   * @param t
   * @throws IllegalArgumentException when tick is under 1 tick
   */
  public void setTimeToLive(int t) throws IllegalArgumentException {
    if (t < 1) {
      throw new IllegalArgumentException("Invalid transition time");
    }

    this.t = t;
  }

  /**
   * Decrement the time to live of this transition.
   */
  public void slowKill() {
    t--;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a)  { return true; }
    if (!(a instanceof Transition)) { return false; }

    Transition that = (Transition) a;

    return (this.dp == that.dp
        && this.dr == that.dr
        && this.dg == that.dg
        && this.db == that.db
        && Math.abs(this.dw - that.dw) < 0.01
        && Math.abs(this.dh - that.dh) < 0.01
        && Math.abs(this.drt - that.drt) < 0.01
        && this.t == that.t);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.dp, this.dr, this.dg, this.db, this.dw, this.dh, this.drt, this.t);
  }
}
