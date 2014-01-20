package mma.pszt.model;

import lombok.Getter;
import mma.pszt.utils.Parameters;

import java.util.Set;

/**
 * @author Maciej Jagiello
 */
@Getter
public class DataModel {

    private Set<Lens> generation;
    private Parameters parameters;
}
