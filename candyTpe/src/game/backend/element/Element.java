package game.backend.element;

import game.backend.move.Direction;

public abstract class Element {

	private boolean explode = true;
	
	public abstract boolean isMovable();
	
	public abstract String getKey();
	
	public String getFullKey() {
		return getKey();
	}

	public boolean isSolid() {
		return true;
	}
	
	public Direction[] explode() {
		return null;
	}
	
	public long getScore() {
		return 0;
	}

	public String stringSpecial(){
		return null;
	}
	public boolean getExplode(){
		return explode;
	}

	public void setExplode(boolean explode){
		this.explode = explode;
	}
	
}
