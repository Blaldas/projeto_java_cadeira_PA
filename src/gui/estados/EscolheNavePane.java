package gui.estados;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logicaEstados.EstadoSelectVeicle;


public class EscolheNavePane extends HBox implements Constantes, PropertyChangeListener{

	private JogoObservavel jobs;
	
	private Button mineira;
	private Button militar;
	
	public EscolheNavePane (JogoObservavel jogoObservavel)
	{
		this.jobs= jogoObservavel;
		this.jobs.addPropertyChangeListener(this);	
		
		criaBotoes();
	
	
	}
	
	private void criaBotoes() {
		
		mineira = new Button("Nave Mineira");
		militar = new Button("Nave Militar");
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(mineira, militar);
		
		mineira.setOnAction(new IniciaMineira());
		militar.setOnAction(new IniciaMilitar());
		

		this.setVisible(true);
	}
	
	
	 class IniciaMineira implements EventHandler<ActionEvent> {
	        @Override
	        public void handle(ActionEvent e){
	           jobs.iniciaMineira();
	           
	        }        
	    }
	 
	 class IniciaMilitar implements EventHandler<ActionEvent> {
	        @Override
	        public void handle(ActionEvent e){
	        	 jobs.iniciaMilitar();	        }        
	    }
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		setVisible(jobs.getEstado() instanceof EstadoSelectVeicle ); 		
	}


	
}
