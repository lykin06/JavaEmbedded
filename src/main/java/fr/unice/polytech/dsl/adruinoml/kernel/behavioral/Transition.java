package fr.unice.polytech.dsl.adruinoml.kernel.behavioral;

import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitable;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitor;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.SIGNAL;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Sensor;

/**
 * Created by mathieu on 12/24/15.
 */
public class Transition implements Visitable {
    private State next;
    private Sensor sensor;
    private SIGNAL value;


    public State getNext() {
        return next;
    }

    public void setNext(State next) {
        this.next = next;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SIGNAL getValue() {
        return value;
    }

    public void setValue(SIGNAL value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
