package es.wanderteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;


public class Puzzle {
	
	int totalBalls = 0;
	int actualBalls = 0;
	Cell[][] map;
	int initCellX, initCellY;
	int actualCellX, actualCellY;
	boolean completed = false;
	
	public Puzzle() {
		loadFromPNG(Gdx.files.internal("level0.png"));
	}
	
	
	
	/**Genera el puzzle a partir de un PNG con las caracteristica siguientes por pixel:
	 * NO_TILE = WHITE
	 * NORMAL_TILE = BLACK
	 * TILE_WITH_BALL = YELLOW
	 * FINAL_TILE = BLUE
	 * INIT_TILE = GREEN
	 * 
	 * @param fileHandle
	 */
	public void loadFromPNG(FileHandle fileHandle) {
		
		/** Colors from the PNG file **/
		int NO_TILE = 0xffffff; // WHITE
		int TILE = 0x000000; // BLACK
		int INIT_TILE = 0x00ff00; //GREEN
		int FINAL_TILE = 0x0000ff; // BLUE
		int TILE_WITH_BALL = 0xffff00; //YELLOW
		
		Pixmap pixmap = new Pixmap(fileHandle);
		map = new Cell[pixmap.getHeight()][pixmap.getWidth()];
		int W = pixmap.getWidth();
		int H = pixmap.getHeight();
		System.out.println(NO_TILE + " " + TILE + " " + TILE_WITH_BALL);
		for (int i = 0; i < pixmap.getHeight(); i++) {
			for (int j = 0; j < pixmap.getWidth(); j++) {
				int shit = pixmap.getPixel(j, i);
				int pix = (pixmap.getPixel(j, i) >>> 8) & 0xffffff; // LE MAGIQUE!
				
				System.out.print("(" + i + "," + j + "):" + shit + " " + pix + " ");
				if(pix == NO_TILE) {
					map[i][j] = new Cell(false, false, false, false, false);
					continue;
				}
				if(pix == TILE) {
					map[i][j] = new Cell();
					continue;
				}
				
				if(pix == TILE_WITH_BALL) {
					map[i][j] = new Cell(true, false, false, true, false);
					totalBalls++;
					continue;
				}
				
				if(pix == FINAL_TILE) {
					map[i][j] = new Cell(true, false, true, false, false);
					continue;
				}
				
				if(pix == INIT_TILE) {
					map[i][j] = new Cell(true, true, false, false, false);
					initCellY = actualCellY = i;
					initCellX = actualCellX = j;
					continue;
				}
			}
			System.out.println();
		}
		
		// Marcar las TILE colindantes a INIT_TILE como NEXT_TILE
		if(initCellY + 1 < map.length) {
			map[initCellY + 1][initCellX].setNextCell(true);
		}
		
		if(initCellY - 1 >= 0 ) {
			map[initCellY - 1][initCellX].setNextCell(true);
		}
		
		if(initCellX + 1 < map[0].length ) {
			map[initCellY][initCellX + 1].setNextCell(true);
			
		}
		
		if(initCellX - 1 >= 0 ) {
			map[initCellY][initCellX - 1].setNextCell(true);
		}
		
	}

	public void update(float delta) {
		
	}

	
	public Cell[][] getMap() {
		return map;
	}

	public void touchScreen(int x, int y, int pointer) {
		for(int i = 0; i < map.length; ++i){
			for(int j = 0; j < map[i].length; ++j){
				if(map[i][j].isNormalCell() && map[i][j].getTileSprite().getBoundingRectangle().contains(x,y)) {
					touchedCell(i, j);
					break;
				}
			}
		}
		
	}
	private void touchedCell(int i, int j) {
		Cell c = map[i][j];
		if(c.isNextCell() && !c.isOldCell()) {
			System.out.println("IS NEXT CELL");
			if(c.isEndCell() && totalBalls == actualBalls) {
				System.out.println("FINISHED!");
				completed = true;
			}
			c.setNextCell(false);
			c.setOldCell(true);
			//if have ball; take the ball
			if(c.isHaveBall()) {
				c.setHaveBall(false);
				c.setBallSprite(null);
				actualBalls++;
			}
			
			// Desmarcar los alrededores de la cell actual como next
			if(actualCellY + 1 < map.length) map[actualCellY + 1][actualCellX].setNextCell(false);
			
			if(actualCellY - 1 >= 0 )map[actualCellY - 1][actualCellX].setNextCell(false);
			
			if(actualCellX + 1 < map[0].length ) map[actualCellY][actualCellX + 1].setNextCell(false);
			
			if(actualCellX - 1 >= 0 ) map[actualCellY][actualCellX - 1].setNextCell(false);
			
			
			//Marcar los alrededores de la cell que a sido pulsada como next
			if(i + 1 < map.length && !map[i + 1][j].isOldCell()) map[i + 1][j].setNextCell(true);
			
			if(i - 1 >= 0 && !map[i - 1][j].isOldCell() )map[i - 1][j].setNextCell(true);
			
			if(j + 1 < map[0].length && !map[i][j + 1].isOldCell() ) map[i][j + 1].setNextCell(true);
			
			if(j - 1 >= 0 && !map[i][j - 1 ].isOldCell() ) map[i][j - 1].setNextCell(true);

			actualCellY = i;
			actualCellX = j;
			
		}
		
	}
	



	public boolean isCompleted() {
		return completed;
	}




	
}
