package cs3500.animator;

import java.io.FileReader;
import java.io.IOException;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationBuilderImpl;
import cs3500.animator.util.AnimationReader;

/**
 * The animation main method.
 *
 * <p>The main method could be called with the following parameters that needs to come with an
 *   string to specify:
 *   <p>
 *     -in "name-of-animation-file": the input file to read from
 *     -view "type-of-view": the type of view to render the animation to
 *     -out "where-output-show-go": the output file to render into (optional)
 *     -speed "integer-ticks-per-second": the speed at which to render the animation at (optional)
 *   </p>
 *   <p>
 *     The input file and view type are always required. When output file is not specified, the
 *     view is renderd into the system console. When the speed is not specified, the default value
 *     is 1.
 *   </p>
 *   <p>
 *     The paramters could be added by any order.
 *   </p>
 * </p>
 */
public class Excellence {
  /**
   * The main method to start the program.
   * @param args arguments to specify in out view and speed
   */
  public static void main(String[] args) {
    String inFile = "";
    String outFile = "";
    String viewType = "";
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          inFile = args[i + 1];
          break;
        case "-out":
          outFile = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-speed":
          speed = Integer.parseInt(args[i + 1]);
          break;
        default:
          continue;
      }

      i++;
    }

    if (viewType.equals("") || inFile.equals("")) {
      throw new IllegalArgumentException("Input file and view type is not provided");
    }

    AnimationBuilder a = new AnimationBuilderImpl(viewType, speed, outFile);

    try {
      Readable file = new FileReader(inFile);
      AnimationReader.parseFile(file, a);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return;
  }
}
