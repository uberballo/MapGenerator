/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapgenerator;

import mapgenerator.noise.ClassicNoise;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import mapgenerator.noise.ValueNoise;
import sun.java2d.pipe.BufferedOpCodes;

/**
 *
 * @author kasper
 */
public class Main extends Application {

	private static ClassicNoise classicNoise = new ClassicNoise();
	private static ValueNoise valueNoise = new ValueNoise();
	private GraphicsContext g2d;
	private double[][] test;


	public static void main(String[] args) throws IOException {
		//printOctavePerlinValues();
		//launch(args);
	}


	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();
		Canvas canvas = new Canvas(640, 500);
		root.getChildren().add(canvas);
		g2d = canvas.getGraphicsContext2D();
		BorderPane placement = new BorderPane();
		placement.setCenter(root);
		
		VBox buttons = new VBox();
		Button picture1Button = new Button("picture 1 with value noise");
		picture1Button.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e){
				drawFromMap(generateValueNoise());
			}
		
		});
		
		Button picture2Button = new Button("picture 2 no octaves");
		picture2Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				drawFromMap(generateNoiseMapNoGradientNoOctaves());
			}
		});
		buttons.getChildren().add(picture1Button);
		buttons.getChildren().add(picture2Button);
		placement.setRight(buttons);
		
		g2d.setFill(javafx.scene.paint.Color.BLACK);

	
		//drawFromMap(generateNoiseMapNoGradient());

		Scene scene = new Scene(placement);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void drawFromMap(double[][] map) {
		for (int i = 0; i < map.length-1; i++) {
			for (int j = 0; j < map[j].length-1; j++) {
				float value = (float)map[i][j];
				g2d.setFill(new Color(value,value,value,1) );
				g2d.fillRect(i, j, 1, 1);
				
			}
		}
	}

	public static void printPerlinSecondValues() {
		int height = 500;
		int width = 500;
		double[][] map = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width ; j++) {
				double nx = i*0.2 ;
				double ny = j*0.2 ;
				double value = (classicNoise.perlinSecond(nx,ny,1));
				map[i][j] = value;
			}
		}
		for (int i = 0; i<100;i++){
			System.out.println(Arrays.toString(map[i]));
		}
		double max = Arrays.stream(map[1]).max().getAsDouble();
		System.out.println(max);

	}


	public static void printInterpolateNoiseValues() {
		int height = 500;
		int width = 500;
		double[][] map = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width ; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (valueNoise.interpolateNoise(nx,ny));
				map[i][j] = value;
			}
		}
		for (int i = 0; i<100;i++){
			System.out.println(Arrays.toString(map[i]));
		}
		double max = Arrays.stream(map[1]).max().getAsDouble();
		System.out.println(max);

	}

	public static void printOctavePerlinValues() {
		int height = 500;
		int width = 500;
		double[][] map = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width ; j++) {
				double nx = i*0.2 ;
				double ny = j*0.2;
				double value = (classicNoise.octavePerlin(nx,ny,1,1,1));
				map[i][j] = value;
			}
		}
		for (int i = 0; i<100;i++){
			System.out.println(Arrays.toString(map[i]));
		}
		double max = Arrays.stream(map[1]).max().getAsDouble();
		System.out.println(max);

	}

	public static double[][] generateValueNoise() {
		int height = 500;
		int width = 500;
		double[][] map = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width ; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (valueNoise.octaveValueNoise(3.5*nx, 3.5*ny));
				map[i][j] = value;
			}
		}
		return map;
	}

	public static double[][] generateNoiseMapNoGradientNoOctaves() {
		int height = 500;
		int width = 500;
		double[][] map = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width ; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (valueNoise.interpolateNoise(nx, ny));
				map[i][j] = value;
			}
		}
		return map;
	}

	public static double[][] generateNoiseMapWithGradient() {
		int height = 500;
		int width = 500;
		double[][] map = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width ; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (classicNoise.perlinSecond(nx,ny,1));
				map[i][j] = value;
			}
		}
		return map;
	}

	

}
