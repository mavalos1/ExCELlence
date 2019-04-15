package cs3500.animator.provider.view;

/**
 * This interface represents all the methods used by an attribute of a shape. Examples of attributes
 * are dimensions, positions, and color.
 */
public interface Attribute {

  Attribute scale(Double factor);

  Attribute add(Attribute other);

  Attribute copy();

}
