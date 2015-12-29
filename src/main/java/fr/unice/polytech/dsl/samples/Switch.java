package fr.unice.polytech.dsl.samples;

import fr.unice.polytech.dsl.builders.AppBuilder;

/**
 * Created by mathieu on 12/22/15.
 */
public class Switch {
    public static void main(String args[]) {
        AppBuilder theSwitch = new AppBuilder("Switch");

        // Declaring the bricks involved in the application
        theSwitch.addBrick("BUTTON")
                .aSensor()
                .boundToPin(9);

        theSwitch.addBrick("LED")
                .anActuator()
                .boundToPin(12);

        // Declaring the two states used to support the behavior
        theSwitch.addState("on")
                .executing("LED")
                .toHigh();

        theSwitch.addState("off")
                .executing("LED")
                .toLow()

                // Setting the initial state
                .isInitial();

        // Declaring the transition system
        theSwitch.addTransition()
                .from("on")
                .to("off")
                .when("BUTTON")
                .isHigh();

        theSwitch.addTransition()
                .from("off")
                .to("on")
                .when("BUTTON")
                .isHigh();

        // Running the code generation tool
        theSwitch.exportToWiring();
    }
}
