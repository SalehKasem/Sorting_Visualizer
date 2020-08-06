package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SelectionSort {

	public static int i = 0;
	public static int j = i + 1;
	public static int minIndex = i;

	public static void sort(Rectangle[] arr) {

		if (arr[j].getY() < arr[minIndex].getY()) {
			minIndex = j;
			Platform.runLater(() -> {
				arr[j].setStroke(Color.RED);
				//arr[minIndex].setStroke(Color.RED);
			});
		}
	}
	
	public static void swape(Rectangle[] arr) {
		double y,height;
		
		y = arr[minIndex].getY();
		height = arr[minIndex].getHeight();

		arr[minIndex].setY(arr[i].getY());
		arr[minIndex].setHeight(arr[i].getHeight());

		arr[i].setY(y);
		arr[i].setHeight(height);
//		Platform.runLater(() -> {
//			arr[j].setStroke(Color.RED);
//			// arr[j+1].setStroke(Color.RED);
//		});
	}

}
