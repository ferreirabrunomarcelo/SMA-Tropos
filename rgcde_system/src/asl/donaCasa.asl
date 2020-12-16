// Agent sample_agent in project amb5

/* Initial beliefs and rules */

/* Initial goals */

+perambularpeloambiente(L, X, Y) : true
	<- 	!mover (X, Y).

+perceberGato(X, Y) : true
	<- 	chamarGato (X,Y).
		
+perceberCao(X, Y) : true
	<- 	!chamarCao (X, Y).
	
+semRacao (X, Y) :true
	<- !pedirRacao.
	
+!pedirRacao : true
	<- 	.print("dar ração", X, " e ", Y);
		.send (entregador, achieve, entregarRacao).
	
+!chamarCao (X,Y) : true
<- 	.print("dar ração", X, " e ", Y);
		.send (cao1, achieve, comerRacao).
		
+!chamarGato (X,Y) : true
<- 	.print("O rato está na posição ", X, " e ", Y);
		.send (gato1, achieve, pegaRato).
		
+!mover (X, Y) : X < 29
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : X == 29 & Y < 29
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : true
		.
		