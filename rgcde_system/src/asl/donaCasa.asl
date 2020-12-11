// Agent sample_agent in project amb5

/* Initial beliefs and rules */

/* Initial goals */

+pos(L, X, Y) : true
	<- 	!mover (X, Y).

+ratoPercebido(X, Y) : true
	<- 	.print("O rato está na posição ", X, " e ", Y);
		.send (gato1, achieve, pegaRato).
		
+!mover (X, Y) : X < 9
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : X == 9 & Y < 9
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : true
		.
		