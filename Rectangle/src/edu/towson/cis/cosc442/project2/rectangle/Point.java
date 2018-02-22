package edu.towson.cis.cosc442.project2.rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Point Class.
 */
public class Point {
	
	/** x and y coordinates. */
	public Double x, y;
	
	/**
	 * Instantiates a new point.
	 *
	 * @param x the x
	 * @param y the y
	 */
	Point(Double x, Double y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	private void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	private void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY() {
		return this.y;
	}
	
}
