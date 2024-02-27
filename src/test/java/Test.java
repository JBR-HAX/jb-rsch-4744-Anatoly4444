import org.jetbrains.assignment.controller.Controller;
import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.model.Move;
import org.jetbrains.assignment.model.Position;

import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    public void testCalcPositions() {
//        [{"direction":"EAST","steps":1},{"direction":"NORTH","steps":3},{"direction":"EAST","steps":3},
//         {"direction":"SOUTH","steps":5},{"direction":"WEST","steps":2}]
        List<Move> moves = List.of(new Move(Direction.EAST, 1),
                new Move(Direction.NORTH, 3),
                new Move(Direction.EAST, 3),
                new Move(Direction.SOUTH, 5),
                new Move(Direction.WEST, 2)
        );
        List<Position> positions = Controller.calcPositions(moves);
        System.out.println(positions);
        //[{"x":0,"y":0},{"x":1,"y":0},{"x":1,"y":3},{"x":4,"y":3},{"x":4,"y":-2},{"x":2,"y":-2}]
    }
}
