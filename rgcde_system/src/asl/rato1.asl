+quantidadeQueijo (X,Y) : true
	<-	!procurarQueijo(X,Y).
	
+!procurarQueijo(X,Y) : true
	<-	.wait (200);
		proximaCasaRato.	