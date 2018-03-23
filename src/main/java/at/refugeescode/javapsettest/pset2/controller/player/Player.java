package at.refugeescode.javapsettest.pset2.controller.player;

import at.refugeescode.javapsettest.pset2.model.move.Move;

public interface Player {

    Move getMove();

    boolean wantsToPlayAgain();

}
