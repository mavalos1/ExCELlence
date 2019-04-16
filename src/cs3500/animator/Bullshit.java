package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.ProviderModelAdapter;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.provider.view.ReadOnlyExCELenceAnimatorModel;
import cs3500.animator.provider.view.VisualView;

public class Bullshit {

	public static void main(String[] args) {
		AnimationModel model = new Model();
		Rectangle r = new Rectangle("R");
		model.addShape(r);

		model.addKeyFrame("R", 0, 0,0,0,0,0,0,0);
		model.addKeyFrame("R", 300, 300, 300, 300, 300, 300, 300, 300);

		ReadOnlyExCELenceAnimatorModel rm = new ProviderModelAdapter(model);
		VisualView v = new VisualView(rm);
		v.showView();
	}
}
