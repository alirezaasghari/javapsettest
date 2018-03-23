package at.refugeescode.javapsettest.pset2.controller;

import at.refugeescode.javapsettest.pset2.model.Result;
import at.refugeescode.javapsettest.pset2.model.move.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class JudgeTest {

    private Judge judge = new Judge();

    private Move move1;

    private Move move2;

    @BeforeEach
    public void before() {

        move1 = Mockito.mock(Move.class);
        move2 = Mockito.mock(Move.class);
    }

    @Test
    public void testMove1wins() {

        Mockito.when(move1.defeats(move2)).thenReturn(true);

        Result result = judge.getResult(move1, move2);

        Assertions.assertEquals("Player 1 wins", result.getWinner());
    }

    @Test
    public void testMove2wins() {

        Mockito.when(move2.defeats(move1)).thenReturn(true);

        Result result = judge.getResult(move1, move2);

        Assertions.assertEquals("Player 2 wins", result.getWinner());
    }

    @Test
    public void testNobodyWins() {

        Result result = judge.getResult(move1, move2);

        Assertions.assertEquals("Nobody wins", result.getWinner());
    }
}