package main.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Game;

public class LoadState extends BasicGameState {

	public static final int ID = 0;

	private boolean leftSide = false;

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if (leftSide)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		leftSide = (input.getMouseX() < Game.WIDTH / 2);
		
		if (input.isMousePressed(0))
			if (leftSide)
				game.enterState(ServerState.ID);
			else
				game.enterState(ClientState.ID);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
