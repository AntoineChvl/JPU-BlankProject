package com.entity.mobileelements;

import java.io.IOException;

import com.collisionshandler.CollisionsHandler;
import com.entity.motionlesselements.Path;

import entity.Entity;
import entity.Sprite;

/**
 * The Abstract Class MobileElements.
 *
 * @author Exia CESI - Saint-Nazaire - Group 5
 */

public abstract class MobileElements extends Entity {

	private int diamondsCounter;
	
	public MobileElements(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public void entityMove(int x, int y, int sideX, char direction) {

		final int xpos = this.getPositionX();
		final int ypos = this.getPositionY();
		final Entity[][] loadArrayMap = this.getMap().getArrayMap();
		final CollisionsHandler getCollisionHandler = this.getMap().getCollisionsHandler();
		boolean collision = false;
		boolean isDiamond = false;
		boolean moveStone = false;
		
			if(this instanceof Player) {
				collision = getCollisionHandler.checkForCollisions(loadArrayMap,xpos + x, ypos + y);
				isDiamond = getCollisionHandler.checkForDiamonds(loadArrayMap, xpos + x,ypos + y);
				moveStone = getCollisionHandler.checkForStoneToMove(loadArrayMap, xpos + x, ypos + y, sideX);
			}else {
				collision = getCollisionHandler.checkForPath(loadArrayMap,xpos + x, ypos + y);
			}
				

			this.loadImage(direction, this);
			
			if(moveStone) {
				loadArrayMap[xpos + x + sideX][ypos + y] = loadArrayMap[xpos + x][ypos + y];
				loadArrayMap[xpos + x][ypos + y] = loadArrayMap[xpos][ypos];
				loadArrayMap[xpos][ypos] = new Path(xpos, ypos);
				this.setPositionY(ypos + y);
				this.setPositionX(xpos + x);
				loadArrayMap[xpos + x + sideX][ypos + y].setPositionX(xpos + x + sideX);
			}
			
			if (!collision) {

				loadArrayMap[xpos + x][ypos + y] = loadArrayMap[xpos][ypos];
				loadArrayMap[xpos][ypos] = new Path(xpos, ypos);
				this.setPositionY(ypos + y);
				this.setPositionX(xpos + x);
			}

			if (isDiamond == true) {
				this.incrementDiamondsCounter();
			}
	}

	public void loadImage(char direction, Entity entity) {

		switch (direction) {

		case 'Z':
			entity.setSprite(entity.getSpriteUp());
			try {
				entity.getSprite().loadImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 'S':
			entity.setSprite(entity.getSpriteDown());
			try {
				entity.getSprite().loadImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 'Q':
			entity.setSprite(entity.getSpriteTurnLeft());
			try {
				entity.getSprite().loadImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 'D':
			entity.setSprite(entity.getSpriteTurnRight());
			try {
				entity.getSprite().loadImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 'X':
			entity.setSprite(entity.getSpriteDeath());
			try {
				entity.getSprite().loadImage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void incrementDiamondsCounter() {
		this.diamondsCounter++;
	}

	public int getDiamondsCounter() {
		return this.diamondsCounter;
	}

	

}
