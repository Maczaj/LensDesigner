package mma.pszt.model;

import mma.pszt.utils.Parameters;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Maciej Jagiello (maciej.jagiello@codilime.com)
 */
public class ModelTest {
    @Test
    public void testNextIteration() {
        Model model = new Model(new Parameters(10, 5, 1.4, 1.0, 1.1));
        System.out.println(model.getLens().toString());
        for (int i = 0; i < 100; i++) {
            model.nextIteration();
            System.out.println(model.getLens().toString());
        }
    }
}
