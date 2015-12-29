package fr.unice.polytech.dsl.adruinoml.kernel;

import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.State;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitable;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.Visitor;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Brick;

import java.util.ArrayList;
import java.util.List;

public class App implements Visitable, NamedElement {
    private String name;
    private List<Brick> bricks;
    private List<State> states;
    private State initial;

    public App(String name) {
        this.name = name;
        this.bricks = new ArrayList<>();
        this.states = new ArrayList<>();
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
