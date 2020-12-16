// Agent gato1 in project amb5


+gatoAvistado (X,Y) : true
				<- !pegaGato.

+gatoApanhado : true
				<- !devorarGato.
				
+semSono (X,Y) : true
	<- !acordar (X,Y).

+!acordar (X, Y) : X < 29
	<- 	.wait (300);
		proximaCasaCao.
				
+!devorarGato : true
<- .print ("comeu gato");
				   .kill_agent (gato1);
				   fome(false).
				   
+!comerRacao : true
<- .print ("comeu racao");
				   fome(false).
								
+!pegaGato [source (donaCasa)] : true
				<- .wait(100);
				   .print ("Caçando o gato");
				    fome(false);
				    proximaCasaCao.
				
+!pegaGato [source (self)] : true
				<- .wait(100);
				   .print ("Caçando o gato");
				    proximaCasaCao.
				