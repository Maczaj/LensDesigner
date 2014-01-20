package mma.pszt.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Maciej Jagiello (maciej.jagiello@codilime.com)
 */
public class ModelTest {
    @Test
    public void testSelectionBestHalf() {
        Lens l1 = Mockito.mock(Lens.class);
        Lens l2 = Mockito.mock(Lens.class);
        Lens l3 = Mockito.mock(Lens.class);
        Lens l4 = Mockito.mock(Lens.class);
        Lens l5 = Mockito.mock(Lens.class);
        Mockito.when(l1.getScore()).thenReturn(3);
        Mockito.when(l2.getScore()).thenReturn(2);
        Mockito.when(l3.getScore()).thenReturn(5);
        Mockito.when(l4.getScore()).thenReturn(1);
        Mockito.when(l5.getScore()).thenReturn(7);
        Set set1 = new HashSet<Lens>();
        set1.add(l1);
        set1.add(l2);
        set1.add(l3);
        set1.add(l4);
        set1.add(l5);
        Set set2 = new HashSet<Lens>();
        set2.add(l2);
        set2.add(l4);
        Model model = new Model();
        Assert.assertEquals(model.selectionBestHalf(set1), set2);
    }

    @Test
    public void testMiscegenation() {
        Lens l1 = Mockito.mock(Lens.class);
        Lens l2 = Mockito.mock(Lens.class);
        Model model = new Model();
        Set set1 = new HashSet<Lens>();
        set1.add(l1);
        set1.add(l2);
        model.miscegenation(set1);
    }
}
