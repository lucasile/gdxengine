package com.lucasile.battlerpg.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lucasile.battlerpg.engine.screens.GameScreen;

public class Engine extends Game {

	private GameScreen gameScreen;
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}
}
