package cs3500.animator;

import cs3500.animator.provider.adaptedController.ProviderControllerAdapter;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Model;
import cs3500.animator.model.shapes.Rectangle;
import cs3500.animator.provider.adaptedModel.ProviderModelAdapter;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.ExCELenceAnimatorModel;

public class Bullshit {

	public static void main(String[] args) {
		AnimationModel model = new Model();
		Rectangle r = new Rectangle("R");
		model.addShape(r);

		model.addKeyFrame("R", 0, 0,0,100,100,20,40,70);
		model.addKeyFrame("R", 300, 300, 300, 300, 300, 200, 75, 145);

		ExCELenceAnimatorModel rm = new ProviderModelAdapter(model);
		EditorView v = new EditorView(rm);

		ProviderControllerAdapter ct = new ProviderControllerAdapter(rm, v);
		ct.go();
	}
}
