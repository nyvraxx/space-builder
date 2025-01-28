package com.nyvraxx.spacebuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen implements Screen {
	private Stage stage;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private static final float WORLD_WIDTH = 100;
	private static final float WORLD_HEIGHT = 100;

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.8f), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();

		stage = new Stage(new FitViewport(1.6f, 1, camera));
		camera.zoom = 100;
		Gdx.input.setInputProcessor(stage);

		{
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(0, 0);

			PolygonShape shape = new PolygonShape();
			shape.setAsBox(100f, 1f); // Create a 1x1 square

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.friction = 0.1f;

			Body body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
			body.setTransform(new Vector2(0, -50), 0);

			shape.dispose();
		}
		{
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(0, 0);

			PolygonShape shape = new PolygonShape();
			shape.setAsBox(1f, 100f); // Create a 1x1 square

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.friction = 0.1f;

			Body body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
			body.setTransform(new Vector2(-80, -5), 0);

			shape.dispose();
		}
		{
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(0, 0);

			PolygonShape shape = new PolygonShape();
			shape.setAsBox(1f, 100f); // Create a 1x1 square

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.friction = 0.1f;

			Body body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
			body.setTransform(new Vector2(80, -5), 0);

			shape.dispose();
		}
		for (int i = 0; i < 3000; i++) {
//			System.out.println(i);
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.DynamicBody;
			bodyDef.position.set(0, 0);

			PolygonShape shape = new PolygonShape();
			shape.set(new Vector2[] { new Vector2(1f, 1f), new Vector2(-1f, 1f), new Vector2(-1f, -1f),
					new Vector2(1f, -1f), }); // Create a 1x1 square

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.density = 0.01f;
			fixtureDef.friction = 0.1f;

			Body body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
			body.setLinearVelocity(Random.nextFloat(3) - 1.5f, Random.nextFloat(30) - 1.5f);

			body.setTransform(new Vector2(Random.nextFloat(30), Random.nextFloat(300)),
					Random.nextFloat((float) Math.PI));
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(delta, 8, 4);
		debugRenderer.render(world, stage.getCamera().combined);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void pause() {
		// Invoked when your application is paused.
	}

	@Override
	public void resume() {
		// Invoked when your application is resumed after pause.
	}

	@Override
	public void hide() {
		// This method is called when another screen replaces this one.
	}

	@Override
	public void dispose() {
		stage.dispose();
		world.dispose();
	}
}
