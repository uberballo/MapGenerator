/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kasper
 */

import mapgenerator.ClassicNoise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClassicNoiseTest {

	private ClassicNoise noise;

	@Before
	public void setup(){
		noise = new ClassicNoise();
	}


	@Test
	public void cosineInterpolateReturnsCorrectValue(){
		double a = 0;
		double b = 1;
		double x = 0.5;
		double result = noise.cosineInterpolate(a, b, x);
		Assert.assertTrue(result<b );
		Assert.assertTrue(result>a );
	}

	@Test
	public void interpolate2ReturnsCorrectValue(){
		double a = 0;
		double b = 1;
		double x = 0.5;
		double result = noise.cosineInterpolate(a, b, x);
		Assert.assertTrue(result<b );
		Assert.assertTrue(result>a );
	}

	@Test
	public void interpolateReturnsCorrectValue(){
		double a = 0;
		double b = 1;
		double x = 0.5;
		double result = noise.cosineInterpolate(a, b, x);
		Assert.assertTrue(result<b );
		Assert.assertTrue(result>a );
	}

	@Test
	public void incMethodIncreasesCorrectlyWithoutRepeat(){
		Assert.assertEquals(3, noise.inc(2));
	}

	@Test
	public void incMethodIncreasesCorrectlyWithRepeat(){
		noise.setRepeat(2);
		Assert.assertEquals(1, noise.inc(2));
	}

	@Test
	public void fadeCalculatesCorrectValue(){
		Assert.assertEquals(32, noise.fade(2),0.0001);
	}

	
}
