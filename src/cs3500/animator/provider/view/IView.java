package cs3500.animator.provider.view;

//This import was changed to accomodate for ReadOnlyExCELenceAnimatorModel location in provider.view
import cs3500.animator.provider.view.ReadOnlyExCELenceAnimatorModel;

/**
 * The interface for any view that displays an ExCELenceAnimatorModel.
 */
public interface IView {

  /**
   * Display this view via its prefered output channel. This can entail a file output, System.out,
   * JFrame, or other means.
   */
  void showView();

  /**
   * Set the model that this view will display.
   *
   * @param model The model to use for this view
   */
  void setModel(ReadOnlyExCELenceAnimatorModel model);

  /**
   * Set the playback speed in ticks per second.
   *
   * @param ticksPerSecond the playback speed.
   * @throws IllegalArgumentException if the given tickPerSecond is less than 1
   */
  void setSpeed(Integer ticksPerSecond);

}
