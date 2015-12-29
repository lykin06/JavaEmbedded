package fr.unice.polytech.dsl.adruinoml.kernel.generator;

import fr.unice.polytech.dsl.adruinoml.kernel.App;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Action;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.State;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Transition;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Sensor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mathieu on 12/22/15.
 */
public abstract class Visitor<T> {
    public abstract void visit(App app);

    public abstract void visit(State state);
    public abstract void visit(Transition transition);
    public abstract void visit(Action action);

    public abstract void visit(Actuator actuator);
    public abstract void visit(Sensor sensor);


    /***********************
     ** Helper mechanisms **
     ***********************/

    protected Map<String,Object> context = new HashMap<>();

    protected T result;

    public T getResult() {
        return result;
    }
}
