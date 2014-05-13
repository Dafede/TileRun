package es.wanderteam.tilerun;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
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
	
	FPSLogger fps = new FPSLogger();
	
	/** Visual game data **/
	TextureAtlas atlas;
	Skin gameVisualSkin;
	
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
		
		if(puzzle.isCompleted()) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
		}
		
		fps.log();
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
		
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		System.out.println(screenX + " " + screenY);
		int realScreenX = screenX;
		int realScreenY = Gdx.graphics.getHeight() - screenY;
		puzzle.touchScreen(realScreenX, realScreenY, pointer);

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
