package mma.pszt.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Maciej Jagiello (maciej.jagiello@codilime.com)
 */
public class ModelTest {
    @Test
    public void testNextIteration() {
        Model model = new Model();
        System.out.println(model.getLens().toString());
        for (int i = 0; i < 100; i++) {
            model.nextIteration();
            System.out.println(model.getLens().toString());
        }
    }
}
