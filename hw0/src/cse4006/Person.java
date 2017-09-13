/*
 * The Hanyang univ. Soft Engineering
 * Copyright (c) 2017 SaeHan Lee.
 * All rights reserved.
 */
package cse4006;

/**
 * Person class
 * 
 * @version hw0 11 Sept. 2017
 * @author SaeHan Lee
 */
public class Person {
	/** person's name(String) */
	String Name;

	/**
	 * Person class constructor
	 * 
	 * @param name
	 *            Person's name (String)
	 */
	Person(String name) {
		Name = name;
	}
	
	/**
	 * get for Person's name
	 * 
	 * @return Person's name(String)
	 */
	String GetName() {
		return Name;
	}

}
