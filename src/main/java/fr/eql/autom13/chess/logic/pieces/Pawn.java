package fr.eql.autom13.chess.logic.pieces;

import fr.eql.autom13.chess.logic.AbstractPiece;
import fr.eql.autom13.chess.logic.Board;
import fr.eql.autom13.chess.logic.Coordinates;
import fr.eql.autom13.chess.logic.Move;
import fr.eql.autom13.chess.logic.Piece;
import fr.eql.autom13.chess.logic.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        int direction = getColour() == PlayerColour.WHITE ? -1 : 1;
        int startRow  = getColour() == PlayerColour.WHITE ?  6 : 1;

        Coordinates oneStep = from.plus(direction, 0);
        if (isInBounds(oneStep) && board.get(oneStep) == null) {
            moves.add(new Move(from, oneStep));

            Coordinates twoStep = from.plus(direction * 2, 0);
            if (from.getRow() == startRow && board.get(twoStep) == null) {
                moves.add(new Move(from, twoStep));
            }
        }

        for (int colDiff : new int[]{-1, 1}) {
            Coordinates diagonal = from.plus(direction, colDiff);
            if (isInBounds(diagonal) && board.get(diagonal) != null
                    && board.get(diagonal).getColour() != getColour()) {
                moves.add(new Move(from, diagonal));
            }
        }

        return moves;
    }

    private boolean isInBounds(Coordinates c) {
        return c.getRow() >= 0 && c.getRow() < 8
                && c.getCol() >= 0 && c.getCol() < 8;
    }
}