package tppaMD_interface;


import gui.Base;
import javafx.scene.layout.Pane;


//feito

public class PaneOrganizer{

	private Pane raiz;
	
    public PaneOrganizer(JogoObservavel observed) {   
        raiz = new Base(observed); 
        
        
    }
    
    public Pane getRoot() {
        return raiz;
    }
    
	
	

}
