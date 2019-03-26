package cs3500.animator.view;

public interface EditorView extends AnimationView {
  void start();
  void pause();
  void restart();
  void toggleLooping(boolean loop);
  void adjustSpeed(int tickPerSecond);
}
