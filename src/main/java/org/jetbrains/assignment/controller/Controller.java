package org.jetbrains.assignment.controller;

import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.model.Move;
import org.jetbrains.assignment.model.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @PostMapping(path = "/locations")
    public ResponseEntity<List<Position>> addMovement(List<Move> movements) {
        List<Position> positions = calcPositions(movements);
        return ResponseEntity.ok(positions);
    }

    private static List<Position> calcPositions(List<Move> movements) {
        Position initialPosition = new Position(0,0);
        List<Position> positions = new ArrayList<>();
        positions.add(initialPosition);
        Position currentPosition = initialPosition;
        for (Move move : movements) {
            int x = currentPosition.x();
            int y = currentPosition.y();
            if(move.direction() == Direction.EAST) {
                y = y + move.steps();
            } else if(move.direction() == Direction.NORTH) {
                x = x + move.steps();
            } else if(move.direction() == Direction.SOUTH) {
                x = x - move.steps();
            } else if(move.direction() == Direction.WEST) {
                y = y - move.steps();
            }
            else throw new IllegalArgumentException();
            Position newPosition = new Position(x, y);
            positions.add(newPosition);
            currentPosition = newPosition;
        }
        return positions;
    }
}
