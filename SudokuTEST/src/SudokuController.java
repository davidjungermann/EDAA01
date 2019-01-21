import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SudokuController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		TilePane tilepane = new TilePane();
		HBox hbox = new HBox();
		SingleSymbolField[][] fields = new SingleSymbolField[10][10];

		tilepane.setPrefColumns(9);
		tilepane.setPrefRows(9);
		tilepane.setHgap(3);
		tilepane.setVgap(3);
		tilepane.setMaxWidth(300);
		tilepane.setMinWidth(300);
		tilepane.setMaxHeight(300);
		tilepane.setMinHeight(300);
		final int SIZE = 40; 

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 9; col++) {
				SingleSymbolField field = new SingleSymbolField();
				tilepane.getChildren().add(field);
				fields[row][col] = field;
				field.setPrefColumnCount(1);
				field.setPrefHeight(1);
				tilepane.setPrefSize(SIZE, SIZE); 
			}
		}

		Button b1 = new Button("Solve");
		Button b2 = new Button("Clear");
		hbox.getChildren().addAll(b1, b2);

		BorderPane borderpane = new BorderPane();
		Scene scene = new Scene(borderpane);

		borderpane.setCenter(tilepane);
		borderpane.setBottom(hbox);
		borderpane.setPrefSize(300, 300);
		tilepane.setAlignment(Pos.CENTER);

		primaryStage.setTitle("Sudoku solver");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
