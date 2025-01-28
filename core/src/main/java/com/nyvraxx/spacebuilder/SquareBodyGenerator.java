package com.nyvraxx.spacebuilder;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;

public class SquareBodyGenerator {
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    public SquareBodyGenerator(float r, Vector2 position) {
        // Create the body definition
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;  // Set the body to be dynamic
        bodyDef.position.set(position);  // Set the position of the body

        // Create a square shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(r, r);  // Set the shape to be a square with side length 2 * r

        // Create the fixture definition and assign the shape to it
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;  // You can set the density to a value, e.g., 1.0f
        fixtureDef.friction = 0.3f; // Set friction
        fixtureDef.restitution = 0.5f;  // Set restitution (bounciness)

    }

    public Body createBody(World world) {
        // Create the body in the world using the body definition
        Body body = world.createBody(bodyDef);

        // Attach the fixture to the body
        body.createFixture(fixtureDef);

        return body;
    }
}
