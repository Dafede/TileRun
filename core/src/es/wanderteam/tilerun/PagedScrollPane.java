package es.wanderteam.tilerun;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PagedScrollPane extends ScrollPane{

	private boolean wasPanDragFling = false;
	 
	private float pageSpacing;
	 
	private Table content;
	
	public PagedScrollPane(Actor widget) {
		super(widget);
		// TODO Auto-generated constructor stub
	}

	public PagedScrollPane (Skin skin) {
        super(null, skin);
        setup();
    }
	
    public PagedScrollPane (Skin skin, String styleName) {
        super(null, skin, styleName);
        setup();
    }

    public PagedScrollPane (Actor widget, ScrollPaneStyle style) {
        super(null, style);
        setup();
    }
    
    private void setup() {
    	content = new Table();
    	content.defaults().space(50);
    	super.setWidget(content);
    }

    public void addPages(Actor...pages) {
    	for(Actor page : pages) {
    		content.add(page).expandX().fillY();
    	}
    }
    
    public void addPage(Actor page) {
    	content.add(page).expandY().fillY();
    }

	/*@Override
	public void act(float delta) {
		super.act(delta);
		if(wasPanDragFling && !isPanning())
	}*/
    
    
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
