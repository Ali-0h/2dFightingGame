# 2dFightingGame

A 2player game, Sprites has been provided within the folder [res] it contain most of the resources of the game, example. [Player1] and [Player2]. This will be a game similar to "Brawhalla" a popular Esport fighting game. But this time it will be a simple one. This game is being created at "Eclipse" with 'JDK'. 

/ / Game System :
1.The game is a 2 player game 
2. theres a variety of map to choose from.[3 maps]
3. The game will be a pvp game similar to brawlhalla and smashlegends.
4.The First player to win 3 rounds WINS.
5.The round has the maximum of 5 rounds 
6.If both reach 3 Wins at the same time, A  draw 
7.If boith players has same score and no one wins at the last round and no damage inflicted = draw
8.If no one died at the last round, the player with tthe most health Wins 
9.If no one reached 3 wins the player that has the most roundwins Wins. 
10.Each round has 1.5 munites to play with.
11.The game can run either in Fullscreen or it will adjust itself in the resolution of the user of the user.

/ / Game Controls:
1. Player 1 
-->[W,A,S,D] - Movement
-->[R] - Atatck
-->[Shift] - Slide
-->[Q] - Roll
2. Player 2 
-->[Arrow Keys] - movement
--> [L] - Attack
--> [J] - Slides
--> [U] - Rolls
3. Misc
--> F1 - Toggle HItboxes

/ / Rules when making this game:
1.	The game should be organize, separate classes and packages for the features of the game. Ex. [MovementLogic/Player1] [Engine/rendering].
2.	The game structure should be a level of a professional.
3.	The code should be easily manupilated for feature updates.]
4. 	Add notes in how the game will work
5.	The features of the game should be implemented, if not be removed as feature, and else if a new bug shall be a feature.
11. 	The speed of the frame in each animation should be easily mnodified.

/ / Feature of the game 
1.MovementLogic | | Player Logic
*Attack 1 and 2:
-->When attack once, do attack1
-->When attack twice do attack1 + Attack2
-->After doing an Attack it would w8 for 0.5 seconds for a next attack, if attacks do the next sequence. if not goes back in being idle.
-->While w8ting for the next input of attack the last frame of the animation of the previous attack will pause until the w8ting times done. 
-->AttackHitbox of Attack1 will be triggered at the 2nd frame of the animation.
-->AttackHitbox of Attack2 will be triggered at the 3rd frame of the animation.
-->Attack dmg of Attack1 is 7.0 dmg per hit.
-->Attack dmg of Attack2 is 9.0 dmg per hit.

*Combo Attack
-->if attack again after attack2 do Combo Attack
-->Combo Attack has faster frames, and deals two dmg
-->Attack dmg of Combo Attack is 8.0 & 9.0 dmg
-->AttackHitbox of ComboAttack will be triggered at the 2nd and 7th frame of the animation
-->After doing a ComboAttack goes back at idle state and disables the attack for 1.5 seconds 

*Attacking
-->While w8ting for next input it pauses the last frame of the previous attack,if  did not attack goes back to idle state and disables attack for 1.5seconds.
-->When Attacking movement becomes slow tremendously(to the point it moves really really slows)
-->When Attacking it can be interrupted by rolling and jumping.
--> The Sequence of attack is Attack1->Attack2->Combo Attack.
-->When spamming the attack button it queue the attacks so animation should be skipped
-->If theres no input after w8ting goes back at idle state. and disables attack for 1.5 seconds.
-->In All of the attack there's a 10% Crit attack that increase the damage by 30% when hit with crit attack.

*Run
-->Running when presses the movement keys
-->When running it start in slow manner then it starts to speed up in the normal speed. 
-->When turning to the opposite while being in top speed, do _turnaround by slowing the speed then slides just slightly then start charging up the run.
-->the speed of animation correspond the speed of the character.
-->When running can be interrupted when hit and stops the player and give a slight of knock back just a little.
-->When Running, to left and right the character faces it also

*Crouch
-->If Crouched, do crouch
-->When in Crouch the gethitbox becomes much more smaller
-->When in crouch state disable ability to roll and slides 
-->Can be interrupted when hitted.
-->before and after crouching do _CrouchTransition

*CrouchWalk
-->if in crouch state then moves do crouch Walk
-->When crouchwalking the speed will be slowed 


*CrouchAttack
-->While Crouching do Attack to do Croucch Attack
-->The hitbox will be triggered at the 2nd frame of the animation

*Jump
-->If jumps do Jump
-->Before jumping do the transition animation the first frame of the _JumpFallbetween 
-->After jumping and before falling do the 2nd frame of the _JumpFallbetween
-->After doing the 2nd frame of _jumpFallbetween do Fall
-->When Jumping disables the ability to Roll 
-->After Jumping cooldowns for 0.3seconds.
-->After jumping slows the speed 

*Slide
-->Before sliding do _SlideTransitionStart and after Sliding do Slide Transition 
-->When run+slide, do slide at the player where is facing 1	
-->If the slide pushes the player forward in short distance for one second.
-->Increases the players speed only by little amount.
-->can be interrupted by jumping.
-->When sliding, disables the player to roll and move.
-->After sliding cooldown for 0.5seconds
-->After sliding the character goes back to slow speed 

