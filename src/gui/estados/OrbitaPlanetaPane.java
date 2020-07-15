package gui.estados;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import tppaMD_interface.Constantes;
import tppaMD_interface.EditarRecursos;
import tppaMD_interface.JogoObservavel;
import tppaMD_logicaEstados.EstadoOrbitaPlaneta;


public class OrbitaPlanetaPane extends HBox implements Constantes, PropertyChangeListener
{
	private JogoObservavel jobs;
	
	private Button voltarAoEspaco;
	private Button descerPlaneta;
	private Button estacaoEspacial;
	private Button verRecursosPlaneta;
	
	
	public OrbitaPlanetaPane (JogoObservavel jo) {
		jobs = jo;
		jobs.addPropertyChangeListener(this);
		
		criaBotoes();
		
		this.setVisible(false);
	}
	
	private void criaBotoes() {
		
		voltarAoEspaco = new Button("Sair de Orbita");
		descerPlaneta = new Button("Entrar no planeta");
		estacaoEspacial= new Button("Ir para Estação Espacial");
		verRecursosPlaneta = new Button("Ver recursos do planeta");
		
		
		//estacaoEspacial.setVisible(jobs.temEstacaoEspacial());
		
		setAlignment(Pos.CENTER);
        getChildren().addAll( voltarAoEspaco, estacaoEspacial,verRecursosPlaneta, descerPlaneta );
        
        voltarAoEspaco.setOnAction(new VoltarAoEspaço());
        estacaoEspacial.setOnAction(new EstacaoEspacial());
        descerPlaneta.setOnAction(new DescerAoPlaneta());
        verRecursosPlaneta.setOnAction(new VerRecursosPlaneta());

	}
	
	class VerRecursosPlaneta implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent e) {
        	MostrarRecursos edt = new MostrarRecursos(jobs);		//criar classe que permite criar janela e deixa modificar os dados
        	edt.initOwner(getScene().getWindow());
        	edt.initModality(Modality.APPLICATION_MODAL);
        	edt.showAndWait();
    		
		}
	}
	
	
	class VoltarAoEspaço implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
            
            jobs.irParaEstadoEsperaMover();
            
		}       	
	}
	
	class EstacaoEspacial implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
            
            jobs.entraEstacaoEspacial();
            
		}       	
	}
	
	class DescerAoPlaneta implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
            
			 jobs.entrarNoPlaneta();
            
		}       	
	}

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		estacaoEspacial.setVisible(jobs.temEstacaoEspacial());
		setVisible(jobs.getEstado() instanceof EstadoOrbitaPlaneta);
		
	}

}
