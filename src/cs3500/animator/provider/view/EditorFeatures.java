package cs3500.animator.provider.view;

/**
 * This Interface represents all the features available to users who want to edit an animation.
 */
public interface EditorFeatures {

  /**
   * Creates a shape with a name and type.
   */
  void declareShape();

  /**
   * Removes a shape from an animation.
   */
  void removeShape();

  /**
   * Views a shape at the cureent keyframe of an animation that's playing.
   */
  void viewShape();

  /**
   * Adds a keyframe to an animation.
   */
  void addKeyframe();

  /**
   * Assigns a Shape to a keyframe in an animation.
   */
  void setKeyframe();

  /**
   * Removed a keyframe and it's associated shape from an animation.
   */
  void removeKeyframe();

  /**
   * Displays a shape at the current keyframe.
   */
  void viewKeyframeForShape();

}
