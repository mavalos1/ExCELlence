package cs3500.ExCELlence.model.transitions;

import cs3500.ExCELlence.model.Color;
import cs3500.ExCELlence.model.Position2D;

public interface Transition {
  Position2D getDeltaPosition();
  int getDeltaR();
  int getDeltaG();
  int getDeltaB();
  double getDeltaHeight();
  double getDeltaWidth();
  double getDeltaRotation();
  int getTimeToLive();
  void slowKill();
}
