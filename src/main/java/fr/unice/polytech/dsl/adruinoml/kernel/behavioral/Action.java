package fr.unice.polytech.dsl.adruinoml.kernel.behavioral;

import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitable;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitor;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.SIGNAL;

/**
 * Created by mathieu on 12/24/15.
 */
public class Action implements Visitable {
    private SIGNAL value;
    private Actuator actuator;

    public SIGNAL getValue() {
        return value;
    }

    public void setValue(SIGNAL value) {
        this.value = value;
    }

    public Actuator getActuator() {
        return actuator;
    }

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
