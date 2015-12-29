package fr.unice.polytech.dsl.adruinoml.kernel.structural;

import fr.unice.polytech.dsl.adruinoml.kernel.NamedElement;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitable;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitor;

/**
 * Created by mathieu on 12/22/15.
 */
public abstract class Brick implements Visitable, NamedElement {
    private String name;
    private int pin;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
