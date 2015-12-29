package fr.unice.polytech.dsl.builders.behavioral;

import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.State;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Transition;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Brick;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.SIGNAL;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Sensor;
import fr.unice.polytech.dsl.builders.AppBuilder;
import fr.unice.polytech.dsl.builders.structural.BrickBuilder;

/**
 * Created by mathieu on 12/24/15.
 */
public class TransitionBuilder {
    private AppBuilder app;
    private State next;
    private BrickBuilder sensor;
    private SIGNAL value;

    public TransitionBuilder(AppBuilder app) {
        this.app = app;
    }

    public TransitionBuilder from(String stateName) {
        app.addTransitionToState(this, stateName);
        return this;
    }

    public TransitionBuilder to(String stateName) {
        StateBuilder sb = app.getStateBuilder(stateName);
        next = new State();
        next.setName(sb.getName());
        return this;
    }

    public TransitionBuilder when(String sensorName) {
        sensor = app.getBrickBuilder(sensorName);
        return this;
    }

    public TransitionBuilder isHigh() {
        value = SIGNAL.HIGH;
        return this;
    }

    public Transition toTransition() {
        Transition t = new Transition();
        t.setNext(next);
        Brick b = sensor.toBrick();
        t.setSensor(new Sensor(b.getName(), b.getPin()));
        t.setValue(value);
        return t;
    }
}
