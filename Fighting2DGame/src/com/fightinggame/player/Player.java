package com.fightinggame.player;

import com.fightinggame.util.Hitbox;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class Player {
    private final AnimationController animation;

    private final int frameWidth = 120;
    private final int frameHeight = 80;

    // Movement
    private double x, y;
    private double speed = 0;
    private final double MAX_SPEED = 300;
    private final double ACCELERATION = 250;
    private final double DECELERATION = 400;
    private boolean facingRight = true;

    // Vertical physics
    private double velocityY = 0;
    private final double GRAVITY = 500;
    private final double JUMP_FORCE = -300;
    private final double GROUND_Y = 400;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private double jumpCooldown = 0;

    // States
    private boolean isRolling = false;
    private boolean isDashing = false;
    private boolean isTurning = false;
    private boolean isCrouching = false;
    private boolean inCrouchTransition = false;

    // Slide State Machine
    private enum SlideState { NONE, STARTING, SLIDING, ENDING }
    private SlideState slideState = SlideState.NONE;
    private double slideTimer = 0;

    // Attack State
    private int attackStep = 0;
    private double attackTimer = 0;
    private double attackChainWindow = 0;
    private boolean isAttacking = false;
    private boolean attackLockout = false;
    private boolean queuedAttack = false;
    private final double ATTACK1_TIME = 0.5, ATTACK2_TIME = 0.6, COMBO_TIME = 0.7;
    private final double CHAIN_WINDOW = 0.5, COMBO_COOLDOWN = 1.5;

    private Hitbox attack1Hitbox = new Hitbox(38, 40, 100);
    private Hitbox attack2Hitbox = new Hitbox(37, 39, 200);
    private Hitbox comboHitbox = new Hitbox(38, 38, 200);
    private Hitbox activeHitbox;
    private boolean hitboxVisible = true;
    private boolean debugTogglePressed = false;

    // Timers
    private double dashCooldown = 0;
    private double rollCooldown = 0;

    // Frame-trigger flags
    private boolean hitboxFired = false;

    public Player(double x, double y, String spriteFolder) {
        this.x = x;
        this.y = y;

        animation = new AnimationController(frameWidth, frameHeight);
        animation.load("idle", spriteFolder + "/_Idle.png", 10);
        animation.load("run", spriteFolder + "/_Run.png", 10);
        animation.load("jump", spriteFolder + "/_Jump.png", 3);
        animation.load("fall", spriteFolder + "/_Fall.png", 3);
        animation.load("dash", spriteFolder + "/_Dash.png", 2);
        animation.load("roll", spriteFolder + "/_Roll.png", 12);
        animation.load("turn", spriteFolder + "/_TurnAround.png", 2);
        animation.load("crouch", spriteFolder + "/_Crouch.png", 1);
        animation.load("crouchWalk", spriteFolder + "/_CrouchWalk.png", 8);
        animation.load("jumpTrans", spriteFolder + "/_JumpFallInbetween.png", 2);
        animation.load("crouchTransition", spriteFolder + "/_CrouchTransition.png", 1);
        animation.load("slide", spriteFolder + "/_Slide.png", 2);
        animation.load("slideStart", spriteFolder + "/_SlideTransitionStart.png", 1);
        animation.load("slideEnd", spriteFolder + "/_SlideTransitionEnd.png", 1);
        animation.load("attack1", spriteFolder + "/_Attack.png", 4);
        animation.load("attack2", spriteFolder + "/_Attack2.png", 6);
        animation.load("comboAttack", spriteFolder + "/_AttackCombo.png", 10);

        if (hitboxVisible) {
            attack1Hitbox.show();
            attack2Hitbox.show();
            comboHitbox.show();
        }

        animation.play("idle");
        animation.setPosition(x, y);
        animation.flip(facingRight);
    }

    public void triggerAttack() {
        if (attackLockout || isRolling || isJumping || isFalling || slideState != SlideState.NONE) return;

        if (!isAttacking) {
            isAttacking = true;
            attackStep = 1;
            attackTimer = ATTACK1_TIME;
            hitboxFired = false;
            animation.playOnce("attack1", ATTACK1_TIME);
            activeHitbox = attack1Hitbox;
        } else {
            queuedAttack = true;
        }
    }

    public void update(double deltaTime) {
    	  if (jumpCooldown > 0) jumpCooldown -= deltaTime;
          if (dashCooldown > 0) dashCooldown -= deltaTime;
          if (rollCooldown > 0) rollCooldown -= deltaTime;
          if (attackLockout) {
              attackTimer -= deltaTime;
              if (attackTimer <= 0) {
                  attackLockout = false;
                  attackStep = 0;
                  queuedAttack = false;
                  isAttacking = false;
                  hitboxFired = false;
              }
          }

        // SLIDE STATE MACHINE
        if (slideState != SlideState.NONE) {
            double slideVelocity = 200 * deltaTime;
            switch (slideState) {
                case STARTING:
                    x += (facingRight ? 1 : -1) * slideVelocity;
                    if (animation.isAnimationDone()) {
                        slideTimer = 0.8;
                        slideState = SlideState.SLIDING;
                        animation.play("slide");
                    }
                    break;
                case SLIDING:
                    slideTimer -= deltaTime;
                    x += (facingRight ? 1 : -1) * slideVelocity;
                    if (slideTimer <= 0) {
                        slideState = SlideState.ENDING;
                        animation.playOnce("slideEnd", 0.2);
                    }
                    break;
                case ENDING:
                    x += (facingRight ? 1 : -1) * slideVelocity;
                    if (animation.isAnimationDone()) {
                        slideState = SlideState.NONE;
                        speed = (facingRight ? 50 : -50);
                        animation.play("idle");
                    }
                    break;
            }
            animation.setPosition(x, y);
            animation.update(deltaTime, 10);
            return;
        }


        if (isRolling) {
            x += (facingRight ? 1 : -1) * 250 * deltaTime;
            animation.setPosition(x, y);
            animation.update(deltaTime, 10);
            if (animation.isAnimationDone()) {
                isRolling = false;
                animation.play("idle");
            }
            return;
        }

        if (isDashing) {
            x += (facingRight ? 1 : -1) * 500 * deltaTime;
            animation.setPosition(x, y);
            animation.update(deltaTime, 12);
            if (animation.isAnimationDone()) {
                isDashing = false;
                animation.play("idle");
            }
            return;
        }

        if (isTurning) {
            double slideDecel = DECELERATION * 1.5;
            speed += speed > 0 ? -slideDecel * deltaTime : slideDecel * deltaTime;
            if (Math.abs(speed) < 1) speed = 0;
            x += speed * deltaTime;
            animation.setPosition(x, y);
            animation.update(deltaTime, 8);
            if (animation.isAnimationDone()) {
                isTurning = false;
                animation.play("idle");
            }
            return;
        }

        if (isJumping || isFalling) {
            velocityY += GRAVITY * deltaTime;
            y += velocityY * deltaTime;
            x += speed * deltaTime;

            if (velocityY < 0) animation.play("jump");
            else animation.play("fall");

            if (y >= GROUND_Y) {
                y = GROUND_Y;
                velocityY = 0;
                isJumping = false;
                isFalling = false;
                jumpCooldown = 0.3;
                animation.play("idle");
            }

            animation.setPosition(x, y);
            animation.update(deltaTime, 6);
            return;
        }
        
        if (isAttacking) {
            attackTimer -= deltaTime;
            attackChainWindow -= deltaTime;
            animation.setPosition(x, y);
            animation.update(deltaTime, 12);

            int frame = animation.getCurrentFrameIndex();

            if (!hitboxFired) {
                double hitboxX = x;
                double hitboxY = y;

                if (attackStep == 1 && frame == 2) {
                    hitboxX += facingRight ? 75 : 4;
                    hitboxY += 40;
                    activeHitbox.trigger(hitboxX, hitboxY);
                    hitboxFired = true;
                } else if (attackStep == 2 && frame == 3) {
                    hitboxX += facingRight ? 75 : 4;
                    hitboxY += 40;
                    activeHitbox.trigger(hitboxX, hitboxY);
                    hitboxFired = true;
                } else if (attackStep == 3 && (frame == 2 || frame == 7)) {
                    hitboxX += facingRight ? 75 : 4;
                    hitboxY += 40;
                    activeHitbox.trigger(hitboxX, hitboxY);
                    hitboxFired = true;
                } else {
                    if (activeHitbox != null) activeHitbox.disable();
                }
            }


            if (attackTimer <= 0) {
                if (attackStep < 3 && queuedAttack) {
                    attackStep++;
                    double nextTime = (attackStep == 2) ? ATTACK2_TIME : COMBO_TIME;
                    String anim = (attackStep == 2) ? "attack2" : "comboAttack";
                    activeHitbox = (attackStep == 2) ? attack2Hitbox : comboHitbox;

                    attackTimer = nextTime;
                    animation.playOnce(anim, nextTime);
                    queuedAttack = false;
                    hitboxFired = false;
                } else if (attackStep < 3) {
                    attackChainWindow = CHAIN_WINDOW;
                    isAttacking = false;
                    queuedAttack = false;
                } else {
                    isAttacking = false;
                    attackStep = 0;
                    attackLockout = true;
                    attackChainWindow = 0;
                    attackTimer = COMBO_COOLDOWN;
                    if (activeHitbox != null) activeHitbox.disable();
                    animation.play("idle");
                    queuedAttack = false;
                    hitboxFired = false;
                }
            }
            return;
        }
        if (inCrouchTransition) {
            animation.update(deltaTime, 4);
            animation.setPosition(x, y);
            if (animation.isAnimationDone()) {
                inCrouchTransition = false;
                animation.play(isCrouching ? "crouch" : "idle");
            }
            return;
        }

        if (isCrouching) {
            if (Math.abs(speed) > 10) {
                animation.play("crouchWalk");
                animation.update(deltaTime, 8);
            } else {
                animation.play("crouch");
                animation.update(deltaTime, 2);
            }
            x += speed * deltaTime;
            animation.setPosition(x, y);
            return;
        }

        double absSpeed = Math.abs(speed);
        if (absSpeed > 20) animation.play("run");
        else animation.play("idle");

        x += speed * deltaTime;
        animation.setPosition(x, y);
        animation.update(deltaTime, animationSpeed(absSpeed));
    }

    public void moveLeft(double deltaTime) {
        if (isTurning || isRolling || isDashing || slideState != SlideState.NONE ) return;

        if (facingRight && speed > 100 && !isJumping && !isFalling && !isCrouching) {
            isTurning = true;
            animation.playOnce("turn", 0.6);
            return;
        }

        if (isCrouching) {
            speed -= ACCELERATION * 0.3 * deltaTime;
            if (speed < -MAX_SPEED * 0.5) speed = -MAX_SPEED * 0.5;
        } else {
            speed -= ACCELERATION * deltaTime;
            if (speed < -MAX_SPEED) speed = -MAX_SPEED;
        }

        facingRight = false;
        animation.flip(facingRight);
    }

    public void moveRight(double deltaTime) {
        if (isTurning || isRolling || isDashing || slideState != SlideState.NONE ) return;

        if (!facingRight && speed < -100 && !isJumping && !isFalling && !isCrouching) {
            isTurning = true;
            animation.playOnce("turn", 0.6);
            return;
        }

        if (isCrouching) {
            speed += ACCELERATION * 0.3 * deltaTime;
            if (speed > MAX_SPEED * 0.5) speed = MAX_SPEED * 0.5;
        } else {
            speed += ACCELERATION * deltaTime;
            if (speed > MAX_SPEED) speed = MAX_SPEED;
        }

        facingRight = true;
        animation.flip(facingRight);
    }

    public void decelerate(double deltaTime) {
        if (speed > 0) {
            speed -= DECELERATION * deltaTime;
            if (speed < 0) speed = 0;
        } else if (speed < 0) {
            speed += DECELERATION * deltaTime;
            if (speed > 0) speed = 0;
        }
    }

    public void jump() {
    	if (!isJumping && !isFalling && jumpCooldown <= 0 && !isCrouching && slideState == SlideState.NONE)
 {
            velocityY = JUMP_FORCE;
            isJumping = true;
            animation.playOnce("jumpTrans", 0.1);
        }
    }

    public void crouch(boolean active) {
        if (isJumping || isFalling || inCrouchTransition || slideState != SlideState.NONE ) return;

        if (active != isCrouching) {
            isCrouching = active;
            inCrouchTransition = true;
            animation.playOnce("crouchTransition", 0.2);
        }
    }

    public void triggerDash(String direction) {
    	if (!isDashing && !isFalling && jumpCooldown <= 0 && !isCrouching && slideState == SlideState.NONE)
 {
            isDashing = true;
            facingRight = direction.equals("right");
            animation.flip(facingRight);
            dashCooldown = 1.0;
            animation.playOnce("dash", 0.16);
        }
    }

    public void triggerRoll() {
    	if (!isRolling && !isJumping && !isFalling && !isDashing && !isCrouching && slideState == SlideState.NONE && rollCooldown <= 0)
            isRolling = true;
            rollCooldown = 1.0;
            animation.playOnce("roll", 0.58);
        }
    

    public void triggerSlide() {
        if (slideState == SlideState.NONE && !isRolling && !isJumping && !isFalling && !isDashing && !isCrouching && Math.abs(speed) > MAX_SPEED * 0.8) {
            slideState = SlideState.STARTING;
            animation.playOnce("slideStart", 0.2);
        }
    }


    public void render(Pane root) {
        if (!root.getChildren().contains(animation.getView())) {
            root.getChildren().add(animation.getView());
        }
        if (hitboxVisible) {
            if (!root.getChildren().contains(attack1Hitbox.getNode()))
                root.getChildren().add(attack1Hitbox.getNode());
            if (!root.getChildren().contains(attack2Hitbox.getNode()))
                root.getChildren().add(attack2Hitbox.getNode());
            if (!root.getChildren().contains(comboHitbox.getNode()))
                root.getChildren().add(comboHitbox.getNode());
        }
    }

    private double animationSpeed(double absSpeed) {
        return (absSpeed > 20) ? Math.min(15, Math.max(5, absSpeed / 10)) : 6;
    }
    
    public void handleInput(com.fightinggame.input.InputHandler input) {
        if (input.isJustPressed("Q")) triggerRoll();
        if (input.isJustPressed("SHIFT")) triggerSlide();
        if (input.isJustPressed("R")) triggerAttack();

        if (input.isKeyPressed("F1")) {
            if (!debugTogglePressed) {
                hitboxVisible = !hitboxVisible;
                setHitboxVisibility(hitboxVisible);
                debugTogglePressed = true;
            }
        } else {
            debugTogglePressed = false;
        }
    }
    private void setHitboxVisibility(boolean visible) {
        if (visible) {
            attack1Hitbox.show();
            attack2Hitbox.show();
            comboHitbox.show();
        } else {
            attack1Hitbox.hide();
            attack2Hitbox.hide();
            comboHitbox.hide();
        }
    }

    public Hitbox getHitbox() {
        return activeHitbox;
    }
}
	