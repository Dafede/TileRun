package es.wanderteam.tilerun;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import es.wanderteam.game.Puzzle;
import es.wanderteam.game.PuzzleRenderer;
import es.wanderteam.game.TileState;

public class PlayScreenNewParadigm implements Screen, InputProcessor {

	SpriteBatch batch;
	BitmapFont winFont;
	
	
	/** Visual game data **/
	TextureAtlas atlas;
	Skin gameVisualSkin;
	
	/** Logic game data **/
	/** 0 == No Tile
	 *  1 == Tile
	 *  2 == Ball
	 *  3 == Fin
	 *  4 == Now
	 *  5 == Next
	 *  6 == Old
	 *  7 == Next and Ball
	 */
	int actualLevel = 0;
	TileState[][] level0 = new TileState[][]{
			{ TileState.NO_TILE, TileState.TILE, TileState.TILE_WITH_BALL, TileState.TILE},
			{ TileState.FINAL_TILE, TileState.TILE, TileState.NO_TILE, TileState.TILE},
			{ TileState.NO_TILE, TileState.TILE, TileState.TILE, TileState.TILE},
			{ TileState.NO_TILE, TileState.NO_TILE, TileState.NO_TILE, TileState.TILE},
			{ TileState.TILE, TileState.TILE, TileState.TILE, TileState.TILE_WITH_BALL},
			{ TileState.TILE, TileState.NO_TILE, TileState.NO_TILE, TileState.TILE},
			{ TileState.TILE_WITH_BALL, TileState.TILE, TileState.TILE, TileState.TILE},
			{ TileState.NO_TILE, TileState.NO_TILE, TileState.NO_TILE, TileState.NEXT_TILE},
			{ TileState.NO_TILE, TileState.NO_TILE, TileState.NO_TILE, TileState.ACTUAL_TILE}
	};
	int[][] level1 = new int[][]{
			{ 0, 1, 2, 1},
			{ 3, 1, 0, 1},
			{ 0, 1, 1, 1},
			{ 0, 0, 0, 1},
			{ 1, 1, 1, 2},
			{ 1, 0, 0, 1},
			{ 2, 1, 1, 1},
			{ 0, 0, 0, 5},
			{ 0, 0, 0, 4}
	};
	int[][] level2 = new int[][]{
			{ 2, 1, 1, 1, 3},
			{ 1, 0, 1, 2, 2},
			{ 1, 1, 2, 1, 1},
			{ 5, 0, 1, 1, 2},
			{ 4, 5, 1, 2, 1}
	};
	int[][] level3 = new int[][]{
			{ 0, 0, 3, 0, 0},
			{ 2, 2, 1, 1, 2},
			{ 1, 1, 1, 1, 1},
			{ 1, 1, 1, 1, 1},
			{ 1, 2, 2, 2, 1},
			{ 1, 0, 1, 1, 2},
			{ 1, 2, 1, 0, 1},
			{ 1, 2, 1, 1, 1},
			{ 0, 5, 4, 0, 0}
	};
	ArrayList<TileState[][]> listaBoard = new ArrayList<TileState[][]>();
		
	Puzzle puzzle;
	PuzzleRenderer renderer;
	