*Roll
-->if roll do roll towards where the player is facing
-->When rolling the player push toward a short distance
-->If jumped while rolling interrupts its and jump
-->While rolling disables the ability to slide.
-->after rolling cooldowns for 0.5seconds

*Fall
-->Do Fall after Jumping
-->Do Fall when at air
-->unterrupted when hurt

*Dash
-->if dashes, do immidiently dashes toward the place the player is facing in short distance.
-->After dashing cooldowns for 0.5seconds

*Hurt
-->When taking damage, do hurt
-->If hurt pauses the frame for 0.5seconds
-->while in pause state disables the ability to Move includes rolling jumnping, rolling, running and etc.

/ / Speed Logic
--> When idling the player speed starts with non
--> When the player tries to run it starts slow and speed up gradually until it reach the normal run speed
--> The speed of the character animation or so called FPS correspond with the speed of the player, if slow lowers the fps, if fast speed up the animation.
--> When Running and tries turning around, slows the player before turning around and do the transition animation.
--> When the player slides animation speeds up and slows down rapidly
--> When jumping do momentum, if jump once keep the player speed but if jumped multiple times slows down the characters
--> After rolling the player slows down rapidly

/ / getattackHitbox and AttackHItbox
-->Ability to modify the gethitbox and attack hit box within their offset x&y and  their height and width. 
-->only allow the attack hitbox to be triggered within the frame it was assigned
-->Only dmg the player once per hitbox, dont loop 
-->toogle the visibility of the hitboxes 

/ / Collision and Map Logic
1. There will be 3 maps to choose [map1], [map2] and [map3]
2. In map there will be a TileSet, Platform and a background
3. The background will be the layer 1 of the map
4. The TileSet that will be the visual map but act as a background.
5. The platforms will be the one to handle the gravity collisions or where the player stand on.
6. The player Collision the player wont be able to collide each other and will use the hitbox as for their collsion instead of the player width and height.
7. Toggle the visibity of the platform draws
8. If possible make the backgrounds like the clouds to move for more good visuals.
9. The player cant go through the platforms

/ / Particles
1. AfterLanding
-->If drop on the ground after falling, do AfterFallPixelArt
-->The particles are the res folder named [AfterFallPixelArt] file name [FX052_01] -> [FX052_04] its a 4 frame effect in diff png. files.

2. Dash
-->When Dash, do Particle DashPixelArt
-->The particels are at the res folder named [DashPixelArt] file name [FX033_01] -> [Fx033_10] its a 10 frame effect in diff png. files

3. Hit
--> When hit, do particle HitPixelArt
-->The PArticles are at the res folder named [HitPixelArt] file name [FX046_01] -> [FX046_03] its a 3 frame effect in diff png. files.

4. Crit
-->When crit on hit, do particle CritPixelArt
-->The particles are at the res folder named [CritPixelArt] file name [FX036_01] -> [FX036_03] its a 3 frame effect in diff png files.


/ / UI
--> When Starting the game theres a Play, Credits and Quit Button button
--> Inside the Play button, able to choose map and how fast the round is and Start Playing
-->Before Starting the game do a loading screen to render everything and before they are able to move do a count down of 3 seconds to star the game
--> While playing can pause the game by pressing the backspace
-->The health of the player is displayed at the bottom of the character in textual visual ;like just "100%".
--> If a round finishes determine who won the round And tells who wob and add ups the score 
--> IF A game wins The player will be zoomed in and says Player Win or smt.
-->have a toggle whether Fullscreen or windowed.

/ / Gravity 
1. The Gravity logic applies to the player 
2. The player uses the Platform as the ground and doesn't Fukc with the tiles
3. When the player falls off the map it dies.

 / / Camera
1. The Camera Focuses at two players kind of ZoomIn
2. The Further the player's to each other is how the camera will be stretched
3. It will follows the movement the players, like in brawlhalla
4. If a player took dmg shake the camera a little bit 
5. If one of the player dies Focus the camera to the who is still alive


 / / Animations | |  SFX | | Files
Type: Animation
The Dimension of the animation is [Frames] * 120 x 80 
Filename | Frames
_Attack		| 4
_Attack2 	| 6
_AttackCombo	| 10
_Crouch		| 1
_CrouchTransition | 1
_CrouchAttack	| 4
_CrouchWalk	| 8
_Dash		| 2 
_Death		| 10
_Fall 	 	| 3
_Hit 	 	| 1
_Idle  	 	| 10
_Jump 		| 3
_JumpFallInbetween| 2
_Roll		| 12
_Run  		| 10
_Slide  	| 2
_SlideTransitionEnd | 1
_SlideTransitionStart  | 1
_TurnAround	| 3

Type: SFX
Filename | Purpose
bgSounds	| For Background music when the battle starts
BodyDrop	| When the character Lands on the ground after Falling
Hit1		| When Attack 1 hits the opponent
Hit2		| When Attack 2 hits the opponent
Hit1 + Hit2	| When Combo Attacks hits the opponent
Jump		| When jumped
Running		| When Running 
Walk		| Walk sfx
Scream + BodyDrops | When the player dies
Slash1		| If Attack 1
Slash2		| If Attack 2 
Slash3 + Slash1	| If Combo Attack [Frame 2 and Frame 77]



