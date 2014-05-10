package es.wanderteam.game;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PuzzleRenderer {

	Puzzle puzzle;
	Sprite[][] spriteMap;
	List<Sprite> ballSpriteList;
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
		createSpriteMap();
	}
		
	private void createSpriteMap() {
		TileState[][] map = puzzle.getMap();
		int numTilesHeight = map.length;
		int numTilesWight = map[map.length - 1].length; //le good shit in a jar
		
		float tileSpriteWidth = ( screenWidth / numTilesWight ) - 2 * paddingWidth;
		float tileSpriteHeight = ( screenHeight / numTilesHeight ) - 2 * paddingHeight;
		
		spriteMap = new Sprite[map.length][];
		for(int i = 0; i < map.length; ++i) {
			
			spriteMap[i] = new Sprite[map[i].length];
			for(int j = 0; j < map[i].length; ++j) {
				switch(map[i][j]) {
				case NO_TILE:
					break;
				case TILE:
					spriteMap[i][j] = new Sprite(atlas.findRegion("tileBoxGreen"));
					break;
				case TILE_WITH_BALL:
					spriteMap[i][j] = new Sprite(atlas.findRegion("tileBoxGreen"));
					ballSpriteList.add(new Sprite(atlas.findRegion("itemYellow")));
					break;
				case FINAL_TILE:
					spriteMap[i][j] = new Sprite(atlas.findRegion("tileBoxGreen"));
					spriteMap[i][j].setColor(Color.BLUE);
					break;
				case INIT_TILE:
					spriteMap[i][j] = new Sprite(atlas.findRegion("tileBoxGreen"));
					spriteMap[i][j].setColor(Color.GREEN);
					break;
				default:
						break;
				}
				
				if(spriteMap[i][j] != null) {
					System.out.println(i + " " + j + " " + tileSpriteWidth + " " + tileSpriteHeight);
					spriteMap[i][j].setSize(tileSpriteWidth, tileSpriteHeight);
					spriteMap[i][j].setX(j * tileSpriteWidth + (2 * j + 1) * (screenWidth * padding));
					spriteMap[i][j].setY(screenHeight - ((i + 1) * tileSpriteHeight + (2 * i + 1) * (screenHeight * padding)));
				}
			
			}
		}
		
	}
	
	public void render (float deltaTime) {
		spriteBatch.begin();
		renderTiles();
		spriteBatch.end();
	}
	
	private void renderTiles() {
		for(int i = 0; i < spriteMap.length; ++i) {
			for(int j = 0; j < spriteMap[i].length; ++j) {
				if(spriteMap[i][j] != null) spriteMap[i][j].draw(spriteBatch);
			}
		}
	}
	
}
