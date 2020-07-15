package gui.estados;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import gui.estados.EsperaMoverPane.Avanca;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logicaEstados.EstadoMercadoNaEstacaoEspacial;
import tppaMD_logicaEstados.EstadoReageEvento;

public class ReageEventoPane extends HBox implements Constantes, PropertyChangeListener{
	
	private JogoObservavel jobs;
	
	private Button avanca;
	
	public ReageEventoPane(JogoObservavel jo) {
		this.jobs = jo;
		this.jobs.addPropertyChangeListener(this);
		
		
		criaBotoes();
		
		
	}

	
	private void criaBotoes() {
		
		avanca = new Button("Passar pelo evento");
		
		
		setAlignment(Pos.CENTER);
		getChildren().add(avanca);
		
		
		avanca.setOnAction(new Avanca());		
		
		this.setVisible(false);		
	}


	class Avanca implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            jobs.aplicaEvento();
        }        
    }
	
	
	
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setVisible(jobs.getEstado() instanceof EstadoReageEvento ); 

		
	}

}
