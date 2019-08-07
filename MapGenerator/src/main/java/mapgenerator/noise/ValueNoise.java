/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator.noise;

/**
 *
 * @author kasper
 */
public class ValueNoise {

	/**
	 * a Value Noise algorithm.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public double valueNoise(int x, int y) {
		int n = x + y * 57;
		n = (n << 14) ^ n;
		return (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
	}

	/**
	 * Combines the noise value calculated from the surrounding lattice points.
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public double smoothNoise(int x, int y) {
		double corners = (valueNoise(x - 1, y - 1) + valueNoise(x + 1, y - 1) + valueNoise(x - 1, y + 1) + valueNoise(x + 1, y + 1)) / 16;
		double sides = (valueNoise(x - 1, y) + valueNoise(x + 1, y) + valueNoise(x, y - 1) + valueNoise(x, y + 1)) / 8;
		double center = valueNoise(x, y) / 4;

		return corners + sides + center;
	}

	/**
	 * Interpolates the value x between a and b. Uses the cosine function.
	 *
	 * @param a under limit
	 * @param b upper limit
	 * @param x
	 * @return
	 */
	public double cosineInterpolate(double a, double b, double x) {
		double ft = x * 3.1415927;
		double f = (1 - Math.cos(ft)) * 0.5;

		return a * (1 - f) + b * f;
	}

	/**
	 * Interpolates the value x between a and b, without any other methods.
	 * Functions the same way as interpolate
	 *
	 * @param a under limit
	 * @param b upper limit
	 * @param x
	 * @return
	 */
	public double interpolate2(double a, double b, double x) {
		return (1.0f - x) * a + b * x;
	}

	/**
	 * Interpolates the value x between a and b, without any other methods.
	 *
	 * @param a under limit
	 * @param b upper limit
	 * @param x
	 * @return
	 */
	public double interpolate(double a, double b, double x) {
		return a + x * (b - a);
	}

	/**
	 * Interpolates noise around the point (x,y)
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public double interpolateNoise(double x, double y) {
		int xAsInt = (int) x;
		double xFraction = x - xAsInt;

		int yAsInt = (int) y;
		double yFraction = y - yAsInt;

		double v1 = smoothNoise(xAsInt, yAsInt);
		double v2 = smoothNoise(xAsInt + 1, yAsInt);
		double v3 = smoothNoise(xAsInt, yAsInt + 1);
		double v4 = smoothNoise(xAsInt + 1, yAsInt + 1);

		double i1 = cosineInterpolate(v1, v2, xFraction);
		double i2 = cosineInterpolate(v3, v4, xFraction);

		return (cosineInterpolate(i1, i2, yFraction) + 1) / 2;
	}

	/**
	 * Combines multiple noise for "octaves" times. Applies amplitudes and
	 * frequency to the input values. Amplitude affects how big the height
	 * differences are. Frequency affects how frequent the height differences
	 * are. Works best without whole numbers
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public double octaveValueNoise(double x, double y,double frequency, int octaves) {
		double total = 0;
		double per = 0.5;
		double amplitude = 1;
		for (int i = 0; i < octaves; i++) {
			total += interpolateNoise(x * frequency, y * frequency) * amplitude;
		}
		return total;
	}
	
}
