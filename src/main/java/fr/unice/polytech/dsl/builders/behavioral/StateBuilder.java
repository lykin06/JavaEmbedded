package fr.unice.polytech.dsl.builders.behavioral;

import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.Action;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.State;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Brick;
import fr.unice.polytech.dsl.builders.AppBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathieu on 12/24/15.
 */
public class StateBuilder {
    private AppBuilder app;
    private String name;
    private List<Action> actions;
    private boolean initial;
    private TransitionBuilder transition;

    public StateBuilder(AppBuilder app, String name) {
        this.app = app;
        this.name = name;
        this.actions = new ArrayList<>();
        this.initial = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransition(TransitionBuilder transition) {
        this.transition = transition;
    }

    public ActionBuilder executing(String actuatorName) {
        Actuator actuator = app.getActuator(actuatorName);
        return new ActionBuilder(this, actuator);
    }

    public StateBuilder isInitial() {
        initial = true;
        return this;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public State toState() {
        State s = new State();
        s.setName(name);
        s.setActions(actions);
        s.setTransition(transition.toTransition());
        return s;
    }

    public boolean isInitialState() {
        return initial;
    }
}
