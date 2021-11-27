package com.abc.geom;

/**
 *
 * Instances are immutable
 */
public final class NamedPoint {
    private final int x;
    private final int y;
    private final String name;

    private boolean distanceHasBeenCalculated;
    private double distanceFromOrigin;

    public NamedPoint(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;

        distanceHasBeenCalculated = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public synchronized double getDistanceFromOrigin() {
        if (!distanceHasBeenCalculated) { // lazy initialization
            distanceFromOrigin = Math.sqrt(x * x + y * y);
            distanceHasBeenCalculated = true;
        }
        return distanceFromOrigin;
    }
}
