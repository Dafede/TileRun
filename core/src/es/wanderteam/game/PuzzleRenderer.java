package es.wanderteam.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PuzzleRenderer {

	Puzzle puzzle;
	//Sprite[][] spriteMap;
	//List<Sprite> ballSpriteList;
	SpriteBatch spriteBatch = new SpriteBatch();
	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Game.pack"));
	
	float padding = 0.01f;
	
	int screenHeight = Gdx.graphics.getHeight();
	int screenWidth = Gdx.graphics.getWidth();
	float paddingWidth = screenWidth * padding;
	float paddingHeight = screenHeight * padding;
	float tileSpriteWidth, tileSpriteHeight;

	
	public PuzzleRenderer(Puzzle p) {
		puzzle = p;
		//ballSpriteList = new ArrayList<Sprite>();
		createSprites();
		puzzle = p;
	}
		
	private void createSprites() {
		Cell[][] map = puzzle.getMap();
		int numTilesHeight = map.length;
		int numTilesWight = map[0].length;
		
		tileSpriteWidth = ( screenWidth / numTilesWight ) - 2 * paddingWidth;
		tileSpriteHeight = ( screenHeight / numTilesHeight ) - 2 * paddingHeight;
		
		//spriteMap = new Sprite[map.length][];
		for(int i = 0; i < map.length; ++i) {
			//spriteMap[i] = new Sprite[map[i].length];
			for(int j = 0; j < map[i].length; ++j) {
				
				if(map[i][j].isNormalCell()) {
					map[i][j].setTileSprite(getTileSprite(i,j, Color.WHITE));
					if(map[i][j].isNextCell())
						map[i][j].getTileSprite().setColor(Color.GRAY);
					if(map[i][j].isInitCell())
						map[i][j].getTileSprite().setColor(Color.GREEN);
					if(map[i][j].isEndCell())
						map[i][j].getTileSprite().setColor(Color.BLUE);
					if(map[i][j].isHaveBall())
						map[i][j].setBallSprite(getBallSprite(i, j));
					
				}
			}
		}
		
	}

	
	private Sprite getTileSprite(int i, int j, Color c) {
		Sprite theNewSprite;
		theNewSprite = new Sprite(atlas.findRegion("tileBoxGreen"));
		theNewSprite.setSize(tileSpriteWidth, tileSpriteHeight);
		theNewSprite.setX(j * tileSpriteWidth + (2 * j + 1) * (screenWidth * padding));
		theNewSprite.setY(screenHeight - ((i + 1) * tileSpriteHeight + (2 * i + 1) * (screenHeight * padding)));
		theNewSprite.setColor(c);
		
		return theNewSprite;
	}
	
	private Sprite getBallSprite(int i, int j) {
		Sprite ball = new Sprite(atlas.findRegion("itemYellow"));
		ball.setSize(tileSpriteWidth, tileSpriteHeight);
		ball.setX(j * tileSpriteWidth + (2 * j + 1) * (screenWidth * padding));
		ball.setY(screenHeight - ((i + 1) * tileSpriteHeight + (2 * i + 1) * (screenHeight * padding)));
		
		return ball;
	}
	
	
	public void render (float deltaTime) {
		spriteBatch.begin();
		renderCells();
		spriteBatch.end();
	}
	
	private void renderCells() {
		Cell[][] cellMap = puzzle.getMap();
		for(int i = 0; i < cellMap.length; ++i) {
			for(int j = 0; j < cellMap[i].length; ++j) {
				cellMap[i][j].draw(spriteBatch);
			}
		}
	}
	
}
