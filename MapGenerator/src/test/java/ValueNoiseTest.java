
import mapgenerator.noise.ValueNoise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kasper
 */
public class ValueNoiseTest {

	private ValueNoise valueNoise;


	@Before
	public void setup() {
		valueNoise = new ValueNoise();
	}


	@Test
	public void cosineInterpolateReturnsCorrectValue() {
		double a = 0;
		double b = 1;
		double x = 0.5;
		double result = valueNoise.cosineInterpolate(a, b, x);
		Assert.assertTrue(result < b);
		Assert.assertTrue(result > a);
	}

	@Test
	public void interpolate2ReturnsCorrectValue() {
		double a = 0;
		double b = 1;
		double x = 0.5;
		double result = valueNoise.interpolate2(a, b, x);
		Assert.assertTrue(result < b);
		Assert.assertTrue(result > a);
	}

	@Test
	public void interpolateReturnsCorrectValue() {
		double a = 0;
		double b = 1;
		double x = 0.5;
		double result = valueNoise.interpolate(a, b, x);
		Assert.assertTrue(result < b);
		Assert.assertTrue(result > a);
	}

	
	@Test
	public void octaveValueNoiseReturnsCorrectValues() {
		int x = 500;
		int y = 500;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double nx = i / 100.0 - 0.5 * 3;
				double ny = j / 100.0 - 0.5 * 3;
				double value = valueNoise.octaveValueNoise(nx, ny,1,1,1);
				Assert.assertTrue(value>=0 && value<=1);
			}
		}
	}

	@Test
	public void interpolateNoiseReturnsCorrectValues() {
		int x = 500;
		int y = 500;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double nx = i / 100.0 - 0.5 * 3;
				double ny = j / 100.0 - 0.5 * 3;
				double value = valueNoise.interpolateNoise(nx, ny);
				Assert.assertTrue(value>=0 && value<=1);
			}
		}
	}
}
