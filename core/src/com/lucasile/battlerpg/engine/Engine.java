package com.lucasile.battlerpg.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lucasile.battlerpg.engine.main.Main;
import com.lucasile.battlerpg.engine.screens.GameScreen;

public class Engine extends Game {

	private GameScreen gameScreen;
	private SpriteBatch worldBatch;
	private SpriteBatch uiBatch;
	private Main main;

	public Engine(Main main) {
		this.main = main;
	}

	@Override
	public void create() {
		worldBatch = new SpriteBatch();
		uiBatch = new SpriteBatch();
		gameScreen = new GameScreen(main, worldBatch, uiBatch);
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
