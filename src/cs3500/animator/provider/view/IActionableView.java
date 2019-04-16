package cs3500.animator.provider.view;

import java.awt.event.ActionListener;

/**
 * The IActionableView interface is a layer on top of IView which describes views that handle
 * user interaction. To listen in on events, your controller (the class which contains the view)
 * should call view.setListener(this)
 */
public interface IActionableView extends IView {

  void setListener(ActionListener listener);

}
