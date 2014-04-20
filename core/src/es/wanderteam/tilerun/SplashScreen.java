package es.wanderteam.tilerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class SplashScreen implements Screen {
	Stage stage;
	
	Image logo;
	
	@Override
	public void show() {
		stage = new Stage();
		
		//Create, positionate and add to the stage the logo of the company
		Texture logoTexture = new Texture(Gdx.files.internal("logo.png"));
		logo = new Image(logoTexture);
		logo.setX((Gdx.graphics.getWidth() - logoTexture.getWidth()) / 2);
		logo.setY((Gdx.graphics.getHeight() - logoTexture.getHeight()) / 2);
		stage.addActor(logo);
		
		//When the logo has been showed enough time, go to the menu screen
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
					
					@Override
					public void run() {
						((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
					}
				})));
			}
		}, 0.1f);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(91f / 255f, 200f / 255f, 165f / 255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
		stage.dispose();
	}

}
