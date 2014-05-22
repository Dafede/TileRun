package es.wanderteam.tilerun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import es.wanderteam.game.AnimatedImage;

public class MenuScreen implements Screen{
	
	//static Music technoMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/techno.mp3"));
	
	Stage stage;
	Skin uiSkin;
	
	Table buttonContainer;
	AnimatedImage background;
	ImageButton ibPlay;
	ImageButtonStyle ibsPlay;
	ImageButton ibOptions;
	ImageButtonStyle ibsOptions;
	ImageButton ibScore;
	ImageButtonStyle ibsScore;
	
	@Override
	public void show() {
		//technoMusic.play();

		//Gdx.input.setCatchBackKey(true);
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("UI.pack"));
		uiSkin = new Skin(atlas);
		
		//Create and add background
		
		background = new AnimatedImage(new Animation(1/15f, new TextureAtlas(Gdx.files.internal("backgroundAnimation.atlas")).getRegions()));
		background.setWidth(Gdx.graphics.getWidth());
		background.setHeight(Gdx.graphics.getHeight());
		stage.addActor(background);
		
		
		buttonContainer = new Table();
		buttonContainer.debug();
		buttonContainer.setFillParent(true);
		buttonContainer.pad(0).defaults().expandX().fillX();
		stage.addActor(buttonContainer);
		
		//Create the style of the play button, create the playbutton whit that style,
		//positioning the play button and adding it to the stage
		buttonContainer.row().pad(10);
		ibsPlay = new ImageButtonStyle();
		ibsPlay.imageUp = uiSkin.getDrawable("buttonPlay");
		
		ibPlay = new ImageButton(ibsPlay);
		ibPlay.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ibPlay.setWidth(ibPlay.getWidth() + 10);
				ibPlay.setHeight(ibPlay.getHeight() + 10);
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				ibPlay.setWidth(ibPlay.getWidth() - 10);
				ibPlay.setHeight(ibPlay.getHeight() - 10);
				stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
					@Override
					public void run() {
						((Game)Gdx.app.getApplicationListener()).setScreen(new SelectionScreen());
					}
				})));
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		
		buttonContainer.add(ibPlay).expandX().fillX();
		
		
		//Create the style of the play button, create the score button whit that style,
		//positioning the play button and adding it to the stage
		buttonContainer.row().pad(10);
		ibsScore = new ImageButtonStyle();
		ibsScore.imageUp = uiSkin.getDrawable("buttonScore");
		
		ibScore = new ImageButton(ibsScore);
		ibScore.addListener(new ClickListener(){

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
						((Game)Gdx.app.getApplicationListener()).setScreen(new ScoreScreen());
					}
				})));
				
				super.touchUp(event, x, y, pointer, button);
			}
			
		});
		buttonContainer.add(ibScore).expandX().fillX();
		
		
		//Create the style of the play button, create the options button whit that style,
		//positioning the play button and adding it to the stage
		buttonContainer.row().pad(10);
		ibsOptions = new ImageButtonStyle();
		ibsOptions.imageUp = uiSkin.getDrawable("buttonOptions");
		
		ibOptions = new ImageButton(ibsOptions);
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
		
		buttonContainer.add(ibOptions).expandX().fillX();
		buttonContainer.row().pad(10);
		
		//atlas.dispose();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(47f / 255f, 67f / 255f, 244f / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        //Table.drawDebug(stage);
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
		stage.dispose();
		uiSkin.dispose();
	}

}
