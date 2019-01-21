package textproc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Bookreader");
		primaryStage.setScene(scene);
		primaryStage.show();

		HBox hbox = new HBox(5);
		Button b1 = new Button("Alphabetic");
		Button b2 = new Button("Frequency");
		Button b3 = new Button("Find");
		TextField t1 = new TextField();
		b3.setDefaultButton(true);
		HBox.setHgrow(t1, Priority.ALWAYS);
		Alert alert = new Alert(AlertType.ERROR, "Ordet återfinns ej i textmassan", ButtonType.CLOSE);

		hbox.getChildren().addAll(b1, b2, b3, t1);

		Scanner scan = new Scanner(new File("undantagsord.txt"));

		Set<String> exceptionWords = new HashSet<String>();
		List<TextProcessor> list = new ArrayList<TextProcessor>();

		GeneralWordCounter counter = new GeneralWordCounter(exceptionWords);

		list.add(counter);

		while (scan.hasNext()) {
			exceptionWords.add(scan.next());
		}
		scan.close();

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor n : list) {
				n.process(word);
			}
		}

		ObservableList<Entry<String, Integer>> words = FXCollections.observableArrayList(counter.getWords());
		ListView<Entry<String, Integer>> listView = new ListView<>(words);
		root.setCenter(listView);
		root.setBottom(hbox);

		b1.setOnAction((event) -> {
			words.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		});

		b2.setOnAction((event) -> {
			words.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
		});

		b3.setOnAction((event) -> {
			for (Entry<String, Integer> e : words) {
				if (e.getKey().equals(t1.getText())) {
					listView.scrollTo(e);
					listView.getSelectionModel().select(e);
					
				}
			}
		});

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
