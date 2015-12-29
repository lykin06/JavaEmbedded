package fr.unice.polytech.dsl.builders;

import fr.unice.polytech.dsl.adruinoml.kernel.App;
import fr.unice.polytech.dsl.adruinoml.kernel.behavioral.State;
import fr.unice.polytech.dsl.adruinoml.kernel.generator.ToWiring;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Actuator;
import fr.unice.polytech.dsl.adruinoml.kernel.structural.Brick;
import fr.unice.polytech.dsl.builders.behavioral.StateBuilder;
import fr.unice.polytech.dsl.builders.behavioral.TransitionBuilder;
import fr.unice.polytech.dsl.builders.structural.BrickBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathieu on 12/22/15.
 */
public class AppBuilder {
    private String name;
    private List<BrickBuilder> bricks = new ArrayList<>();
    private List<StateBuilder> states = new ArrayList<>();

    public AppBuilder(String name) {
        this.name = name;
    }

    public BrickBuilder addBrick(String name) {
        BrickBuilder child = new BrickBuilder(name);
        bricks.add(child);
        return child;
    }

    public StateBuilder addState(String name) {
        StateBuilder child = new StateBuilder(this, name);
        states.add(child);
        return child;
    }

    public TransitionBuilder addTransition() {
        return new TransitionBuilder(this);
    }

    public App toApp() {
        App app = new App(name);

        List<Brick> b = new ArrayList<>();
        for (BrickBuilder bb : bricks) {
            b.add(bb.toBrick());
        }
        app.setBricks(b);

        List<State> s = new ArrayList<>();
        for (StateBuilder sb : states) {
            State state = sb.toState();
            s.add(state);
            if (sb.isInitialState()) {
                app.setInitial(state);
            }
        }
        app.setStates(s);

        return app;
    }

    public void exportToWiring() {
        App app = this.toApp();
        ToWiring codeGen = new ToWiring();
        app.accept(codeGen);
        System.out.println(codeGen.getResult());
    }

    public Actuator getActuator(String actuatorName) {
        for(BrickBuilder b : bricks) {
            if(b.getName().equals(actuatorName)) {
                return new Actuator(b.getName(), b.getPin());
            }
        }
        return null;
    }

    public StateBuilder getStateBuilder(String stateName) {
        for(StateBuilder s : states) {
            if(s.getName().equals(stateName)) {
                return s;
            }
        }
        return null;
    }

    public StateBuilder addTransitionToState(TransitionBuilder transition, String name) {
        StateBuilder s = getStateBuilder(name);
        if (s != null) {
            s.setTransition(transition);
        }
        return s;
    }

    public BrickBuilder getBrickBuilder(String brickName) {
        for(BrickBuilder b : bricks) {
            if(b.getName().equals(brickName)) {
                return b;
            }
        }
        return null;
    }
}
