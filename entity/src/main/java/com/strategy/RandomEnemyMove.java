package com.strategy;

import com.entity.mobileelements.Enemy;

public class RandomEnemyMove extends Strategy<Enemy> {

	
	private Enemy me = null;
	
	public RandomEnemyMove(Enemy me) {
		this.me = me;
	}

	@Override
	public void runStrategy() {
		
		double randomDirection = Math.random();
		randomDirection = randomDirection * 4 +1;
		int randomDirInt = (int) randomDirection;
		switch(randomDirInt) {
		case 1:
			me.entityMove(0, -1, 0, 'S');
			me.getMap().getPlayer().playerDeathLinkToEnemy();
			break;
		case 2:
			me.entityMove(-1, 0, 0, 'S');
			me.getMap().getPlayer().playerDeathLinkToEnemy();
			break;
		case 3:
			me.entityMove(0, 1, 0, 'S');
			me.getMap().getPlayer().playerDeathLinkToEnemy();
			break;
		case 4:
			me.entityMove(+1, 0, 0, 'S');
			me.getMap().getPlayer().playerDeathLinkToEnemy();
			break;
		}
		
	}

}