	@Override
	public void show() {
		//CONSTRUCTOR
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		atlas = new TextureAtlas(Gdx.files.internal("Game.pack"));
		gameVisualSkin = new Skin(atlas);
		
		listaBoard.add(level0);
		//listaBoard.add(level1);
		//listaBoard.add(level2);
		//listaBoard.add(level3);
		
		puzzle = new Puzzle();
		renderer = new PuzzleRenderer(puzzle);
		
		//winFont = new BitmapFont(Gdx.files.internal("Calibri.fnt"),Gdx.files.internal("Calibri.png"),false);
	}
	
	
	@Override
	public void render(float delta) {
		//RENDER SE LLAMA CADA VEZ
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		puzzle.update(delta);
		renderer.render(delta);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		/*int realScreenX = screenX;
		int realScreenY = Gdx.graphics.getHeight() - screenY;
		int touchedTileX = 0, touchedTileY = 0;
		for(int i = 0; i < boardSprites.length; ++i){
			for(int j = 0; j < boardSprites[i].length; ++j){
				if(boardSprites[i][j] != null && boardSprites[i][j].getBoundingRectangle().contains(realScreenX, realScreenY))
				{
					touchedTileX = i;
					touchedTileY = j;
					break;
				}
			}
		}
		/** Logic game data **/
		/** 0 == No Tile
		 *  1 == Tile
		 *  2 == Ball
		 *  3 == Fin
		 *  4 == Now
		 *  5 == Next
		 *  6 == Old
		 *  7 == Next and Ball
		 */
		/*Sprite oldTileSprite;
		System.out.println("Touch UP inside " + touchedTileX + " " + touchedTileY);
		switch (board[touchedTileX][touchedTileY]) {
		case 0:
			System.out.println("NO TILE");
			break;
		case 1:
			System.out.println("NORMAL TILE");
			break;
		case 2:
			System.out.println("BALL TILE");
			break;
		case 3:
			System.out.println("FIN");
			if(touchedTileX + 1 <board.length)
			{
				if(board[touchedTileX + 1][touchedTileY] == 4)
				{
					GenerateLevel(++actualLevel);
				}
			}
			
			if(touchedTileY + 1 < boardSprites[touchedTileX].length)
			{
				if(board[touchedTileX][touchedTileY + 1] == 4)
				{
					GenerateLevel(++actualLevel);
				}
			}
			
			if(touchedTileX - 1 >= 0)
			{
				if(board[touchedTileX - 1][touchedTileY] == 4)
				{
					GenerateLevel(++actualLevel);
				}
			}
			
			if(touchedTileY - 1 >= 0)
			{
				if(board[touchedTileX][touchedTileY - 1] == 4)
				{
					GenerateLevel(++actualLevel);
				}
			}
			break;
		case 4:
			System.out.println("NOW TILE");
			break;
		case 5:
			System.out.println("NEXT TILE");
			//Search next and block it
			for(int ii = 0; ii < boardSprites.length; ++ii){
				for(int jj = 0; jj < boardSprites[touchedTileX].length; ++jj){
					if(board[ii][jj] == 5){
						boardSprites[ii][jj].setColor(Color.WHITE);
						board[ii][jj] = 1;
					if(board[ii][jj] == 7){
						boardSprites[ii][jj].setColor(Color.WHITE);
						board[ii][jj] = 2;
					}
					}
				}
			}

			board[touchedTileX][touchedTileY] = 4;
			if(touchedTileX + 1 <board.length)
			{
				if(board[touchedTileX + 1][touchedTileY] == 1) board[touchedTileX + 1][touchedTileY] = 5;
				if(board[touchedTileX + 1][touchedTileY] == 2) board[touchedTileX + 1][touchedTileY] = 7;
			}
			
			if(touchedTileY + 1 < boardSprites[touchedTileX].length)
			{
				if(board[touchedTileX][touchedTileY + 1] == 1) board[touchedTileX][touchedTileY + 1] = 5;
				if(board[touchedTileX][touchedTileY + 1] == 2) board[touchedTileX][touchedTileY + 1] = 7;
			}
			
			if(touchedTileX - 1 >= 0)
			{
				if(board[touchedTileX - 1][touchedTileY] == 1) board[touchedTileX - 1][touchedTileY] = 5;
				if(board[touchedTileX - 1][touchedTileY] == 2) board[touchedTileX - 1][touchedTileY] = 7;
			}
			
			if(touchedTileY - 1 >= 0)
			{
				if(board[touchedTileX][touchedTileY - 1] == 1) board[touchedTileX][touchedTileY - 1] = 5;
				if(board[touchedTileX][touchedTileY - 1] == 2) board[touchedTileX][touchedTileY - 1] = 7;
			}

			oldTileSprite = new Sprite(atlas.findRegion("tileBoxRed"));
			oldTileSprite.setX(boardSprites[nowTileX][nowTileY].getX());
			oldTileSprite.setY(boardSprites[nowTileX][nowTileY].getY());
			oldTileSprite.setSize(boardSprites[nowTileX][nowTileY].getWidth(), boardSprites[nowTileX][nowTileY].getHeight());
			boardSprites[nowTileX][nowTileY] = oldTileSprite;
			board[nowTileX][nowTileY] = 6;
			
			nowTileX = touchedTileX;
			nowTileY = touchedTileY;
			break;
		case 6:
			System.out.println("OLD TILE");
			break;
		case 7:
			System.out.println("NEXT AND BALL");
			//Search next and block it
			for(int ii = 0; ii < boardSprites.length; ++ii){
				for(int jj = 0; jj < boardSprites[touchedTileX].length; ++jj){
					if(board[ii][jj] == 5){
						boardSprites[ii][jj].setColor(Color.WHITE);
						board[ii][jj] = 1;
					}
					if(board[ii][jj] == 7){
						boardSprites[ii][jj].setColor(Color.WHITE);
						board[ii][jj] = 2;
					}
				}
			}
			
			
			board[touchedTileX][touchedTileY] = 4;
			if(touchedTileX + 1 <board.length)
			{
				if(board[touchedTileX + 1][touchedTileY] == 1) board[touchedTileX + 1][touchedTileY] = 5;
				if(board[touchedTileX + 1][touchedTileY] == 2) board[touchedTileX + 1][touchedTileY] = 7;
			}
			
			if(touchedTileY + 1 < boardSprites[touchedTileX].length)
			{
				if(board[touchedTileX][touchedTileY + 1] == 1) board[touchedTileX][touchedTileY + 1] = 5;
				if(board[touchedTileX][touchedTileY + 1] == 2) board[touchedTileX][touchedTileY + 1] = 7;
			}
			
			if(touchedTileX - 1 >= 0)
			{
				if(board[touchedTileX - 1][touchedTileY] == 1) board[touchedTileX - 1][touchedTileY] = 5;
				if(board[touchedTileX - 1][touchedTileY] == 2) board[touchedTileX - 1][touchedTileY] = 7;
			}
			
			if(touchedTileY - 1 >= 0)
			{
				if(board[touchedTileX][touchedTileY - 1] == 1) board[touchedTileX][touchedTileY - 1] = 5;
				if(board[touchedTileX][touchedTileY - 1] == 2) board[touchedTileX][touchedTileY - 1] = 7;
			}

			oldTileSprite = new Sprite(atlas.findRegion("tileBoxRed"));
			oldTileSprite.setX(boardSprites[nowTileX][nowTileY].getX());
			oldTileSprite.setY(boardSprites[nowTileX][nowTileY].getY());
			oldTileSprite.setSize(boardSprites[nowTileX][nowTileY].getWidth(), boardSprites[nowTileX][nowTileY].getHeight());
			boardSprites[nowTileX][nowTileY] = oldTileSprite;
			board[nowTileX][nowTileY] = 6;
			
			nowTileX = touchedTileX;
			nowTileY = touchedTileY;
			break;
		default:
			break;
		}*/
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
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
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
