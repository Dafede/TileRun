package es.wanderteam.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;


public class Puzzle {
	
	TileState[][] map;
	
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
		map = new TileState[pixmap.getHeight()][pixmap.getWidth()];
		int W = pixmap.getWidth();
		int H = pixmap.getHeight();
		System.out.println(NO_TILE + " " + TILE + " " + TILE_WITH_BALL);
		for (int i = 0; i < pixmap.getHeight(); i++) {
			for (int j = 0; j < pixmap.getWidth(); j++) {
				int shit = pixmap.getPixel(j, i);
				int pix = (pixmap.getPixel(j, i) >>> 8) & 0xffffff; // LE MAGIQUE!
				
				System.out.print("(" + i + "," + j + "):" + shit + " " + pix + " ");
				if(pix == NO_TILE) {
					map[i][j] = TileState.NO_TILE;
					continue;
				}
				if(pix == TILE) {
					map[i][j] = TileState.TILE;
					continue;
				}
				
				if(pix == TILE_WITH_BALL) {
					map[i][j] = TileState.TILE_WITH_BALL;
					continue;
				}
				
				if(pix == FINAL_TILE) {
					map[i][j] = TileState.FINAL_TILE;
					continue;
				}
				
				if(pix == INIT_TILE) {
					map[i][j] = TileState.INIT_TILE;
					continue;
				}
			}
			System.out.println();
		}
		
		// Marcar las TILE colindantes a INIT_TILE como NEXT_TILE
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == TileState.INIT_TILE) {
					if(i + 1 < map.length) {
						if(map[i + 1][j] == TileState.TILE)
							map[i + 1][j] = TileState.NEXT_TILE;
						if(map[i + 1][j] == TileState.TILE_WITH_BALL)
							map[i + 1][j] = TileState.NEXT_TILE_WITH_BALL;
					}
					
					if(i - 1 <= 0 ) {
						if(map[i - 1][j] == TileState.TILE)
							map[i - 1][j] = TileState.NEXT_TILE;
						if(map[i - 1][j] == TileState.TILE_WITH_BALL)
							map[i - 1][j] = TileState.NEXT_TILE_WITH_BALL;
					}
					
					if(j + 1 < map[i].length ) {
						if(map[i][j + 1] == TileState.TILE)
							map[i][j + 1] = TileState.NEXT_TILE;
						if(map[i][j + 1] == TileState.TILE_WITH_BALL)
							map[i][j + 1] = TileState.NEXT_TILE_WITH_BALL;
					}
					
					if(j - 1 <= 0 ) {
						if(map[i][j - 1] == TileState.TILE)
							map[i][j - 1] = TileState.NEXT_TILE;
						if(map[i][j - 1] == TileState.TILE_WITH_BALL)
							map[i][j - 1] = TileState.NEXT_TILE_WITH_BALL;
					}
					
				}
			}
		}

		
	}

	public void update(float delta) {
		
	}


	
	
	public TileState[][] getMap() {
		return map;
	}



	
	
}
