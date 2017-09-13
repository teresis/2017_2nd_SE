	/*
	 * The Hanyang univ. Soft Engineering
	 * Copyright (c) 2017 SaeHan Lee.
	 * All rights reserved.
	 */
package cse4006;

	/**
	 * Queue class
	 * 
	 * @version hw0 11 Sept. 2017
	 * @author SaeHan Lee
	 */
public class Friendqueue {
	/**
	 * my own queue structure head = 0 (fix)
	 *
	 */
	public int[] queue;
	private int tail = 0;

	/**
	 * Queue class constructor
	 * 
	 * @param number
	 *            number of maximum Friend
	 */
	Friendqueue(int number) {
		queue = new int[number];
	}

	/**
	 * push function
	 * 
	 * @param input
	 *            number to push(int)
	 */
	void push(int input) {
		queue[tail++] = input;
	}

	/**
	 * pop function
	 * 
	 * @return queue[0](FIFO)
	 */
	int pop() {
		int temp = queue[0];
		for (int i = 1; i < tail; i++) {
			queue[i - 1] = queue[i];
		}
		tail--;
		return temp;
	}

	/**
	 * Check whether is empty
	 */
	Boolean IsEmpty() {
		return tail == 0;
	}

}

