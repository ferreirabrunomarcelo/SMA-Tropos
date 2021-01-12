import jason.asSyntax.*;

import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.util.Random;
import java.util.logging.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ambiente5 extends Environment {   // Classe de ambiente

	public static final Term    pc = Literal.parseLiteral("proximaCasa");
	public static final Term	pcg = Literal.parseLiteral("proximaCasaGato");
	public static final Term	pcr = Literal.parseLiteral("proximaCasaRato");

    private Location donaCasaLoc, gatoLoc, ratoLoc;
    public int posQueijoX = 20, posQueijoY = 20;

    public static final int QUEIJO  = 16;

	private ModeloAmbiente modelo;				// variï¿½vel de modelo
    private VisaoAmbiente  visao;				// variï¿½vel de visï¿½o

    class ModeloAmbiente extends GridWorldModel {		// Classe de modelo

       public ModeloAmbiente (int arg0, int arg1, int arg2) {	// Recebe a coluna, linha e agente
	           super(arg0, arg1, arg2);
	            try {
		            setAgPos(0, 0, 0);								// Posiciona o primeiro agente na posiï¿½ï¿½o 0,0
		            setAgPos(1, 9, 9);
		            setAgPos(2, 7, 1);
		            addQueijos();
		        	donaCasaLoc = getAgPos(0);
		        	gatoLoc = getAgPos(1);
		        	ratoLoc = getAgPos(2);

               	    } catch (Exception e) {
                           e.printStackTrace();
                    }
        	}
       
       void addQueijos() {
    	   Random posQueijos = new Random();
    	   for(int i = 0; i < 12; i++) {
    		   int x = posQueijos.nextInt(29);
    		   int y = posQueijos.nextInt(29);
    		   posQueijoX = x;
    		   posQueijoY = y;
    		   add(QUEIJO, x, y);
    		   
    	   }
       }
       
       void proximaCasa() {
    	   
       	Location donaCasaLoc = getAgPos(0);
       	
      
       	            
       	
      	int colunaDona = donaCasaLoc.x;
      	int ratoAchado = 0;
       	
       	for (int coluna = colunaDona; coluna <= (colunaDona + 3); coluna++) {
        	if ((coluna == ratoLoc.x) && (donaCasaLoc.y == ratoLoc.y)) {
        		ratoAchado = 1;
        	    Literal ratoPercebido = Literal.parseLiteral("chamargato(" + ratoLoc.x +"," + ratoLoc.y + ")");
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
       void proximaCasaRato() {
    	   
    	   //ratoLoc = getAgPos(2);
    	   //remove(QUEIJO, ratoLoc.x, ratoLoc.y);
    	   
    	   //ISSO AQUI É O MOVIMENTO PARA O CÃO QUANDO A DONA DE CASA O CHAMAR / CARTEIRO ACHAR DONA DE CASA
    	   /*if (ratoLoc.x != posQueijoX) {
    			if (ratoLoc.x < posQueijoX) {
    				ratoLoc.x++;
    			} else {
    				ratoLoc.x--;
    			} 
    		} else if (ratoLoc.y != posQueijoY) {
    			if (ratoLoc.y < posQueijoY) {
    				ratoLoc.y++;
    			} else {
    				ratoLoc.y--;
    			}
    		}*/
    	   
    	  
    	   //getAgPos(2, ratoLoc);
    	  ratoLoc = getAgPos(2);
    	   Random alea = new Random();
    	 
    	   
    	   int linhatual = ratoLoc.x;
    	   int colunatual = ratoLoc.y;
    		
    	   int direcao = alea.nextInt(4);
    	   switch (direcao) {
    	  
    		case 0: {
    			if (ratoLoc.x < 29) {
    				ratoLoc.x ++;
    			}
    			else if (ratoLoc.y < 29) {
    				ratoLoc.y ++;
    			}
    			break;
    		}
    		case 1:	{
    			if (ratoLoc.x > 0) {
    				ratoLoc.x --;
    			}
    			break;
    			}
    		case 2: {
    			if (ratoLoc.y < 29) {
    				ratoLoc.y ++;
    			}
    			break;
    		}
    		case 3:	{
    			if (ratoLoc.y > 0) {
    				ratoLoc.y --;
    			
    			}
    			break;
    		}
    		}
    	   
    	   if (hasObject(QUEIJO, ratoLoc)) {
    			ratoLoc.x = linhatual;
    			ratoLoc.y = colunatual;
    		}

    		setAgPos(2, ratoLoc);
    	   Literal procurarQueijo = Literal.parseLiteral("quantidadeQueijo (" + ratoLoc.x + ", " + ratoLoc.y + ")");
   	       addPercept(procurarQueijo);
       }       
    }
    
class VisaoAmbiente extends GridWorldView {
    
    public VisaoAmbiente(ModeloAmbiente model) {
              
		super(model, "Mundo CasaInfestada", 700);
             
    	defaultFont = new Font("Arial", Font.BOLD, 8); // Muda a fonte padrï¿½o
            
   		setVisible(true);            
   		
		repaint();
	  }
    
    
    @Override
	public void draw(Graphics g, int x, int y, int object) {
		switch (object) {
	            
			case Ambiente5.QUEIJO:
				desenhaQueijo(g, x, y);
				break;
		}
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
    
    
    public void desenhaQueijo(Graphics g, int x, int y) {
        
    	
    	super.drawObstacle(g, x, y);
    	g.setColor(Color.red);
            
    	drawString(g, x, y, defaultFont, "QUEIJO");
    	
     }
    
    
}




 /* Chamado antes da execuï¿½ï¿½o da MAS como os argumentos informados em .mas2j */

    @Override
    public void init(String[] args) {
        super.init(args);
        
        modelo = new ModeloAmbiente(30,30,3);
        
        visao  = new VisaoAmbiente(modelo);
               
        modelo.setView(visao);
 
        clearPercepts();

       
    	donaCasaLoc = modelo.getAgPos(0);

 //Literal pos1 = Literal.parseLiteral("pos(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
// addPercept(pos1);
        
 ratoLoc = modelo.getAgPos(2);
 Literal posRato = Literal.parseLiteral("quantidadeQueijo(" + ratoLoc.x + "," + ratoLoc.y + ")");
 addPercept(posRato);    

 Literal pos1 = Literal.parseLiteral("perambularpeloambiente(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
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
        
        if (action.equals(pcr)) {
        	modelo.proximaCasaRato();
        	
        }
       
        return true; 
    }
    
    
    


}