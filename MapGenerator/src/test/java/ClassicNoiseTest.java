/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kasper
 */
import mapgenerator.noise.ClassicNoise;
import mapgenerator.noise.ValueNoise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassicNoiseTest {

	private ClassicNoise classicNoise;
	private ValueNoise valueNoise;

	@Before
	public void setup() {
		classicNoise= new ClassicNoise();
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
	public void incMethodIncreasesCorrectlyWithoutRepeat() {
		Assert.assertEquals(3, classicNoise.inc(2));
	}

	@Test
	public void incMethodIncreasesCorrectlyWithRepeat() {
		classicNoise.setRepeat(2);
		Assert.assertEquals(1, classicNoise.inc(2));
	}

	@Test
	public void fadeCalculatesCorrectValue() {
		Assert.assertEquals(32, classicNoise.fade(2), 0.0001);
	}

	@Test
	public void methodGradReturnsCorrectValue() {
		int hash = 15;
		int x = 2;
		int y = 2;
		int z = 2;
		Assert.assertEquals(-4, classicNoise.grad(hash, x, y, z), 0.0001);
		hash = 0;
		Assert.assertEquals(4, classicNoise.grad(hash, x, y, z), 0.0001);
	}

	@Test
	public void octaveValueNoiseReturnsCorrectValues() {
		int x = 100;
		int y = 100;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double nx = i / 100.0 - 0.5 * 3;
				double ny = j / 100.0 - 0.5 * 3;
				double value = valueNoise.octaveValueNoise(nx, ny);
				Assert.assertTrue(value>=0 && value<=1);
			}
		}
	}

	@Test
	public void octavePerlinNoiseReturnsCorrectValues() {
		int x = 100;
		int y = 100;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double nx = i *0.2;
				double ny = j *0.2;
				double value = classicNoise.octavePerlin(nx, ny,1,1,1);
				Assert.assertTrue(value>=0 && value<=1);
			}
		}
	}

}
