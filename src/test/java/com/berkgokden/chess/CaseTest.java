package com.berkgokden.chess;

import com.berkgokden.chess.solver.ChessSolverHelper;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Testing ChessSolverHelper and SolverTask
 * with known test cases
 */
public class CaseTest {
    @Test
    public void shouldPassWhenTestCase1SolvedCorrectly() throws Exception {
        int m = 3;
        int n = 3;
        Map<PieceType, Integer> resources = new HashMap<>();
        resources.put(PieceType.KING, 2);
        resources.put(PieceType.ROOK, 1);
        Set<Board> solutionSet = ChessSolverHelper.solve(m, n, resources);
        assertEquals(4, solutionSet.size());
        // TODO: add proper solution checking
        System.out.println("Solutions: Size("+solutionSet.size()+")");
        for (Board board : solutionSet) {
            board.prettyPrint();
        }
    }
}
