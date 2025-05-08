package raf.draft.dsw.utils;

import raf.draft.dsw.iterators.SpiralCoordinatesIterator;

import java.awt.*;
import java.util.List;

public final class SpiralMatrixDrawer {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Private constructor to prevent instantiation
    private SpiralMatrixDrawer() {
    }

    /**
     * Generates a spiral matrix of dimensions {@code length} x {@code width},
     * where values are filled in spiral order from 1 up to {@code length * width}.
     *
     * @param width  the number of columns
     * @param length the number of rows
     * @return a 2D array (length x width) with values assigned in a spiral traversal pattern
     * @throws IllegalArgumentException if width or length is non-positive
     */
    public static int[][] generateSpiralMatrix(int width, int length) {
        if (width <= 0 || length <= 0) {
            throw new IllegalArgumentException("Width and length must be positive.");
        }

        // Create a 2D matrix (length rows, width columns)
        int[][] matrix = new int[length][width];

        // Our new SpiralCoordinatesIterator now returns List<Point> for each "side"
        SpiralCoordinatesIterator spiralIterator = new SpiralCoordinatesIterator(width, length);

        int value = 1;
        // Fetch each "side" in the spiral order
        while (spiralIterator.hasNext()) {
            // One entire spiral edge (list of points)
            List<Point> sidePoints = spiralIterator.next();

            // Fill all coordinates on this side of the spiral
            for (Point p : sidePoints) {
                // Note: p.x is the row index, p.y is the column index
                matrix[p.x][p.y] = value++;
            }
        }

        return matrix;
    }

    /**
     * Returns a string representation of the matrix. The last value is highlighted in red.
     *
     * @param matrix a 2D array of int values
     * @return a String representation of the matrix
     */
    public static String matrixToString(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return "";
        }

        // The last (largest) value, which we color in red
        int maxValue = matrix.length * matrix[0].length;
        int maxWidth = String.valueOf(maxValue).length();
        int lastValue = maxValue;

        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            for (int value : row) {
                sb.append("[");
                if (value == lastValue) {
                    sb.append(ANSI_RED)
                            .append(String.format("%" + maxWidth + "d", value))
                            .append(ANSI_RESET);
                } else {
                    sb.append(String.format("%" + maxWidth + "d", value));
                }
                sb.append("]");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Prints the given matrix to stdout, highlighting the largest number in red.
     *
     * @param matrix a 2D array of int values
     */
    public static void printMatrix(int[][] matrix) {
        System.out.print(matrixToString(matrix));
    }
}
