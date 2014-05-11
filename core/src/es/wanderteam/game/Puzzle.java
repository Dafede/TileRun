package es.wanderteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;


public class Puzzle {
	
	Cell[][] map;
	int initCellX, initCellY;
	
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
					continue;
				}
				
				if(pix == FINAL_TILE) {
					map[i][j] = new Cell(true, false, true, false, false);
					continue;
				}
				
				if(pix == INIT_TILE) {
					map[i][j] = new Cell(true, true, false, false, false);
					initCellY = i;
					initCellX = j;
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



	
	
}
