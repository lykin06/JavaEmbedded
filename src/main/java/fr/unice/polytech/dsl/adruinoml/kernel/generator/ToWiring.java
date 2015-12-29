package fr.unice.polytech.dsl.adruinoml.kernel.generator;

import fr.unice.polytech.dsl.adruinoml.kernel.App;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Action;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.State;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Transition;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Brick;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Sensor;

/**
 * Created by mathieu on 12/22/15.
 */
public class ToWiring extends Visitor<StringBuffer> {

    private final static String CURRENT_STATE = "current_state";

    public ToWiring() {
        this.result = new StringBuffer();
    }

    private void write(String s) {
        result.append(String.format("%s\n", s));
    }

    @Override
    public void visit(App app) {
        write("// Wiring code generated from an ArduinoML model");
        write(String.format("// Application name: %s\n", app.getName()));

        write("// Structural concepts");
        write("void setup() {");
        for (Brick brick : app.getBricks()) {
            brick.accept(this);
        }
        write("}\n");

        write("// Behavioral concepts");
        write("long time = 0; long debounce = 200;\n");

        for (State state : app.getStates()) {
            state.accept(this);
        }

        write("void loop() {");
        write(String.format("\tstate_%s();", app.getInitial().getName()));
        write("}");
    }

    @Override
    public void visit(Actuator actuator) {
        write(String.format("\tpinMode(%d, OUTPUT); // %s [Actuator]", actuator.getPin(), actuator.getName()));
    }

    @Override
    public void visit(Sensor sensor) {
        write(String.format("\tpinMode(%d, INPUT); // %s [Sensor]", sensor.getPin(), sensor.getName()));
    }

    @Override
    public void visit(State state) {
        write(String.format("void state_%s() {", state.getName()));
        for (Action action : state.getActions()) {
            action.accept(this);
        }
        write("\tboolean guard = millis() - time > debounce;");
        context.put(CURRENT_STATE, state);
        state.getTransition().accept(this);
        write("}\n");
    }

    @Override
    public void visit(Transition transition) {
        write(String.format("\tif( digitalRead(%d) == %s && guard ) {",
                transition.getSensor().getPin(), transition.getValue()));
        write("\t\ttime = millis();");
        write(String.format("\t\tstate_%s();", transition.getNext().getName()));
        write("\t} else {");
        write(String.format("\t\tstate_%s();", ((State) context.get(CURRENT_STATE)).getName()));
        write("\t}");
    }

    @Override
    public void visit(Action action) {
        write(String.format("\tdigitalWrite(%d,%s);",action.getActuator().getPin(),action.getValue()));
    }
}
