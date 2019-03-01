package cs3500.ExCELlence.model;

public class Color {
  private int r;
  private int b;
  private int g;

  public Color() {
    this.setR(0);
    this.setG(0);
    this.setB(0);
  }

  public Color(int r, int g, int b) {
    this.setR(r);
    this.setG(g);
    this.setB(b);
  }

  public int getR() {
    return r;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }

  public int getG() {
    return g;
  }

  public void setG(int g) {
    this.g = g;
  }
}
