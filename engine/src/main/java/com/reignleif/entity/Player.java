package com.reignleif.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.reignleif.utils.Refs;

public class Player extends Entity {

	private final int WALK_FRAMES_AMT = 2; // 3 counting from 0
	private final int ATK_FRAMES_AMT = 3; // 4 counting from 0

	private final float WALK_FRAMES_FPS = 5; // From TexturePacker anim viewer
	private final float ATK_FRAMES_FPS = 10; // From TexturePacker anim viewer

	private TextureAtlas spriteAtlas;

	private Animation<TextureRegion> walkDownAnim;
	private Animation<TextureRegion> walkUpAnim;
	private Animation<TextureRegion> walkRightAnim;
	private Animation<TextureRegion> walkLeftAnim;
	private Animation<TextureRegion> attackLeftAnim;
	private Animation<TextureRegion> attackUpAnim;
	private Animation<TextureRegion> attackRightAnim;
	private Animation<TextureRegion> attackDownAnim;

	private TextureRegion idleLeft, idleRight, idleUp, idleDown;

	private Array<TextureRegion> frames;

	private float stateTime;
	private EntityState currentState = EntityState.IDLE, previousState = EntityState.IDLE;
	private EntityDirection currentDirection = EntityDirection.DOWN;

	private Body body;

	public Player(World world) {
		super(world);

		stateTime = 0;
		spriteAtlas = new TextureAtlas(Gdx.files.internal("playerSprite.txt"));
		frames = new Array<TextureRegion>();

		// IDLE Sprites
		idleLeft = new TextureRegion(spriteAtlas.findRegion("idle_left"));
		idleRight = new TextureRegion(spriteAtlas.findRegion("idle_right"));
		idleUp = new TextureRegion(spriteAtlas.findRegion("idle_up"));
		idleDown = new TextureRegion(spriteAtlas.findRegion("idle_down"));

		for (int i = 0; i < WALK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("walk_down_" + i));
		walkDownAnim = new Animation<TextureRegion>(WALK_FRAMES_FPS, frames);
		frames.clear();

		for (int i = 0; i < WALK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("walk_up_" + i));
		walkUpAnim = new Animation<TextureRegion>(WALK_FRAMES_FPS, frames);
		frames.clear();

		for (int i = 0; i < WALK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("walk_left_" + i));
		walkLeftAnim = new Animation<TextureRegion>(WALK_FRAMES_FPS, frames);
		frames.clear();

		for (int i = 0; i < WALK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("walk_right_" + i));
		walkRightAnim = new Animation<TextureRegion>(WALK_FRAMES_FPS, frames);
		System.out.println(walkRightAnim.toString());
		frames.clear();

		for (int i = 0; i < ATK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("attack_left_" + i));
		attackLeftAnim = new Animation<TextureRegion>(ATK_FRAMES_FPS, frames);
		frames.clear();

		for (int i = 0; i < ATK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("attack_right_" + i));
		attackRightAnim = new Animation<TextureRegion>(ATK_FRAMES_FPS, frames);
		frames.clear();

		for (int i = 0; i < ATK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("attack_up_" + i));
		attackUpAnim = new Animation<TextureRegion>(ATK_FRAMES_FPS, frames);
		frames.clear();

		for (int i = 0; i < ATK_FRAMES_AMT; i++)
			frames.add(spriteAtlas.findRegion("attack_down_" + i));
		attackDownAnim = new Animation<TextureRegion>(ATK_FRAMES_FPS, frames);
		frames.clear();

		setCurrentPosition(100, 100);
		sprite.setRegion(idleDown);
		createPlayerBody();
	}

