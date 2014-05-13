package es.wanderteam.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cell {

	private boolean normalCell = true;
	private boolean initCell = false;
	private boolean endCell = false;
	private boolean haveBall = false;
	private boolean nextCell = false;
	private boolean oldCell = false;
	
	private Sprite tileSprite = null;
	private Sprite ballSprite = null;
		
	public Cell() {
		super();
	}
	
	public Cell(boolean normalCell, boolean initCell, boolean endCell,
			boolean haveBall, boolean nextCell) {
		super();
		this.normalCell = normalCell;
		this.initCell = initCell;
		this.endCell = endCell;
		this.haveBall = haveBall;
		this.nextCell = nextCell;
	}
	
	public void draw(Batch spriteBatch) {
		if(tileSprite != null) tileSprite.draw(spriteBatch);
		if(ballSprite != null) ballSprite.draw(spriteBatch);
	}
	
	public boolean isNormalCell() {
		return normalCell;
	}

	public boolean isInitCell() {
		return initCell;
	}

	public void setInitCell(boolean initCell) {
		this.initCell = initCell;
		setColorByState();
	}

	public boolean isEndCell() {
		return endCell;
	}

	public void setEndCell(boolean endCell) {
		this.endCell = endCell;
		setColorByState();
	}

	public boolean isHaveBall() {
		return haveBall;
	}

	public void setHaveBall(boolean haveBall) {
		this.haveBall = haveBall;
	}

	public boolean isNextCell() {
		return nextCell;
	}

	public void setNextCell(boolean nextCell) {
		this.nextCell = nextCell;
		setColorByState();
	}

	public boolean isOldCell() {
		return oldCell;
	}

	public void setOldCell(boolean oldCell) {
		this.oldCell = oldCell;
		setColorByState();
	}

	public Sprite getTileSprite() {
		return tileSprite;
	}

	public void setTileSprite(Sprite tileSprite) {
		this.tileSprite = tileSprite;
	}

	public Sprite getBallSprite() {
		return ballSprite;
	}

	public void setBallSprite(Sprite ballSprite) {
		this.ballSprite = ballSprite;
	}

	private void setColorByState(){
		if(tileSprite != null) 
		{
			if(initCell == true) {
				tileSprite.setColor(Color.YELLOW);
				return;
			}
			if(endCell == true) {
				tileSprite.setColor(Color.BLUE);
				return;
			}
			
			if(oldCell == true) {
				tileSprite.setColor(Color.RED);
				return;
			}
			if(nextCell == true) {
				tileSprite.setColor(Color.GRAY);
				return;
			}
			
			
			tileSprite.setColor(Color.WHITE);
			return;	
		}
		
	}


	
}
