// Agent sample_agent in project amb5

/* Initial beliefs and rules */

/* Initial goals */

+pos(L, X, Y) : true
	<- 	!mover (X, Y).

+ratoPercebido(X, Y) : true
	<- 	.print("O rato est� na posi��o ", X, " e ", Y);
		.send (gato1, achieve, pegaRato).
		
+!mover (X, Y) : X < 29
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : X == 29 & Y < 29
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : true
		.
		