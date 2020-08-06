package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SortingManager {

	Rectangle[] items;
	SortingMethod method;
	Manager manager;
	int i = 0, j = 0;

	public SortingManager(Manager manager, Rectangle[] items, SortingMethod method) {
		this.items = items;
		this.method = method;
		this.manager = manager;
	}

	public void sort() {
		if (method == SortingMethod.BubbleSort) {

			Platform.runLater(() -> {
				BubbleSort.sort(items);
				items[j].setStroke(Color.CYAN);
				items[j + 1].setStroke(Color.CYAN);
				j++;
				if (j >= (items.length - i - 1)) {
					j = 0;
					i++;
					if (i == (items.length - 1)) {
						manager.stop();
						i = 0;
						j = 0;
					}
				}
				indexUpdate(j);
			});
		} else if (method == SortingMethod.SelectionSort) {
			Platform.runLater(() -> {
				SelectionSort.sort(items);
				items[SelectionSort.j].setStroke(Color.CYAN);
				// items[j + 1].setStroke(Color.CYAN);
				SelectionSort.j++;
				if (j >= (items.length)) {
					SelectionSort.i++;
					SelectionSort.swape(items);
					SelectionSort.j = SelectionSort.i + 1;
					if (SelectionSort.i == (items.length - 1)) {
						manager.stop();
						SelectionSort.i = 0;
						SelectionSort.j = SelectionSort.i + 1;
						SelectionSort.minIndex = SelectionSort.i;
					}
				}
			});
		}
	}

	public void indexUpdate(int j) {
		if (method == SortingMethod.BubbleSort) {

			BubbleSort.j = j;
		}

	}

}
