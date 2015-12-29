package fr.unice.polytech.dsl.builders.structural;

import fr.unice.polytech.dsl.adruinoml.kernel.structural.SIGNAL;

/**
 * Created by mathieu on 12/24/15.
 */
public class Signal {
    private SIGNAL signal;

    public Signal(SIGNAL signal) {
        this.signal = signal;
    }

    public SIGNAL getSignal() {
        return signal;
    }
}
