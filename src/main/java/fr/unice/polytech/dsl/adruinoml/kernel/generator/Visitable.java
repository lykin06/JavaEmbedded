package fr.unice.polytech.dsl.adruinoml.kernel.generator;

/**
 * Created by mathieu on 12/22/15.
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
