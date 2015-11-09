package ch.sebastianhaeni.edgewars.util;

/**
 * Position helper class.
 */
public class Position {
    private float mX;
    private float mY;

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Position(float x, float y) {
        this.mX = x;
        this.mY = y;
    }

    /**
     * @return gets x
     */
    public float getX() {
        return mX;
    }

    /**
     * @return gets y
     */
    public float getY() {
        return mY;
    }

    /**
     * Sets a new position
     *
     * @param other the new position
     */
    public void set(Position other) {
        mX = other.getX();
        mY = other.getY();
    }

    @Override
    public String toString() {
        return "{x: " + mX + ", y: " + mY + "}";
    }

    /**
     * Determines if the positions are about the same with a precision of 0.1.
     *
     * @param position the other position to compare with
     * @return if this is about the same
     */
    public boolean isAboutTheSame(Position position) {
        return Math.abs(mX - position.getX()) < .1f && Math.abs(mY - position.getY()) < .1f;
    }
}
