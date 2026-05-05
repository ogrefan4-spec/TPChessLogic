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
        return new ArrayList<>();
    }
}