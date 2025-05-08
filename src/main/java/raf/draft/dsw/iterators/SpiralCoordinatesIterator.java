package raf.draft.dsw.iterators;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * <p>
 * The {@code SpiralCoordinatesIterator} class provides an iterator that returns
 * lists of coordinates in spiral order. Each {@code next()} call returns the
 * coordinates of one “side” of the spiral (e.g., the top row, then the right
 * column, then the bottom row, then the left column, and so on).
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * For a 3x4 matrix:
 *  - next() returns top row → (0, 0), (0, 1), (0, 2), (0, 3)
 *  - next() returns right column → (1, 3), (2, 3)
 *  - next() returns bottom row → (2, 2), (2, 1), (2, 0)
 *  - next() returns left column → (1, 0)
 *  - next() returns the next top row (the "inner" spiral) → (1, 1), (1, 2)
 *  ... and so on, until no sides remain.
 * </pre>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * int width = 3;
 * int length = 4;
 * SpiralCoordinatesIterator spiralSidesIterator = new SpiralCoordinatesIterator(width, length);
 * while (spiralSidesIterator.hasNext()) {
 *     List<Point> oneSideCoordinates = spiralSidesIterator.next();
 *     // process the list of points for that side
 * }
 * </pre>
 */
public final class SpiralCoordinatesIterator implements Iterator<List<Point>> {

    private final int width;
    private final int length;

    // Boundaries for the current “layer” of the spiral
    private int top;
    private int bottom;
    private int left;
    private int right;

    // Which side we are currently traversing
    private Side currentSide = Side.TOP;
    private enum Side { TOP, RIGHT, BOTTOM, LEFT }

    /**
     * Constructs a {@code SpiralCoordinatesIterator} for a matrix with the specified dimensions.
     *
     * @param width  the number of columns in the matrix
     * @param length the number of rows in the matrix
     * @throws IllegalArgumentException if {@code width} or {@code length} is non-positive
     */
    public SpiralCoordinatesIterator(int width, int length) {
        if (width <= 0 || length <= 0) {
            throw new IllegalArgumentException("width and length must be positive");
        }
        this.width = width;
        this.length = length;

        // Initialize spiral boundaries
        this.top = 0;
        this.bottom = length - 1;
        this.left = 0;
        this.right = width - 1;
    }



    /**
     * Returns {@code true} if there is another “side” of the spiral to iterate over.
     *
     * @return {@code true} if more sides remain; {@code false} otherwise
     */
    @Override
    public boolean hasNext() {
        return (top <= bottom) && (left <= right);
    }

    /**
     * Returns the coordinates for the next “side” (row or column) in the spiral.
     *
     * @return a {@code List<Point>} containing the coordinates of the next side
     * @throws NoSuchElementException if the iteration has no more sides
     */
    @Override
    public List<Point> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more sides in the spiral.");
        }

        List<Point> sidePoints = new ArrayList<>();

        switch (currentSide) {
            case TOP -> {
                // Traverse from left to right along 'top' row
                for (int col = left; col <= right; col++) {
                    sidePoints.add(new Point(top, col));
                }
                top++;  // Move the top boundary down
                currentSide = Side.RIGHT;
            }
            case RIGHT -> {
                // Traverse from top to bottom along 'right' column
                for (int row = top; row <= bottom; row++) {
                    sidePoints.add(new Point(row, right));
                }
                right--;  // Move the right boundary left
                currentSide = Side.BOTTOM;
            }
            case BOTTOM -> {
                // Traverse from right to left along 'bottom' row
                for (int col = right; col >= left; col--) {
                    sidePoints.add(new Point(bottom, col));
                }
                bottom--;  // Move the bottom boundary up
                currentSide = Side.LEFT;
            }
            case LEFT -> {
                // Traverse from bottom to top along 'left' column
                for (int row = bottom; row >= top; row--) {
                    sidePoints.add(new Point(row, left));
                }
                left++;  // Move the left boundary right
                currentSide = Side.TOP;
            }
        }

        return sidePoints;
    }
}
