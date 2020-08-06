package application;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Manager implements Runnable {

	SortingManager sortingManager;
	Rectangle[] items;
	Pane space;
	Button sortBtn;
	Button shuffleBtn;
	Thread thread;
	private SortingMethod sortMethod;
	private boolean running;
	private int speed =20000;

	public Manager(Rectangle[] items, Pane space, Button sortBtn, Button shuffleBtn) {
		this.items = items;
		this.space = space;
		this.sortBtn = sortBtn;
		this.shuffleBtn = shuffleBtn;
	}

	public synchronized void start() {
		running = true;
		shuffleBtn.setDisable(true);
		sortBtn.setDisable(true);
		thread = new Thread(this, "Manager");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shuffleBtn.setDisable(false);
		sortBtn.setDisable(false);
	}

	@Override
	public void run() {
		sortingManager = new SortingManager(this, items, sortMethod);
		long lastTime = System.nanoTime();
		long delta = 0;
		final double ns = 1000000000.0;

		while (running) {

			long now = System.nanoTime();

			delta += (long) ((now - lastTime) / (ns / speed));
			lastTime = now;
			System.out.println(speed);
			while (delta >= 1) {
				//sortingManager.updateColors();

				update();
				delta--;
			}
			//sortingManager.updateColors();
		}
	}

	private void update() {
			sortingManager.sort();
			
	}

	public SortingMethod getSortMethod() {
		return sortMethod;
	}

	public void setSortMethod(SortingMethod sortMethod) {
		this.sortMethod = sortMethod;
	}

	public void updateItems(Rectangle[] items) {
		this.items = items;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
