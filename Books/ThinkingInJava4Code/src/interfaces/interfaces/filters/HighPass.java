//: interfaces/filters/HighPass.java
package interfaces.filters;

import filters.Waveform;

public class HighPass extends filters.Filter {
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public Waveform process(Waveform input) {
        return input;
    }
} ///:~
