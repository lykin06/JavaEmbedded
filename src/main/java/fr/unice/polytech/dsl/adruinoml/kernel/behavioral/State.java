package fr.unice.polytech.dsl.adruinoml.kernel.behavioral;

import fr.unice.polytech.dsl.adruinoml.kernel.NamedElement;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitable;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathieu on 12/22/15.
 */
public class State implements Visitable, NamedElement {
    private String name;
    private List<Action> actions = new ArrayList<Action>();
    private Transition transition;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
