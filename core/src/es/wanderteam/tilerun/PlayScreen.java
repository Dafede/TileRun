package es.wanderteam.tilerun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PlayScreen implements Screen, InputProcessor {

	SpriteBatch batch;
	
	/** Visual game data **/
	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Game.pack"));
	
	
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
	
	int[][] board = new int[][]{
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
	
	Sprite[][] boardSprites = new Sprite[][]{
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null},
			{ null, null, null, null}
	};
	
	//Percentaje of space between tiles
	float padding = 0.01f;
	float tileSpriteWidth, tileSpriteHeight;
	int nowTileX, nowTileY;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		
		int numTilesHeight = board.length;
		int numTilesWight = board[board.length - 1].length; //error si la matriz board no esta definida
		int screenHeight = Gdx.graphics.getHeight();
		int screenWidth = Gdx.graphics.getWidth();
		float paddingWidth = screenWidth * padding;
		float paddingHeight = screenHeight * padding;
		
		tileSpriteWidth = ( screenWidth / numTilesWight ) - 2 * paddingWidth;
		tileSpriteHeight = ( screenHeight / numTilesHeight ) - 2 * paddingHeight;
		
		
		System.out.println("Tile Data: " + tileSpriteWidth + " " + tileSpriteHeight + " " +paddingWidth + " " + paddingHeight);
		
		for(int i = 0; i < board.length; ++i){
			for(int j = 0; j < board[i].length; ++j){
								
				if(board[i][j] != 0){
					
					if(board[i][j] == 4){
						nowTileX = i;
						nowTileY = j;
					}
					
					Sprite newTile = new Sprite(atlas.findRegion("tileBoxGreen"));
					newTile.setSize(tileSpriteWidth, tileSpriteHeight);
					
					newTile.setX(j * tileSpriteWidth + (2 * j + 1) * (screenWidth * padding));
					newTile.setY(screenHeight - ((i + 1) * tileSpriteHeight + (2 * i + 1) * (screenHeight * padding)));

					System.out.println(i + " " + j + " :" + newTile.getX() + " " + newTile.getY());
					boardSprites[i][j] = newTile;
				}
			}
		}
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// UPDATE
		
		
		
		// DRAW
		Sprite ball = new Sprite(atlas.findRegion("itemYellow"));
		batch.begin();
		
		for(int i = 0; i < boardSprites.length; ++i){
			for(int j = 0; j < boardSprites[i].length; ++j){
				if(boardSprites[i][j] != null){
					switch (board[i][j]) {
					case 0:
						//Dont draw
						break;
					case 1:
						//Draw normal tile
						boardSprites[i][j].draw(batch);
						break;
					case 2:
						//Draw normal tile
						boardSprites[i][j].draw(batch);
						//And ball
						ball.setX(boardSprites[i][j].getX());
						ball.setY(boardSprites[i][j].getY());
						ball.setSize(tileSpriteWidth, tileSpriteHeight);
						ball.draw(batch);
						break;
					case 3:
						//Draw normal tile grayed
						boardSprites[i][j].setColor(Color.GRAY);
						boardSprites[i][j].draw(batch);
						break;
					case 4:
						//Draw normal tile GREEN
						boardSprites[i][j].setColor(Color.GREEN);
						boardSprites[i][j].draw(batch);						
						break;
					case 5:
						//Draw normal tile highlighted
						boardSprites[i][j].setColor(Color.BLUE);
						boardSprites[i][j].draw(batch);
						break;
					case 6:
						//Draw normal tile
						boardSprites[i][j].draw(batch);
						break;
					default:
					case 7:
						//Draw normal tile
						boardSprites[i][j].setColor(Color.BLUE);
						boardSprites[i][j].draw(batch);
						//And ball
						ball.setX(boardSprites[i][j].getX());
						ball.setY(boardSprites[i][j].getY());
						ball.setSize(tileSpriteWidth, tileSpriteHeight);
						ball.draw(batch);
						break;
					}
				}
			}
		}
		
		batch.end();
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
		int realScreenX = screenX;
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
		
		Sprite oldTileSprite;
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
			if(touchedTileX + 1 <board.length && board[touchedTileX + 1][touchedTileY] == 1) board[touchedTileX + 1][touchedTileY] = 5;
			if(touchedTileY + 1 < boardSprites[touchedTileX].length && board[touchedTileX][touchedTileY + 1] == 1) board[touchedTileX][touchedTileY + 1] = 5;
			if(touchedTileX - 1 >= 0 && board[touchedTileX - 1][touchedTileY] == 1) board[touchedTileX - 1][touchedTileY] = 5;
			if(touchedTileY - 1 >= 0 && board[touchedTileX][touchedTileY - 1] == 1) board[touchedTileX][touchedTileY - 1] = 5;

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
			if(touchedTileX + 1 <board.length && board[touchedTileX + 1][touchedTileY] == 1) board[touchedTileX + 1][touchedTileY] = 5;
			if(touchedTileY + 1 < boardSprites[touchedTileX].length && board[touchedTileX][touchedTileY + 1] == 1) board[touchedTileX][touchedTileY + 1] = 5;
			if(touchedTileX - 1 >= 0 && board[touchedTileX - 1][touchedTileY] == 1) board[touchedTileX - 1][touchedTileY] = 5;
			if(touchedTileY - 1 >= 0 && board[touchedTileX][touchedTileY - 1] == 1) board[touchedTileX][touchedTileY - 1] = 5;

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
		}
		
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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
