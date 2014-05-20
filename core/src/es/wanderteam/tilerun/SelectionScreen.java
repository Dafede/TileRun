package es.wanderteam.tilerun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SelectionScreen implements Screen, InputProcessor{

	Stage stage;
	Table container;
	Skin uiSkin;
	
	Image background;
	
	List<String> listLevelNames = new ArrayList<String>(Arrays.asList(
			"level1.png", "level2.png", "level3.png",
			"unimplemented","unimplemented","unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented",  
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented", 
			"unimplemented", "unimplemented", "unimplemented", "unimplemented", "unimplemented"
			));
	List<TextButton> listButtons = new ArrayList<TextButton>();
	
	TextButtonStyle tbsSelection;
	
	int buttonPerRow = 3;
	int buttonPerColumn = listLevelNames.size() / buttonPerRow;
	float padding = 0.01f;
	int screenHeight = Gdx.graphics.getHeight();
	int screenWidth = Gdx.graphics.getWidth();
	float paddingWidth = screenWidth * padding;
	float paddingHeight = screenHeight * padding;
	float buttonWidth = ( screenWidth / buttonPerRow ) - 2 * paddingWidth; 
	float buttonHeight = ( screenHeight / buttonPerColumn ) - 2 * paddingHeight;
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		
		//lets do magik
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(multiplexer);
		
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("UI.pack"));
		uiSkin = new Skin(atlas);
		/** Set the background **/
		background = new Image(uiSkin.getDrawable("mainScreen"));
		background.setWidth(Gdx.graphics.getWidth());
		background.setHeight(Gdx.graphics.getHeight());
		stage.addActor(background);
		
		tbsSelection = new TextButtonStyle();
		tbsSelection.up = uiSkin.getDrawable("buttonPlay");
		tbsSelection.font = new BitmapFont();
		
		
		container = new Table();
		stage.addActor(container);
		container.setFillParent(true);
		
		Table nuevoTable = new Table();
		
		ScrollPane scroller = new ScrollPane(nuevoTable);
		
<<<<<<< HEAD
=======
		
>>>>>>> 425a4ce56f74619a44f08aebbd674678e019287f
		nuevoTable.pad(0).defaults().expandX().fillX();

		for(int i = 0; i < listLevelNames.size(); ++i) {
			nuevoTable.row().pad(5);
			TextButton button = new TextButton(listLevelNames.get(i), tbsSelection);
			button.setName(listLevelNames.get(i));
			button.addListener(new ClickListener(){

				@Override
				public void clicked(InputEvent event, float x, float y) {
					final String level = event.getListenerActor().getName();
					stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
						@Override
						public void run() {
							((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreenNewParadigm(level));
						}
					})));
					super.clicked(event, x, y);
				}
				
			});
			nuevoTable.add(button).expand().fillX();
		}

		container.add(scroller).fill().colspan(4);
		container.row().space(10).padBottom(10);
	}
	
	@Override
	public void render(float delta) {
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Table.drawDebug(stage);
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
		uiSkin.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		System.out.print(" " + keycode + " " + Keys.BACK + " " + Keys.BACKSLASH);
		
		if(Keys.DEL == keycode) {
			
			stage.addAction(Actions.sequence(Actions.fadeOut(0.1f),Actions.run(new Runnable() {
				@Override
				public void run() {
					((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
				}
			})));
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
		// TODO Auto-generated method stub
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
