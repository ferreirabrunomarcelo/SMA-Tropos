// Agent gato1 in project amb5

+aindaNaoPegou (X,Y) : true
				<- !pegaRato.

+ratoApanhado : true
				<- .print ("Nham! Nham! Nham!");
				   .kill_agent (rato1).
								
+!pegaRato [source (donaCasa)] : true
				<- .wait(100);
				   .print ("Caçando o rato");
				    proximaCasaGato.
				
+!pegaRato [source (self)] : true
				<- .wait(100);
				   .print ("Caçando o rato");
				    proximaCasaGato.
				