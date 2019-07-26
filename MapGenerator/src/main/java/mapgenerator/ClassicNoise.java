/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

/**
 *
 * @author kasper
 */
public class ClassicNoise {

	private static int grad3[][] = {{1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0},
	{1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1},
	{0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}};

	private int YIMAX = 10;
	private int XIMAX = 10;
	
	
	public double noise(int x, int y){
		int n = x+y*57;
		n = (n<<14)^n;
		return ( 1.0 - ((n * (n * n *15731 + 789221) + 1376312589) & 0x7fffffff)/1073741824.0);
	} 

	public double smoothNoise(int x, int y){
		double corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16;
		double sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, y + 1)) / 8;
		double center = noise(x, y) / 4;

		return corners + sides + center;
	}

	public double cosineInterpolate(double a, double b, double x){
		double ft = x * 3.1415927;
		double f = (1 - Math.cos(ft)) * 0.5;

		return a*(1-f) + b*f;
	}

	public double interpolate(double a, double b, double x){
		return (1.0f-x)*a + b*x;
	}


	public double interpolateNoise(double x, double y){
		int xAsInt = (int) x;
		double xFraction = x - xAsInt;

		int yAsInt = (int) y;
		double yFraction = y - yAsInt;

		double v1 = smoothNoise(xAsInt, yAsInt);
		double v2 = smoothNoise(xAsInt +1 , yAsInt);
		double v3 = smoothNoise(xAsInt, yAsInt + 1);
		double v4 = smoothNoise(xAsInt + 1, yAsInt + 1);

		double i1 = cosineInterpolate(v1, v2, xFraction);
		double i2 = cosineInterpolate(v3, v4, xFraction);

		return cosineInterpolate(i1, i2, yFraction);
	}

	public double perlinNoise2d(double x, double y){
		double total = 0;
		double per = 0.5;
		double octaceve = 1;
		double freq = 2;
		double amp = 2;
		for (int i = 0; i< octaceve;i++){
			total += interpolateNoise(x*freq, y*freq) * amp;
		}
		return total;
	}
}
