package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BubbleSort {

	public static int i = 0;
	public static int j = 0;


	public static void sort(Rectangle[] arr) {

		double y, height;

		//for (j = 0; j < arr.length - i - 1; j++) {
			if (arr[j].getY() < arr[j + 1].getY()) {
				// swap
				y = arr[j].getY();
				height = arr[j].getHeight();

				arr[j].setY(arr[j + 1].getY());
				arr[j].setHeight(arr[j + 1].getHeight());

				arr[j + 1].setY(y);
				arr[j + 1].setHeight(height);
				Platform.runLater(() -> {
					arr[j].setStroke(Color.RED);
					// arr[j+1].setStroke(Color.RED);
				});
			}
	//	}
		i++;
	}
}
