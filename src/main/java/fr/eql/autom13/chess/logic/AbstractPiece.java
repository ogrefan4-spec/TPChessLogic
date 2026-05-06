package fr.eql.autom13.chess.logic;

import fr.eql.autom13.chess.logic.Piece;

import java.util.List;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    protected boolean isInBounds(Coordinates c) {
        return c.getRow() >= 0 && c.getRow() < 8
                && c.getCol() >= 0 && c.getCol() < 8;
    }

    // Pour les pièces qui glissent (fou, tour, reine)
    protected void addSlidingMoves(List<Move> moves, Coordinates from, Board board, int[][] directions) {
        for (int[] dir : directions) {
            Coordinates to = from.plus(dir[0], dir[1]);
            while (isInBounds(to)) {
                if (board.get(to) == null) {
                    moves.add(new Move(from, to));
                } else {
                    if (board.get(to).getColour() != getColour()) {
                        moves.add(new Move(from, to)); // capture
                    }
                    break; // pièce alliée ou ennemie bloque la suite
                }
                to = to.plus(dir[0], dir[1]);
            }
        }
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }
}