# Bricktris
A Tetris clone build in JavaFX

Build in JavaFX, this is my second project. Tetris is one of my favorite puzzle games so I tried to give my homage with a little extra 
from myself sprinkled over. The game is fully featured, with different game modes, win conditions,  
complex scoring system and applicable high score tables for each mode, special effects like sounds and visual effects during gameplay etc. 
There are still some minor bugs and inconsistencies in the code, but all should be minor and not game breaking. 

When the program is fired, it initialises most classes in the StandardFX interface so all classes can use the other classes when needed.
Only the GameFX class is initialised before the game is started to guarantee a fresh game board and variables necessary for game play.
The game board consists of a VBox with 20 Hboxes of 10 Rectangles wide. A shape (object) on the board traverses these rectangles 
and switches them on or off during movement. A list of next shapes is kept up to date on the right side of the board, and on the left
a swap box shows the currently swapped shape ready to be swapped in when needed.

One of the most time demanding effects was making the reflection that hovers above the bottom of the board / shapes on the board.
It updates the location of the reflection on the fly so you always see where the current shape will drop.

When the victory or game over conditions are met, the game shows the game over screen with a highscore table where the player can input a 
name when they have a high score.

(During gameplay, when the game is over and one doesn't see the game over text and continue button in the middle of the screen,  
click in that area, the button is there but it sometimes doesn't show) 

De game code is fully designed and custom made by myself. I made this for portfolio purposes only and it is not meant  
for sale in any way. This program and it's custom made parts can be used freely except for commercial gain.  
I used free downloadable music from certain websites I link to in the credits menu in game, most if not all background are from  
free wallpaper websites and the special visual effects are from a freely usable website as well. 
I cannot guarantee that certain sound effects and a few backgrounds are not rights free, if that's the case and you find  
you have the rights to these creations, please let me know and I'll remove them from the game and github if necessary. 

Gameplay:

'W' swaps a shape fron/on the board.
'A' & 'S' rotate a shape (counter) clockwise.
'Space' Drops a shape as far down as possible
Arrows 'left', 'right', 'down' move the shape in that direction
'Enter' / 'Escape' open the pause menu during gameplay.

Repository:

Jar - Contains a build of the game
Resources - contains all music, sounds, effects, fonts and backgrounds used in the game.
src - all classes / interfaces used

Images:

![Image 1](https://github.com/Primaat/Bricktris/blob/master/screenshots/TitleScreenSmall.png)
![Image 2](https://github.com/Primaat/Bricktris/blob/master/screenshots/ModeSelectScreen.png)
![Image 3](https://github.com/Primaat/Bricktris/blob/master/screenshots/GamePlayScreen1.png)
![Image 4](https://github.com/Primaat/Bricktris/blob/master/screenshots/GamePlayScreen2.png)
![Image 5](https://github.com/Primaat/Bricktris/blob/master/screenshots/GameOverScreen.png)
