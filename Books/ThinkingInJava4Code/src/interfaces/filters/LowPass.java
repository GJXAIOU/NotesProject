//: interfaces/filters/LowPass.java
package filters;


public class LowPass extends filters.Filter {
  double cutoff;
  public LowPass(double cutoff) { this.cutoff = cutoff; }
  @Override
  public Waveform process(Waveform input) {
    return input; // Dummy processing
  }
} ///:~
