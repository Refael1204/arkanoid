Arkanoid Game:
This game is written in Java and demonstrates the fundamentals of Object-Oriented Programming (OOP). It also makes heavy use of the Observer design pattern and incorporates mechanics-based physics.

Overview:
The game features a variety of objects that interact dynamically:

Obvious objects: Bricks, balls, paddle, and levels.
Deeper objects: Level configurations, backgrounds, collidable objects, points, lines, and animations.
Physics and Mechanics
The game's physics system relies on vector calculations and mathematical modeling:

Each ball has a current position and a trajectory position (position in the next frame).
The distance between these two points is determined by the ball's velocity.
During each frame of the animation loop, the ball executes the moveOneStep() method to:
Calculate its trajectory.
Determine if a collision is expected with any collidable object.
If a collision is detected:

The ball updates its direction based on the type of object it collides with:
Hitting a block: The ball bounces in the opposite direction, and the block is removed.
Hitting the paddle: The bounce angle depends on the collision location on the paddle.
This collision logic involves multiple calculations and interactions, which can become complex. To manage this effectively, the Observer design pattern is used.

Observer Design Pattern Implementation
This project uses a GUI platform, where many objects are sprites (animated elements displayed on the screen). Sprites must update their state every frame, making the Observer pattern an ideal choice for managing updates and interactions.

How it Works:
1. Sprites as Listeners:

	- All objects implementing the Sprite interface act as listeners.
	- They are notified whenever a new frame is rendered.
	- Each sprite has its own implementation of the timePassed() method, which handles its logic 	  for the current frame.

2. Level as Notifier:

	- The level object acts as the notifier.
	- It iterates through a list of sprites and calls their timePassed() method every frame.

3. Collision Logic:

	- The ball calculates potential collisions by creating a line between its current and 	  trajectory positions.
	- If a collision occurs, the colliding object sends information back to the ball.
	- The ball updates its direction accordingly based on the type and properties of the 	  colliding object.

This implementation ensures efficient updates and separation of concerns, making the codebase more manageable and scalable.

Features:

Real-time collision detection and physics-based interactions.
Dynamic animation of sprites with seamless frame-by-frame updates.
Modular design utilizing OOP principles and design patterns.


Summary:

The Arkanoid game showcases an advanced implementation of OOP, design patterns, and physics in Java. It demonstrates effective use of the Observer pattern to manage complex interactions between objects, ensuring smooth gameplay and logical consistency.

