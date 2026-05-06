package fr.eql.autom13.chess.logic.pieces;

import fr.eql.autom13.chess.logic.AbstractPiece;
import fr.eql.autom13.chess.logic.Board;
import fr.eql.autom13.chess.logic.Coordinates;
import fr.eql.autom13.chess.logic.Move;
import fr.eql.autom13.chess.logic.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        int[][] jumps = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};

        for (int[] jump : jumps) {
            Coordinates to = from.plus(jump[0], jump[1]);
            if (isInBounds(to) && (board.get(to) == null || board.get(to).getColour() != getColour())) {
                moves.add(new Move(from, to));
            }
        }
        return moves;
    }
}