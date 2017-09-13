/*
 * The Hanyang univ. Soft Engineering
 * Copyright (c) 2017 SaeHan Lee.
 * All rights reserved.
 */
package cse4006;

/**
 * Graph class
 * 
 * @version hw0 11 Sept. 2017
 * @author SaeHan Lee
 */

public class FriendGraph {
	int Friend_num = 0;
	Boolean[][] Graph;
	String[] Name;
	Friendqueue Queue;
	Boolean[] IsVisited;

	/**
	 * FriendGraph class constructor
	 * 
	 * @param number
	 *            the number of maximum friend
	 */
	FriendGraph(int number) {
		Graph = new Boolean[number][number];
		Name = new String[number];
		Queue = new Friendqueue(number);
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				Graph[i][j] = false;
			}
		}
	}

	/**
	 * Add person function
	 * 
	 * @param person
	 *            person to add
	 */
	void addPerson(Person person) {
		add_name(person.GetName());
	}

	/**
	 * add person's name(string) function
	 * 
	 * @param inS person's name
	 */
	void add_name(String inS) {
		Name[Friend_num++] = inS;
	}

	/**
	 * find index which matching input string function
	 * 
	 * @param inS
	 *            person's name(string)
	 * @return matching index
	 */
	int find_index(String inS) {
		for (int i = 0; i < Friend_num; i++) {
			if (Name[i].equals(inS)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param start
	 *            start person's name
	 * @param finish
	 *            destination person's name
	 */
	void addFriendShip(String start, String finish) {

		index_add_graph(find_index(start), find_index(finish));

	}

	/**
	 * add edge between both vertexes
	 * 
	 * @param start_index
	 *            start person's name
	 * @param finish_index
	 *            destinatin person's name
	 */
	void index_add_graph(int start_index, int finish_index) {
		if (start_index != -1 | finish_index != -1) {
			Graph[start_index][finish_index] = true;
			Graph[finish_index][finish_index] = true;
		}
	}

	/**
	 * calculate distance between persons
	 * 
	 * @param start
	 * @param finish
	 * @return distance
	 */
	int getDistance(String start, String finish) {

		return index_getDistance(find_index(start), find_index(finish));

	}

	/**
	 * calculate distance between converted indexes
	 * 
	 * @param start_index
	 *            start person's index
	 * @param finish_index
	 *            destination person's index
	 * @return distance
	 */
	int index_getDistance(int start_index, int finish_index) {
		int cnt = 1;
		int temp;
		if (start_index == -1 || finish_index == -1) {
			return -1; // if any index can't find (-1) return -1
		} else if (start_index == finish_index)
			return 0; // if both index is same, return 0
		else {
			IsVisited = new Boolean[Friend_num];
			for (int i = 0; i < Friend_num; i++) {
				IsVisited[i] = false;
			}
			IsVisited[start_index] = true;
			Queue.push(start_index);
			/*
			 * we meet the condition so enforce BFS
			 */
			while (!Queue.IsEmpty()) {
				temp = Queue.pop();
				for (int i = 0; i < Friend_num; i++) {
					if (Graph[temp][i] && !IsVisited[i]) {
						if (i == finish_index) {
							return cnt;
						} else {
							IsVisited[i] = true;
							for (int j = 0; j < Friend_num; j++) {
								if (Graph[i][j] && !IsVisited[j]) {
									Queue.push(j);
								}
							}
						}
					}
				}
				cnt++;
			}
		}
		return -1;
	}

}
