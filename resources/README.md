# nguyenmayeux animation program

The program is structured as a Model-View-Controller application.

The model represents a the data within an animation, which we structured as series of shapes, each of which contains a series of changes that it would need to make unto itself when called upon a time tick. This model allows the ability to add new shape, get a shape by name, and get all the shapes in the model. 

It can advance itself using the tick() method, making the shapes reaches the next time frame.

The view represents a series of outputs that we can represents the animation in. As of now, we have a textual view, which would render the animation as a series text containing data of a shape at a frame from start to end; a SVG view, which will render the animation into SVG-style code; and a visual view, which will render the animation into a Java Swing JPanel. Each of this view has the option to write to a file or to the console its output.

The controller is the mediator between the model and the view. It prompts the model to advance, and inform the view to render. This controller structure avoid passing the whole model to the view itself, making it that the model is untouched by the view, and that multiple view could be representing the same model through the same controller behaviours.