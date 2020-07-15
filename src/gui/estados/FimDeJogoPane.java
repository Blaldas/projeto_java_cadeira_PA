package gui.estados;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logicaEstados.EstadoFimDeJogo;



public class FimDeJogoPane extends HBox implements Constantes, PropertyChangeListener{

	JogoObservavel jobs;
	
	Button recomecar;
	Button sair;
	Button mostrarLog;
	
	public FimDeJogoPane(JogoObservavel jo) {
		jobs = jo;
		jobs.addPropertyChangeListener(this);
		
		monta();
		
	}
	
	///////////////////////
	////sair//recomecar////
	//////mostrarLog///////
	///////////////////////
	public void monta() {
		
		
		//HBox mudaEstado = new HBox();
		sair = new Button("Finalizar Jogo");
		recomecar = new Button("Recomeçar Jogo");
		
		
		//mudaEstado.setAlignment(Pos.CENTER);
		//mudaEstado.getChildren().addAll(sair, recomecar);
       
		recomecar.setOnAction(new Recomecar());
        sair.setOnAction(new Sair());
		
        HBox mostraLog = new HBox();
        mostrarLog = new Button("Ver Log");
        
        //mostraLog.setAlignment(Pos.CENTER);
        //mudaEstado.getChildren().add(mostrarLog);
        
        mostrarLog.setOnAction(new MostrarLog());
        
        getChildren().addAll(recomecar, sair, mostrarLog);
        setAlignment(Pos.CENTER);
        
    	this.setVisible(false);
        
        
	}
	
	
	
	class Sair implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.FimOuRecomeco(1);

        }        
    }
	
	class Recomecar implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.FimOuRecomeco(2);
            
        }     
    }
	
	class MostrarLog implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	
        	System.out.println( jobs.mostrarLog());
            jobs.getjogoObservavel().getJogo().addLogComentarios("Veja a consola");
            jobs.atualiza();
            
        } 
	
	}
	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setVisible(jobs.getEstado() instanceof EstadoFimDeJogo ); 
		
	}

}
