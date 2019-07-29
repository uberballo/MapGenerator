/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import java.util.Arrays;

/**
 *
 * @author kasper
 */
public class ClassicNoise {

	private static int[] permutation = {151, 160, 137, 91, 90, 15,
		131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23,
		190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33,
		88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166,
		77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244,
		102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196,
		135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123,
		5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42,
		223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9,
		129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228,
		251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107,
		49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254,
		138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180
	};

	private static int[] p;
	private int repeat = 0;

	public ClassicNoise() {
		p = new int[512];
		for (int i = 0; i < 512; i++) {
			p[i] = permutation[i % 256];
		}
	}

	public double dotGridGradient(int ix, int iy, double x, double y){
		double dx = x - ix;
		double dy = y - iy;

	}

	public double fade(double x) {
		return x * x * x * (x * (x * 6 - 15) + 10);
	}

	public int inc(int x) {
		x++;
		if (repeat > 0) {
			x %= repeat;
		}
		return x;
	}

	//This i understand
	public double grad(int hash, double x, double y, double z) {

		switch (hash & 0xF) {
			case 0x0:
				return x + y;
			case 0x1:
				return -x + y;
			case 0x2:
				return x - y;
			case 0x3:
				return -x - y;
			case 0x4:
				return x + z;
			case 0x5:
				return -x + z;
			case 0x6:
				return x - z;
			case 0x7:
				return -x - z;
			case 0x8:
				return y + z;
			case 0x9:
				return -y + z;
			case 0xA:
				return y - z;
			case 0xB:
				return -y - z;
			case 0xC:
				return y + x;
			case 0xD:
				return -y + z;
			case 0xE:
				return y - x;
			case 0xF:
				return -y - z;
			default:
				return 0;
		}
	}

	//Dont understand
	public double grad2(int hash, double x, double y, double z) {
		int h = hash & 15;
		double u = h < 8 /* 0b1000 */ ? x : y;

		double v;
		if (h < 4 /* 0b0100 */) {
			v = y;
		} else if (h == 12 /* 0b1100 */ || h == 14 /* 0b1110*/) {
			v = x;
		} else {
			v = z;
		}

		return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
	}

	public double perlinSecond(double x, double y, double z) {
		if (repeat > 0) {
			x = x % repeat;
			y = y % repeat;
			z = z % repeat;
		}

		int xi = (int) x & 255;
		int yi = (int) y & 255;
		int zi = (int) z & 255;
		double xf = x - (int) x;
		double yf = y - (int) y;
		double zf = z - (int) z;

		double u = fade(xf);
		double v = fade(yf);
		double w = fade(zf);

		int aaa, aba, aab, abb, baa, bba, bab, bbb;
		aaa = p[p[p[xi] + yi] + zi];
		aba = p[p[p[xi] + inc(yi)] + zi];
		aab = p[p[p[xi] + yi] + inc(zi)];
		abb = p[p[p[xi] + inc(yi)] + inc(zi)];
		baa = p[p[p[inc(xi)] + yi] + zi];
		bba = p[p[p[inc(xi)] + inc(yi)] + zi];
		bab = p[p[p[inc(xi)] + yi] + inc(zi)];
		bbb = p[p[p[inc(xi)] + inc(yi)] + inc(zi)];

		double x1, x2, y1, y2;
		x1 = interpolate(grad(aaa, xf, yf, zf),
				grad(baa, xf - 1, yf, zf),
				u);
		x2 = interpolate(grad(aba, xf, yf - 1, zf),
				grad(bba, xf - 1, yf - 1, zf),
				u);
		y1 = interpolate(x1, x2, v);

		x1 = interpolate(grad(aab, xf, yf, zf - 1),
				grad(bab, xf - 1, yf, zf - 1),
				u);
		x2 = interpolate(grad(abb, xf, yf - 1, zf - 1),
				grad(bbb, xf - 1, yf - 1, zf - 1),
				u);
		y2 = interpolate(x1, x2, v);

		return (interpolate(y1, y2, w) + 1) / 2;
	}

	public double noise(int x, int y) {
		int n = x + y * 57;
		n = (n << 14) ^ n;
		return (1.0 - ((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / 1073741824.0);
	}

	public double smoothNoise(int x, int y) {
		double corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16;
		double sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, y + 1)) / 8;
		double center = noise(x, y) / 4;

		return corners + sides + center;
	}

	//Doesn't return rounded number
	public double cosineInterpolate(double a, double b, double x) {
		double ft = x * 3.1415927;
		double f = (1 - Math.cos(ft)) * 0.5;

		return a * (1 - f) + b * f;
	}

	public double interpolate(double a, double b, double x) {
		return (1.0f - x) * a + b * x;
	}

	public double interpolate2(double a, double b, double x) {
		return a + x * (b - a);
	}

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

		return cosineInterpolate(i1, i2, yFraction);
	}

	public double perlinNoise2d(double x, double y) {
		double total = 0;
		double per = 0.5;
		double octaceve = 1;
		double freq = 2;
		double amp = 2;
		for (int i = 0; i < octaceve; i++) {
			total += interpolateNoise(x * freq, y * freq) * amp;
		}
		return total;
	}
}
