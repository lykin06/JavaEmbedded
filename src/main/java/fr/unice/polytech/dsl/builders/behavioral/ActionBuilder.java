package fr.unice.polytech.dsl.builders.behavioral;

import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Action;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.SIGNAL;

/**
 * Created by mathieu on 12/24/15.
 */
public class ActionBuilder {
    private StateBuilder stateBuilder;
    private Actuator actuator;
    private SIGNAL signal;

    public ActionBuilder(StateBuilder stateBuilder, Actuator actuator) {
        this.stateBuilder = stateBuilder;
        this.actuator = actuator;
    }

    public StateBuilder toHigh() {
        this.signal = SIGNAL.HIGH;
        stateBuilder.addAction(this.toAction());
        return stateBuilder;
    }

    public StateBuilder toLow() {
        this.signal = SIGNAL.LOW;
        stateBuilder.addAction(this.toAction());
        return stateBuilder;
    }

    public Action toAction() {
        Action a = new Action();
        a.setActuator(actuator);
        a.setValue(signal);
        return a;
    }
}
