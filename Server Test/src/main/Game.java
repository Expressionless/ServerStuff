package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.game.states.*;

public class Game extends StateBasedGame {

	private AppGameContainer appgc;
	public static final int WIDTH = 400, HEIGHT = 400;
	
	public Game() {
		super("Server Testing");
		
		System.out.println("Initializing Display");
		try {
			appgc = new AppGameContainer(this);
			appgc.setTargetFrameRate(60);
			appgc.setShowFPS(false);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger("Main").log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		addState(new LoadState());
		addState(new ClientState());
		addState(new ServerState());
	}

}
