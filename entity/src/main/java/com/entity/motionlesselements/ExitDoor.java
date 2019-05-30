package com.entity.motionlesselements;

import java.io.IOException;

import entity.Sprite;

/**
 * The ExitDoor class.
 * @author Antoine Chauvel
 * @version 1.0
 *
 */
public class ExitDoor extends MotionlessElements{

	/**
	 * The ExitDoor's default sprite
	 */
	private static final Sprite sprite = new Sprite('e',"Door.png");
	static {
		try {
			sprite.loadImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * The ExitDoor's constructor
	 * @param x the x of the exit door
	 * @param y the y of the exit door
	 */
	public ExitDoor(int x, int y) {
		super(sprite, x, y);
		// TODO Auto-generated constructor stub
	}
	
	

}
