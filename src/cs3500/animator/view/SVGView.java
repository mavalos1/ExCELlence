package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.helper.Transition;
import cs3500.animator.model.shapes.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SVGView extends ViewImpl {
  StringBuilder svgOutput;

  public SVGView(int x, int y, int h, int w, int tickPerSecond, AnimationModel model) {
    super(x, y, h, w, tickPerSecond, model);
    svgOutput = new StringBuilder();
  }

  private String renderToSVG() {
    svgOutput.append(
        String.format(
          "<svg width=\"%.0f\" height=\"%.0f\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n",
          this.canvasSize.getW(), this.canvasSize.getH()
        )
    );

    List<Shape> shapes = model.getAllShapes();
    for (Shape s : shapes) {
      svgOutput.append(s.SVGHeader());
      svgOutput.append(s.SVGTransition((int) 1000 / tPs));
      svgOutput.append(s.SVGFooter());
    }
    svgOutput.append("\n</svg>");
    return svgOutput.toString();
  }

  public void render() {
    String fileContent = renderToSVG();

    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter("out.svg"));
      writer.write(fileContent);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
