package at.refugeescode.javapsettest.pset2.model.move;

public class Spock implements Move {

    Spock() {
    }

    @Override
    public boolean defeats(Move move) {
        return move.getClass() == Scissors.class || move.getClass() == Rock.class;
    }
}
