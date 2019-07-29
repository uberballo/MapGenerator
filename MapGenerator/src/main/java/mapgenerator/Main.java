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

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		ClassicNoise noise = new ClassicNoise();
		
		int x = 500;
		int y = 500;
		BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i<x;i++){
			for (int j=0;j<y;j++){
				double value = (noise.perlinSecond(i, j,0));
				System.out.println(value);
				int rgb = 0x010101 * (int) ((value + 1) * 127.5);
				image.setRGB(i, j, rgb);
			}
		}
		ImageIO.write(image, "png", new File("noise.png"));
		System.out.println(noise.perlinSecond(1, 1, 0));
		for (int i = 0; i< 10; i++){
		}

	}
	
}
