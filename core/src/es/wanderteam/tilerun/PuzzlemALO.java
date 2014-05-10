package es.wanderteam.tilerun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import es.wanderteam.game.TileState;


public class PuzzlemALO{
	
	TileState[][] initialBoard;
	//PuzzleTile[][] boardPuzzleTile;
	Skin gameSkin;
	
	int numTilesHeight, numTilesWight;
	int screenHeight, screenWidth;
	float paddingWidth, paddingHeight;
	float padding = 0.01f;
	float puzzleTileWidth, puzzleTileHeight;
	
	public PuzzlemALO(TileState[][] initialBoard, Skin skin) {
		super();
		
		this.initialBoard = initialBoard;
		gameSkin = skin;
		
		if(initialBoard != null) 
			GenerateTilesFromBoard();
	}
	
	private void GenerateTilesFromBoard() {
		numTilesHeight = initialBoard.length;
		numTilesWight = initialBoard[initialBoard.length - 1].length;
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		paddingWidth = screenWidth * padding;
		paddingHeight = screenHeight * padding;
		puzzleTileWidth = ( screenWidth / numTilesWight ) - 2 * paddingWidth;
		puzzleTileHeight = ( screenHeight / numTilesHeight ) - 2 * paddingHeight;
		
		/*boardPuzzleTile = new PuzzleTile[numTilesHeight][];
		
		for(int i = 0; i < numTilesHeight; ++i){
			boardPuzzleTile[i] = new PuzzleTile[numTilesWight];
			for(int j = 0; j < numTilesWight; ++j){
				boardPuzzleTile[i][j] = new PuzzleTile(i, j, puzzleTileWidth, puzzleTileHeight,
						j * puzzleTileWidth + (2 * j + 1) * (screenWidth * padding),
						screenHeight - ((i + 1) * puzzleTileHeight + (2 * i + 1) * (screenHeight * padding)),
						initialBoard[i][j], gameSkin);
			}
		}
		*/
	}

/*	public void draw(SpriteBatch batch) {
		for(int i = 0; i < numTilesHeight; ++i){
			for(int j = 0; j < numTilesWight; ++j){
				boardPuzzleTile[i][j].draw(batch);
				
			}
		}	
	}
*/
	public void touchPuzzle(int screenX, int screenY, int pointer) {
		System.out.println(screenX + " " + screenY);
		int realScreenX = screenX;
		int realScreenY = Gdx.graphics.getHeight() - screenY;
		int touchedTileX = 0, touchedTileY = 0;
		/*for(int i = 0; i < boardPuzzleTile.length; ++i){
			for(int j = 0; j < boardPuzzleTile[i].length; ++j){
				if(boardPuzzleTile[i][j] != null && boardPuzzleTile[i][j].getTile().get.getBoundingRectangle().contains(realScreenX, realScreenY))
				{
					touchedTileX = i;
					touchedTileY = j;
					break;
				}
			}
		}*/
		
	}
	
	
}
