package mapgenerator;

import mapgenerator.noise.ClassicNoise;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mapgenerator.noise.ValueNoise;

/**
 *
 * @author kasper
 */
public class Main extends Application {

	private static ClassicNoise classicNoise = new ClassicNoise();
	private static ValueNoise valueNoise = new ValueNoise();
	private GraphicsContext g2d;
	private double[][] map;
	private int height = 500;
	private int length = 500;
	private int octave = 1;
	private double frequency = 1;
	private double amplitude = 1;

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.map = new double[length][height];

		Group root = new Group();
		Canvas canvas = new Canvas(540, 540);
		root.getChildren().add(canvas);
		g2d = canvas.getGraphicsContext2D();
		BorderPane placement = new BorderPane();
		placement.setCenter(root);

		VBox buttons = new VBox();
		Button picture1Button = new Button("value noise with octave");
		picture1Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				generateValueNoise();
				drawNoiseFromMap();
			}

		});

		Button picture2Button = new Button("value noise without octave");
		picture2Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				generageValueNoiseWithoutOctaves();
				drawNoiseFromMap();
			}
		});

		Button picture3Button = new Button("Perlin noise without octave");
		picture3Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				generatePerlinNoiseWithoutOctaves();
				drawNoiseFromMap();
			}
		});

		Button picture4Button = new Button("Perlin noise with octave");
		picture4Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				generatePerlinNoiseWithOctaves();
				drawNoiseFromMap();
			}
		});

		Button picture5Button = new Button("Game map from current map");
		picture5Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				drawFromMap();
			}
		});

		buttons.getChildren().add(picture1Button);
		buttons.getChildren().add(picture2Button);
		buttons.getChildren().add(picture4Button);
		buttons.getChildren().add(picture3Button);
		buttons.getChildren().add(picture5Button);

		//Slider
		//Octave slider
		VBox octaveSliderLayout = new VBox();
		Label octaveSliderLabel = new Label();
		octaveSliderLabel.setText("Octave: " + octave);

		Slider octaveSlider = new Slider();
		octaveSlider.setMin(1);
		octaveSlider.setMax(10);
		octaveSlider.setShowTickLabels(true);
		octaveSlider.setShowTickMarks(true);
		octaveSlider.setMinorTickCount(0);
		octaveSlider.setMajorTickUnit(1);
		octaveSlider.setBlockIncrement(1);
		octaveSlider.setSnapToTicks(true);

		octaveSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number oldValue, Number newValue) {
				double value = newValue.doubleValue();
				if (value == Math.floor(value)) {
					octave = (int) value;
					octaveSliderLabel.setText("Octave: " + octave);
				}
			}
		});

		octaveSliderLayout.getChildren().add(octaveSliderLabel);
		octaveSliderLayout.getChildren().add(octaveSlider);

		//Frequency slider
		VBox frequencySliderLayout = new VBox();
		Label frequencySliderLabel = new Label();
		frequencySliderLabel.setText("Frequency: " + frequency);

		Slider frequencySlider = new Slider();
		frequencySlider.setMin(0.01);
		frequencySlider.setMax(5);
		frequencySlider.setValue(1);
		frequencySlider.setShowTickLabels(true);
		frequencySlider.setShowTickMarks(true);
		frequencySlider.setMinorTickCount(0);
		frequencySlider.setMajorTickUnit(1);

		frequencySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number oldValue, Number newValue) {
				double value = newValue.doubleValue();
				frequency = value;
				frequencySliderLabel.setText("Frequency: " + String.format("%.2f", frequency));

			}
		});

		frequencySliderLayout.getChildren().add(frequencySliderLabel);
		frequencySliderLayout.getChildren().add(frequencySlider);

		//Amplitude
		VBox amplitudeSliderLayout = new VBox();
		Label amplitudeSliderLabel = new Label();
		amplitudeSliderLabel.setText("Amplitude: " + amplitude);

		Slider amplitudeSlider = new Slider();
		amplitudeSlider.setMin(0.01);
		amplitudeSlider.setMax(5);
		amplitudeSlider.setValue(1);
		amplitudeSlider.setShowTickLabels(true);
		amplitudeSlider.setShowTickMarks(true);
		amplitudeSlider.setMinorTickCount(0);
		amplitudeSlider.setMajorTickUnit(1);

		amplitudeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number oldValue, Number newValue) {
				double value = newValue.doubleValue();
				amplitude = value;
				amplitudeSliderLabel.setText("Amplitude: " + String.format("%.2f", amplitude));

			}
		});

		amplitudeSliderLayout.getChildren().add(amplitudeSliderLabel);
		amplitudeSliderLayout.getChildren().add(amplitudeSlider);

		HBox sliders = new HBox();
		sliders.setSpacing(10);
		sliders.getChildren().add(octaveSliderLayout);
		sliders.getChildren().add(frequencySliderLayout);
		sliders.getChildren().add(amplitudeSliderLayout);

		placement.setRight(buttons);
		placement.setBottom(sliders);

		Scene scene = new Scene(placement);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void drawNoiseFromMap() {
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[j].length - 1; j++) {
				float value = (float) map[i][j];
				value = value > 1 ? 0 : value;
				value = value < 0 ? 0 : value;
				g2d.setFill(new Color(value, value, value, 1));
				g2d.fillRect(i, j, 1, 1);

			}
		}
	}

	public void drawFromMap() {
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[j].length - 1; j++) {
				float value = (float) map[i][j];
				value = value > 1 ? 0 : value;
				value = value < 0 ? 0 : value;
				Color color = Color.BLACK;
				if (value < 0.3) {
					color = Color.BLUE;
				} else if (value < 0.5) {
					color = Color.GREEN;
				} else {
					color = Color.SADDLEBROWN;
				}
				g2d.setFill(color);
				g2d.fillRect(i, j, 1, 1);

			}
		}
	}
	public void generateValueNoise() {
		int height = 500;
		int width = 500;
		double amp = 3.5;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double nx = i / 100.0 - 0.5;
				double ny = j / 100.0 - 0.5;
				double value = (valueNoise.octaveValueNoise(nx,ny, octave, frequency, amplitude));
				this.map[i][j] = value;
			}
		}

	}

	public void generageValueNoiseWithoutOctaves() {
		int height = 500;
		int width = 500;
		int amp = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double nx = i / 100.0 - 0.5 * 2;
				double ny = j / 100.0 - 0.5 * 2;
				double value = (valueNoise.interpolateNoise(nx * amp, ny * amp));
				this.map[i][j] = value;
			}
		}
	}

	public void generatePerlinNoiseWithoutOctaves() {
		int height = 500;
		int width = 500;
		int amp = 2;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double nx = i / 100.0 - 0.1;
				double ny = j / 100.0 - 0.1;
				double value = (classicNoise.perlinSecond(nx * amp, ny * amp, 1));
				this.map[i][j] = value;
			}
		}
	}

	public void generatePerlinNoiseWithOctaves() {
		int height = 500;
		int width = 500;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				double nx = i / 100.0 - 0.1;
				double ny = j / 100.0 - 0.1;
				double value = (classicNoise.octavePerlin(nx, ny, 1, octave, frequency, amplitude, 1));
				this.map[i][j] = value;
			}
		}
	}

}
