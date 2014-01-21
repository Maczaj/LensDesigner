package mma.pszt.model;

import lombok.Getter;
import mma.pszt.utils.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maciej Jagiello
 */
@Getter
public class Ray {

    private List<Point> midPoints;

    public Ray(List<Point> midPoints) {
        this.midPoints = midPoints;
    }

}
