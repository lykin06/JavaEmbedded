package fr.unice.polytech.dsl.adruinoml.kernel.structural;

import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitor;

/**
 * Created by mathieu on 12/22/15.
 */
public class Sensor extends Brick {
    public Sensor(String name, int pin) {
        super();
        super.setName(name);
        super.setPin(pin);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
