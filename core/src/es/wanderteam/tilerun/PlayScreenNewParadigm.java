package es.wanderteam.tilerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import es.wanderteam.game.Puzzle;
import es.wanderteam.game.PuzzleRenderer;

public class PlayScreenNewParadigm implements Screen, InputProcessor {

	SpriteBatch batch;
	
	FPSLogger fps = new FPSLogger();
	
	/** Visual game data **/
	TextureAtlas atlas;
	Skin gameVisualSkin;
		
	Puzzle puzzle;
	PuzzleRenderer renderer;
	
	
	public PlayScreenNewParadigm(String level) {
		puzzle = new Puzzle(level);
		renderer = new PuzzleRenderer(puzzle);
	}
	
	@Override
	public void show() {
		//CONSTRUCTOR
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		atlas = new TextureAtlas(Gdx.files.internal("Game.pack"));
		gameVisualSkin = new Skin(atlas);
		
		
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
		if(Keys.DEL == keycode) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new SelectionScreen());
		}
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
