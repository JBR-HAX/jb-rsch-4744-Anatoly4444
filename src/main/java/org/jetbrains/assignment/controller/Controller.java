package org.jetbrains.assignment.controller;

import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.model.Move;
import org.jetbrains.assignment.model.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @PostMapping(path = "/locations")
    public ResponseEntity<List<Position>> locations(@RequestBody List<Move> movements) {
        List<Position> positions = calcPositions(movements);
        return ResponseEntity.ok(positions);
    }

    @PostMapping(path = "/moves")
    public ResponseEntity<List<Move>> moves(@RequestBody List<Position> positions) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            if(positions.size() - 1 == i)
                break;
            Position position1 = positions.get(i);
            Position position2 = positions.get(i + 1);
            if(position1.x() != position2.x()) {
                if(position2.x() > position1.x()) {
                    Move move = new Move(Direction.NORTH, position2.x() - position1.x());
                    moves.add(move);
                } else {
                    Move move = new Move(Direction.SOUTH, position1.x() - position2.x());
                    moves.add(move);
                }
            }
            if(position1.y() != position2.y()) {
                if(position2.y() > position1.y()) {
                    Move move = new Move(Direction.EAST, position2.y() - position1.y());
                    moves.add(move);
                } else {
                    Move move = new Move(Direction.WEST, position1.y() - position2.y());
                    moves.add(move);
                }
            }
        }
        return ResponseEntity.ok(moves);
    }

    public static List<Position> calcPositions(List<Move> movements) {
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
