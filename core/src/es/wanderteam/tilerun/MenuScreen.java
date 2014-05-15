package es.wanderteam.tilerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen{
	Stage stage;
	Skin uiSkin;
	
	Image background;
	ImageButton ibPlay;
	ImageButtonStyle ibsPlay;
	ImageButton ibOptions;
	ImageButtonStyle ibsOptions;
	ImageButton ibScore;
	ImageButtonStyle ibsScore;
	
	@Override
	public void show() {
		
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("UI.pack"));
		uiSkin = new Skin(atlas);
		
		//Create and add background
		background = new Image(uiSkin.getDrawable("mainScreen"));
		background.setWidth(Gdx.graphics.getWidth());
		background.setHeight(Gdx.graphics.getHeight());
		stage.addActor(background);
		
		
		//Create the style of the play button, create the playbutton whit that style,
		//positioning the play button and adding it to the stage
		ibsPlay = new ImageButtonStyle();
		ibsPlay.imageUp = uiSkin.getDrawable("buttonPlay");
		

		ibPlay = new ImageButton(ibsPlay);
		ibPlay.setName("UI_PLAY_BUTTON");
		ibPlay.setX((Gdx.graphics.getWidth() - ibPlay.getWidth()) / 2);
		ibPlay.setY((Gdx.graphics.getHeight() * 1.5f  - ibPlay.getHeight()) / 2);
		ibPlay.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
					@Override
					public void run() {
						((Game)Gdx.app.getApplicationListener()).setScreen(new SelectionScreen());
					}
				})));
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		
		stage.addActor(ibPlay);
		
		//Create the style of the play button, create the options button whit that style,
		//positioning the play button and adding it to the stage
		ibsOptions = new ImageButtonStyle();
		ibsOptions.imageUp = uiSkin.getDrawable("buttonOptions");
		
		ibOptions = new ImageButton(ibsOptions);
		ibOptions.setX((Gdx.graphics.getWidth() - ibOptions.getWidth()) / 2);
		ibOptions.setY((Gdx.graphics.getHeight() * 0.5f - ibOptions.getHeight()) / 2);
		ibOptions.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
					
					@Override
					public void run() {
						((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
					}
				})));
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		
		stage.addActor(ibOptions);
		
		//Create the style of the play button, create the score button whit that style,
		//positioning the play button and adding it to the stage
		ibsScore = new ImageButtonStyle();
		ibsScore.imageUp = uiSkin.getDrawable("buttonScore");
		
		ibScore = new ImageButton(ibsScore);
		ibScore.setX((Gdx.graphics.getWidth() - ibScore.getWidth()) / 2);
		ibScore.setY((Gdx.graphics.getHeight() - ibScore.getHeight()) / 2);
		ibScore.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
					stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
					
					@Override
					public void run() {
						((Game)Gdx.app.getApplicationListener()).setScreen(new ScoreScreen());
					}
				})));
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		stage.addActor(ibScore);
		
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(47f / 255f, 67f / 255f, 244f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//when resize the window, resize the viewport too for update the colision boxes etc
		stage.getViewport().update(width, height, true);
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
		
	}

}
