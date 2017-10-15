/*
 * The Hanyang univ. Soft Engineering
 * Copyright (c) 2017 SaeHan Lee.
 * All rights reserved.
 */
package cse4006;

/**
 * Main class
 * 
 * @version hw0 11 Sept. 2017
 * @author SaeHan Lee
 */
public class main {
	/**
	 * main function -entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create Graph object
		FriendGraph G = new FriendGraph(50); // this can handle maximum number of friends by changing number

		// Create Person objects
		Person john = new Person("John");
		Person tom = new Person("Tom");
		Person jane = new Person("Jane");
		Person marry = new Person("Marry");

		// Add persons
		G.addPerson(john);
		G.addPerson(tom);
		G.addPerson(jane);
		G.addPerson(marry);

		// Add friendship
		G.addFriendShip("John", "Tom");
		G.addFriendShip("Tom", "Jane");

		// Calculate a results
		System.out.println(G.getDistance("John", "Tom"));
		System.out.println(G.getDistance("John", "Jane"));
		System.out.println(G.getDistance("John", "John"));
		System.out.println(G.getDistance("John", "Marry"));
		System.out.println(G.getClass().getName());

	}

}