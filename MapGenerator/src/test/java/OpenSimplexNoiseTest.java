
import java.util.Random;
import mapgenerator.noise.OpenSimplexNoise;
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
public class OpenSimplexNoiseTest {

	private OpenSimplexNoise noise;

	@Before
	public void setup(){
		noise = new OpenSimplexNoise();
	}
	
	@Test
	public void fastFloorFunctionsCorrectly(){
		Random random = new Random();
		for (int i = 0; i<50;i++){
			double value = random.nextDouble();
			Assert.assertEquals(Math.floor(value),OpenSimplexNoise.fastFloor(value),0.001);
		}
	}

	@Test
	public void OpenSimplexNoiseReturnsCorrectValues(){
		int x = 100;
		int y = 100;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				double nx = i / 100.0 - 0.1;
				double ny = j / 100.0 - 0.1;
				double value = noise.openNoise(nx, ny);
				Assert.assertTrue(value>=-1 && value<=1);
			}
		}
		
	}
	
}
