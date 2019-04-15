package cs3500.animator.provider.view;

/**
 * This interface represents all the methods shared by all controllers for an animation.
 */
public interface IController {

  /**
   * Displays the view from the start of an animation.
   */
  void go();

  /**
   * Sets the tick rate for an animation.
   *
   * @param ticksPerSecond number of ticks per second
   */
  void setPlaybackTickrate(Integer ticksPerSecond);

}
