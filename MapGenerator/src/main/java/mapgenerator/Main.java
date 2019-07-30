/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import static javax.swing.text.html.HTML.Attribute.HEIGHT;
import static javax.swing.text.html.HTML.Attribute.WIDTH;

/**
 *
 * @author kasper
 */
public class Main {

	private static ClassicNoise noise = new ClassicNoise();

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		/*
		System.out.println(noise.perlinSecond(1, 1, 0));
		 */
		test2();

	}

	public static void test() {
		int[][] map = new int[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (noise.perlinNoise2d(7*nx, 7*ny));
				if (value<0.35){
					map[i][j] = 0;
				} else if (value>0.6){
					map[i][j] = 2;

				} else{
					map[i][j] = 1;
				}
			}
		}
		for (int i = 0; i<100;i++){
			System.out.println(Arrays.toString(map[i]));
		}

	}

	public static void test1() {
		double[][] map = new double[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (noise.perlinNoise2d(7*nx, 7*ny))+1;
				map[i][j] = Math.round(value*100.0)/100.0;
			}
		}
		for (int i = 0; i<100;i++){
			System.out.println(Arrays.toString(map[i]));
		}

	}

	public static void test2() throws IOException {
		int x = 256;
		int y = 256;
		BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				//double value = (noise.octavePerlin(i*1.9, j,1,5,1));

				double nx = i / (double)x ;
				double ny = j / (double)y;
				//double value = noise.interpolateNoise(nx,ny);
				double value = noise.perlinSecond(i * 1.5, j * 1.2, 1);
				System.out.println(value);
				int rgb = 0x010101 * (int) ((value + 1) * 127.5);
				image.setRGB(i, j, rgb);
			}
		}
		ImageIO.write(image, "png", new File("noise.png"));

	}

}
