package cs3500.ExCELlence.model.transitions;

import cs3500.ExCELlence.model.Color;
import cs3500.ExCELlence.model.Position2D;

public class TransitionImpl implements Transition {
  private Position2D dp;
  private Color dc;
  private double dh;
  private double dw;
  private double dr;
  private int t;

  public TransitionImpl(
      Position2D deltaPosition, Color deltaColor,
      double deltaHeight, double deltaWidth,
      double deltaRotation, int timeToLive) {
    this.dp = deltaPosition;
    this.dc = deltaColor;
    this.dh = deltaHeight;
    this.dw = deltaWidth;
    this.dr = deltaRotation;
    this.t = timeToLive;
  }

  public Position2D getDeltaPosition() {
    return this.dp;
  }

  public Color getDeltaColor() {
    return this.dc;
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

  public void slowKill() {
    t--;
  }
}