	private void createPlayerBody() {
		BodyDef bDef = new BodyDef();
		bDef.position.set(getSprite().getX() / 2, getSprite().getY() / 2);
		bDef.type = BodyType.DynamicBody;
		body = world.createBody(bDef);

		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getRegionWidth() / 2, sprite.getRegionHeight() / 2);
		fDef.filter.categoryBits = Refs.PLAYER_BIT;
		fDef.filter.maskBits = Refs.GROUND_BIT | Refs.OBJ_BIT | Refs.ENTITY_BIT | Refs.ENEMY_BIT | Refs.TERRAIN_BIT;
		fDef.shape = shape;
		body.createFixture(fDef).setUserData(this);
	}

	@Override
	public void moveEntity(float x, float y) {

		body.applyLinearImpulse(new Vector2(x, y), body.getWorldCenter(), true);
	}

	public void update(float delta) {
		getSprite().setX(body.getPosition().x / 2);
		getSprite().setY(body.getPosition().y / 2);
		currentPosition.set(body.getPosition());

		if (Gdx.input.isKeyPressed(Keys.W)) {
			moveEntity(0, (200 * delta));
			setDirection(EntityDirection.UP);
		}
		else if (Gdx.input.isKeyPressed(Keys.S)) {
			moveEntity(0, (-200 * delta));
			setDirection(EntityDirection.DOWN);
		}
		else if (Gdx.input.isKeyPressed(Keys.D)) {
			moveEntity((200 * delta), 0);
			setDirection(EntityDirection.RIGHT);
		}
		else if (Gdx.input.isKeyPressed(Keys.A)) {
			moveEntity((-200 * delta), 0);
			setDirection(EntityDirection.LEFT);
		} else if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			// attack anim
		}
		else {
			body.setLinearVelocity(0, 0);
			setDirection(EntityDirection.DOWN);
		}

	}

	public void setDirection(EntityDirection direction) {
		currentDirection = direction;
	}
	
	public TextureRegion getFrame(float delta) {
		
		currentState = getState();
		currentDirection = getDirection();
		
		System.out.println(currentState.toString());		
		System.out.println(currentDirection.toString());

		TextureRegion region;
		switch (currentState) {
		case WALKING:
			if (currentDirection.getId().equals("left")) region = walkLeftAnim.getKeyFrame(delta);
			else if (currentDirection.getId().equals("right")) region = walkRightAnim.getKeyFrame(delta);
			else if (currentDirection.getId().equals("up")) region = walkUpAnim.getKeyFrame(delta);
			else if (currentDirection.getId().equals("down")) region = walkDownAnim.getKeyFrame(delta);
			else region = walkDownAnim.getKeyFrame(delta);
			break;
		case IDLE:
			if (currentDirection.getId().equals("left")) region = idleLeft;
			else if (currentDirection.getId().equals("right")) region = idleRight;
			else if (currentDirection.getId().equals("up")) region = idleUp;
			else if (currentDirection.getId().equals("down")) region = idleDown;
			else region = idleDown;
			break;
		case ATTACKING:
			if (currentDirection.getId().equals("left")) region = attackLeftAnim.getKeyFrame(delta);
			else if (currentDirection.getId().equals("right")) region = attackRightAnim.getKeyFrame(delta);
			else if (currentDirection.getId().equals("up")) region = attackUpAnim.getKeyFrame(delta);
			else if (currentDirection.getId().equals("down")) region = attackDownAnim.getKeyFrame(delta);
			else region = walkDownAnim.getKeyFrame(delta);
			break;
		default:
			region = idleDown;
			break;
		}

		stateTime = currentState == previousState ? stateTime + delta : 0;
		previousState = currentState;

		return region;
	}

	public EntityState getState() {
		if (body.getLinearVelocity().x != 0 || body.getLinearVelocity().y != 0)
			return EntityState.WALKING;
		else if (Gdx.input.isButtonPressed(Buttons.LEFT))
			return EntityState.ATTACKING;
		else return EntityState.IDLE;
	}

	public EntityDirection getDirection() {
		return currentDirection;
	}

	public Body getPlayerBody() {
		return body;
	}

	@Override
	public boolean onCollide(Entity entity) {
		return false;
	}

}
