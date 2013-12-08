package mma.pszt.model;

import mma.pszt.utils.Parameters;

import java.util.Set;
import lombok.*;

/**
 * @author Maciej Jagiello
 */
@Getter
public class DataModel {

    private Set<Lens> generation;
    private Parameters parameters;
}
