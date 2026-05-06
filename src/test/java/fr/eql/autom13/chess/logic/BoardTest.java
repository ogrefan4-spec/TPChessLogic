package fr.eql.autom13.chess.logic;

import fr.eql.autom13.chess.logic.pieces.Queen;
import fr.eql.autom13.chess.logic.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void forNewGameShouldReturnNonNullBoard() {
        Board board = Board.forNewGame();
        assertNotNull(board);
    }

    @Test
    void emptyShouldReturnNonNullBoard() {
        Board board = Board.empty();
        assertNotNull(board);
    }

    @Test
    void emptyAllCellsShouldBeNull() {
        Board board = Board.empty();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                assertNull(board.get(new Coordinates(row, col)),
                        "Les cellules (" + row + "," + col + ") devraient être null sur un échiquier vide");
            }
        }
    }

    @Test
    void moveShouldMovePieceToDestination() {
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates from = new Coordinates(0, 0);
        Coordinates to = new Coordinates(0, 5);

        board.placePiece(from, rook);
        board.move(from, to);

        assertSame(rook, board.get(to));
    }

    @Test
    void moveShouldClearSourceCell() {
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates from = new Coordinates(0, 0);
        Coordinates to = new Coordinates(0, 5);

        board.placePiece(from, rook);
        board.move(from, to);

        assertNull(board.get(from));
    }

    @Test
    void moveShouldCaptureOpponentPiece() {
        Board board = Board.empty();
        Piece whiteRook = new Rook(PlayerColour.WHITE);
        Piece blackRook = new Rook(PlayerColour.BLACK);
        Coordinates from = new Coordinates(3, 0);
        Coordinates to = new Coordinates(3, 4);

        board.placePiece(from, whiteRook);
        board.placePiece(to, blackRook);
        board.move(from, to);

        assertSame(whiteRook, board.get(to));
        assertNull(board.get(from));
    }

    @Test
    void placePieceShouldPlacePieceAtGivenCoordinates() {
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 4);

        board.placePiece(coords, rook);

        assertSame(rook, board.get(coords));
    }

    @Test
    void placePieceShouldOverwriteExistingPiece() {
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 4);

        board.placePiece(coords, rook);
        board.placePiece(coords, queen);

        assertSame(queen, board.get(coords));
    }

    @Test
    void placePieceOtherCellsShouldRemainsUnchanged () {
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        board.placePiece(new Coordinates(3, 4), rook);

        assertNull(board.get(new Coordinates(7, 7)));
        assertNull(board.get(new Coordinates(3, 5)));

    }

}