package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SampleController {

	@FXML
	private Button sortBtn;

	@FXML
	private Pane space;

	@FXML
	private GridPane gridPane;

	@FXML
	private Button shuffleBtn;

	@FXML
	private ComboBox<String> comBox;

	@FXML
	private TextField speed;

	@FXML
	private Button minusBtn;

	@FXML
	private Button pluseBtn;

	ObservableList<String> list;

	Rectangle[] items;
	Random rand = new Random();
	private int width = 1;
	Manager manager;

	@FXML
	void initialize() {
		int x = 0;
		int n = (int) (space.getMaxWidth() / width);
		items = new Rectangle[n];

		space.setStyle("-fx-background-color: black;");
		gridPane.setStyle("-fx-background-color: black;");
		// initialize sorting algorithm combo box
		initSortAlgComBox();
		
		for (int i = 0; i < n; i++) {
			int height = rand.nextInt((int) space.getMaxHeight());
			Rectangle rect = new Rectangle(x, space.getMaxHeight() - height, width, height);
			items[i] = rect;
			draw(items[i]);
			x += width;
		}

		manager = new Manager(items, space, sortBtn, shuffleBtn);

	}

	private void draw(Rectangle rect) {
		rect.setStroke(Color.CYAN);
		rect.setFill(Color.BLUE);
		space.getChildren().add(rect);
	}

	@FXML
	void sortBtnPressed(MouseEvent event) {
		String str = comBox.getValue();
		if (str==null)
			showWarningAlert("", "Choose Sorting Algorithm First!");
		else {
			switch (str) {
			case "Bubble Sort":
				manager.setSortMethod(SortingMethod.BubbleSort);
				break;
			case "Selection Sort":
				manager.setSortMethod(SortingMethod.SelectionSort);
				break;
			case "Merge Sort":
				manager.setSortMethod(SortingMethod.MergeSort);
				break;
			case "Heap Sort":
				manager.setSortMethod(SortingMethod.HeapSort);
				break;
			}
			manager.start();
		}

	}

	@FXML
	void speedPlusePressed(ActionEvent event) {

		manager.setSpeed(manager.getSpeed() + 5000);
		speed.setText(String.valueOf(manager.getSpeed()));

		if (manager.getSpeed() == 60000)
			pluseBtn.setDisable(true);

		if (manager.getSpeed() > 0)
			minusBtn.setDisable(false);
	}

	@FXML
	void speedMinusPressed(ActionEvent event) {

		manager.setSpeed(manager.getSpeed() - 5000);
		speed.setText(String.valueOf(manager.getSpeed()));

		if (manager.getSpeed() <= 0)
			minusBtn.setDisable(true);

		if (manager.getSpeed() < 60000)
			pluseBtn.setDisable(false);
	}

	@FXML
	void shuffleBtnPressed(MouseEvent event) {

		int i = 0;
		Integer[] heightVal = new Integer[items.length];

		for (i = 0; i < items.length; i++) {
			heightVal[i] = (int) items[i].getHeight();
		}
		System.out.println(Arrays.toString(heightVal));
		// shuffle the height values
		Collections.shuffle(Arrays.asList(heightVal));
		System.out.println(Arrays.toString(heightVal));
		for (i = 0; i < items.length; i++) {
			items[i].setHeight(heightVal[i]);
			items[i].setY(space.getMaxHeight() - heightVal[i]);

		}
		manager.updateItems(items);
	}

	@FXML
	void sortAlgComBox(ActionEvent event) {

	}

	void initSortAlgComBox() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Bubble Sort");
		arr.add("Selection Sort");
		arr.add("Merge Sort");
		arr.add("Heap Sort");
		list = FXCollections.observableArrayList(arr);
		comBox.setItems(list);
	}

	public void showWarningAlert(String header, String content) {
		Alert alert = new Alert(AlertType.WARNING, content);
		alert.setHeaderText(header);
		alert.show();
	}
}
