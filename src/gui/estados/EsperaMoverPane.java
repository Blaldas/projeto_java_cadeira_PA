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
import tppaMD_logicaEstados.EstadoEsperaMover;


public class EsperaMoverPane extends HBox implements Constantes, PropertyChangeListener{
	
	private JogoObservavel jobs;
	
	private Button avanca;
	
	
	public EsperaMoverPane (JogoObservavel jo)
	{
		this.jobs = jo;
		this.jobs.addPropertyChangeListener(this);
		
		
		avanca = new Button("Avançar no espaço");
		
		
		setAlignment(Pos.CENTER);
		getChildren().add(avanca);
		
		avanca.setOnAction(new Avanca());		
		this.setVisible(false);
	}

	
	class Avanca implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
            jobs.avancaNoEspaço();
        }        
    }
	
	
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		setVisible(jobs.getEstado() instanceof EstadoEsperaMover);
		
	}
	
}
