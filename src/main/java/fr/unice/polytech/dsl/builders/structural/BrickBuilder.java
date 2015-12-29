package fr.unice.polytech.dsl.builders.structural;

import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Brick;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Sensor;

/**
 * Created by mathieu on 12/22/15.
 */
public class BrickBuilder {
    private String name;
    private BRICKTYPE type;
    private int pin;

    public BrickBuilder(String name) {
        this.name = name;
        this.type = BRICKTYPE.UNKOWN;
        this.pin = -1;
    }

    public BrickBuilder aSensor() {
        this.type = BRICKTYPE.SENSOR;
        return this;
    }

    public BrickBuilder anActuator() {
        this.type = BRICKTYPE.ACTUATOR;
        return this;
    }

    public BrickBuilder boundToPin(int pin) {
        this.pin = pin;
        return this;
    }

    public Brick toBrick() {
        switch (type) {
            case ACTUATOR: return new Actuator(name, pin);
            case SENSOR: return new Sensor(name, pin);
            default: throw new UnsupportedOperationException("Undefined Brick Type");
        }
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }
}
