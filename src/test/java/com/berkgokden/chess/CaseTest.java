package com.berkgokden.chess;

import com.berkgokden.chess.solver.ChessSolverHelper;
import org.junit.Test;

import java.util.*;
import static org.junit.Assert.*;

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

    @Test
    public void shouldPassWhenTestCase2SolvedCorrectly() throws Exception {
        int m = 4;
        int n = 4;
        Map<PieceType, Integer> resources = new HashMap<>();
        resources.put(PieceType.KNIGHT, 4);
        resources.put(PieceType.ROOK, 2);
        Set<Board> solutionSet = ChessSolverHelper.solve(m, n, resources);
        assertEquals(8, solutionSet.size());
        // TODO: add proper solution checking
        System.out.println("Solutions: Size("+solutionSet.size()+")");
        for (Board board : solutionSet) {
            board.prettyPrint();
        }
    }

    /**
     * Test Case For Pieces:[KNIGHT, KING, KING, QUEEN, QUEEN, BISHOP, BISHOP]
     * on 7x7 board
     * Time: 1055124 ms
     * Solutions: Size(2148780)
     * @throws Exception
     */
    @Test
    public void shouldPassWhenTestCase3SolvedCorrectly() throws Exception {
        int m = 7;
        int n = 7;
        Map<PieceType, Integer> resources = new HashMap<>();
        resources.put(PieceType.KING, 2);
        resources.put(PieceType.QUEEN, 2);
        resources.put(PieceType.BISHOP, 2);
        resources.put(PieceType.KNIGHT, 1);
        Set<Board> solutionSet = ChessSolverHelper.solve(m, n, resources);
        // assertEquals(8, solutionSet.size());
        // TODO: add proper solution checking
        System.out.println("Solutions: Size("+solutionSet.size()+")");
        /* for (Board board : solutionSet) {
            board.prettyPrint();
        } */
    }

    @Test
    public void shouldPassWhenPermutationCalculatedCorrectly() throws Exception {
        Set<List<PieceType>> permutations = new HashSet<List<PieceType>>();
        PieceType[] pieces = new PieceType[] {PieceType.KING, PieceType.KING, PieceType.ROOK};
        ChessSolverHelper.generatePieceTypePermutations(permutations, new ArrayList<PieceType>(), new ArrayList(Arrays.asList(pieces)));

        // TODO: aff proper checking
        assertEquals(3, permutations.size());
        for (List<PieceType> permutation : permutations) {
            System.out.println(permutation);
        }
    }
}
