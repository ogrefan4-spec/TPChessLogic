package fr.eql.autom13.chess.logic.pieces;

import fr.eql.autom13.chess.logic.AbstractPiece;
import fr.eql.autom13.chess.logic.Board;
import fr.eql.autom13.chess.logic.Coordinates;
import fr.eql.autom13.chess.logic.Move;
import fr.eql.autom13.chess.logic.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

        for (int[] dir : directions) {
            Coordinates to = from.plus(dir[0], dir[1]);
            if (isInBounds(to) && (board.get(to) == null || board.get(to).getColour() != getColour())) {
                moves.add(new Move(from, to));
            }
        }
        return moves;
    }
}