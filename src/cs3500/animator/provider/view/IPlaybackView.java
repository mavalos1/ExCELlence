package cs3500.animator.provider.view;

/**
 * This interface represents all the operations offered by a views of an animation that have the
 * ability to pause, play, and restart themselves during a viewing of an animation.
 */
public interface IPlaybackView extends IView {

  /**
   * Pauses an animation when called.
   */
  void pauseAnimation();

  /**
   * Plays an animation when called.
   */
  void playAnimation();

  /**
   * Restarts an animation when called.
   */
  void restartAnimation();

  /**
   * Plays an animation starting at the given tick.
   *
   * @param tick the time at which the animation will start playing
   */
  void startAnimationFromTick(Integer tick);

  /**
   * Loops an animation (replays it indefinitley).
   *
   * @param loop a boolean. Set to true if you want the animation to loop or false if you don't.
   */
  void setLoop(boolean loop);

}
