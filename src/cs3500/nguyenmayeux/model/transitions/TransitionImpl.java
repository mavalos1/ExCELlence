package cs3500.nguyenmayeux.model.transitions;

import cs3500.nguyenmayeux.model.Position2D;

public class TransitionImpl implements Transition {
  private Position2D dp;
  private int r;
  private int g;
  private int b;
  private double dh;
  private double dw;
  private double dr;
  private int t;
  private boolean hide;

  public TransitionImpl(
      Position2D deltaPosition, int deltaR, int deltaG, int deltaB,
      double deltaHeight, double deltaWidth,
      double deltaRotation, int timeToLive, boolean hide) {
    if (t < 0) {
      throw new IllegalArgumentException("Invalid transition time");
    }

    this.dp = deltaPosition;
    this.r = deltaR;
    this.g = deltaG;
    this.b = deltaB;
    this.dh = deltaHeight;
    this.dw = deltaWidth;
    this.dr = deltaRotation;
    this.t = timeToLive;
    this.hide = hide;
  }

  public Position2D getDeltaPosition() {
    return this.dp;
  }

  public int getDeltaR() {
    return this.r;
  }

  public int getDeltaG() {
    return this.g;
  }

  public int getDeltaB() {
    return this.b;
  }

  public double getDeltaHeight() {
    return this.dh;
  }

  public double getDeltaWidth() {
    return this.dw;
  }

  public double getDeltaRotation() {
    return this.dr;
  }

  public int getTimeToLive() {
    return this.t;
  }

  public boolean getHide() {
    return this.hide;
  }

  public void slowKill() {
    t--;
  }
}
