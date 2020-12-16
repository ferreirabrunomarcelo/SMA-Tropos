import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.logging.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ambiente5 extends Environment {   // Classe de ambiente

	public static final Term    pc = Literal.parseLiteral("proximaCasa");
	public static final Term	pcg = Literal.parseLiteral("proximaCasaGato");
	
    private Location donaCasaLoc, gatoLoc, ratoLoc;


	private ModeloAmbiente modelo;				// variável de modelo
    private VisaoAmbiente  visao;				// variável de visão

    class ModeloAmbiente extends GridWorldModel {		// Classe de modelo

       public ModeloAmbiente (int arg0, int arg1, int arg2) {	// Recebe a coluna, linha e agente
	           super(arg0, arg1, arg2);
	            try {
		            setAgPos(0, 0, 0);								// Posiciona o primeiro agente na posição 0,0
		            setAgPos(1, 9, 9);
		            setAgPos(2, 7, 1);
		            
		        	donaCasaLoc = getAgPos(0);
		        	gatoLoc = getAgPos(1);
		        	ratoLoc = getAgPos(2);

               	    } catch (Exception e) {
                           e.printStackTrace();
                    }
        	}
       
       void proximaCasa() {

       	Location donaCasaLoc = getAgPos(0);
       	            
      	int colunaDona = donaCasaLoc.x;
      	int ratoAchado = 0;
       	
       	for (int coluna = colunaDona; coluna <= (colunaDona + 3); coluna++) {
        	if ((coluna == ratoLoc.x) && (donaCasaLoc.y == ratoLoc.y)) {
        		ratoAchado = 1;
        	    Literal ratoPercebido = Literal.parseLiteral("ratoPercebido(" + ratoLoc.x +"," + ratoLoc.y + ")");
        	    addPercept(ratoPercebido);
        	}
       		
       	}

       	if (ratoAchado == 0) {
           	donaCasaLoc.x++;
            
           	if (donaCasaLoc.x == getWidth()) {
           		donaCasaLoc.x = 0;
           		donaCasaLoc.y++;
           	    }
           	            
           	if (donaCasaLoc.y == getHeight()) {
           	    return;
           	    }
           	      	
           	setAgPos(0, donaCasaLoc);
            Literal pos1 = Literal.parseLiteral("pos(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
            addPercept(pos1);
       	}
       	
        }
       
       void proximaCasaGato() {

          	gatoLoc = getAgPos(1);
          	ratoLoc = getAgPos(2);
          	
           	if ((gatoLoc.x == ratoLoc.x) && (gatoLoc.y == ratoLoc.y)) {
           	    Literal ratoApanhado = Literal.parseLiteral("ratoApanhado");
           	    addPercept(ratoApanhado);
           	}
           	else {
           		
           		if (ratoLoc.x > gatoLoc.x) {
               		gatoLoc.x++;
           		}
               
           		if (ratoLoc.x < gatoLoc.x) {
               		gatoLoc.x--;
           		}

           		if (ratoLoc.x == gatoLoc.x) {
           		   if (ratoLoc.y > gatoLoc.y) {
           			   gatoLoc.y++;
           		   }
           		   
           		   if (ratoLoc.y < gatoLoc.y) {
           			   gatoLoc.y--;
           		   }
           		   
           		}

          	}

               setAgPos(1, gatoLoc);
               Literal perseguicao = Literal.parseLiteral("aindaNaoPegou (" + gatoLoc.x + ", " + gatoLoc.y + ")");
       	       addPercept(perseguicao);
          	
        }       
    }
    
class VisaoAmbiente extends GridWorldView {
    
    public VisaoAmbiente(ModeloAmbiente model) {
              
		super(model, "Mundo CasaInfestada", 700);
             
    	defaultFont = new Font("Arial", Font.BOLD, 8); // Muda a fonte padrão
            
   		setVisible(true);            
   		
		repaint();
	  }
    
    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		String rotulo = "";

		switch (id) {
		case 0: {
			c = Color.green;
			rotulo = new String ("Dona");
			break;
		}
		case 1: {
			c = Color.yellow;
			rotulo = new String ("Gato");
			break;
		}
		case 2: {
			c = Color.gray;
			rotulo = new String ("Rato");
			break;
		}
		}
		
		if (id >= 0 && id < 3) {
			super.drawAgent(g, x, y, c, -1);

			g.setColor(Color.black);
	            
			super.drawString(g, x, y, defaultFont, rotulo);
	     
			setVisible(true);
		}

    }
}


 /* Chamado antes da execução da MAS como os argumentos informados em .mas2j */

    @Override
    public void init(String[] args) {
        super.init(args);
        
        modelo = new ModeloAmbiente(30,30,3);
        
        visao  = new VisaoAmbiente(modelo);
               
        modelo.setView(visao);
 
        clearPercepts();

    	donaCasaLoc = modelo.getAgPos(0);
        Literal pos1 = Literal.parseLiteral("pos(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
        addPercept(pos1);        
                
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
       if (true) { 
             informAgsEnvironmentChanged();
        }
        
        if (action.equals(pc)) {
        	modelo.proximaCasa();
        } 
        
        if (action.equals(pcg)) {
        	modelo.proximaCasaGato();
        }
        return true; 
    }
    
    
    
 /* Chamado antes do fim da execução do MAS */ 
    @Override
    public void stop() {
        super.stop();
    }


}