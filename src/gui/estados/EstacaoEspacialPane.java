package gui.estados;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logicaEstados.EstadoMercadoNaEstacaoEspacial;


public class EstacaoEspacialPane extends VBox implements Constantes, PropertyChangeListener{

	private JogoObservavel jobs;
	
	private Button melhorarCargo;
	private Button healLandingCraft;
	private Button contratarMilitar;
	private Button upgradeWeapons;
	private Button comprarDrone;
	private Button voltarOrbita;
	
	
	public 	EstacaoEspacialPane (JogoObservavel jo)
	{
		jobs = jo;
		jobs.addPropertyChangeListener(this);
		
		melhorarCargo		= new Button("Melhorar Porão");
		healLandingCraft	= new Button("Reparar drone");
		contratarMilitar	= new Button("Contratar Oficial");
		upgradeWeapons		= new Button("Melhorae Armas");
		comprarDrone		= new Button("Obter novo Drone");
		
		voltarOrbita		= new Button("Voltar Para Orbita");
	
		
		HBox trocas= new HBox();
		trocas.setAlignment(Pos.CENTER);
		trocas.setSpacing(10);
		trocas.getChildren().addAll(melhorarCargo, healLandingCraft, contratarMilitar, upgradeWeapons, comprarDrone);
		
		setAlignment(Pos.CENTER);
		setSpacing(10);
		getChildren().addAll(trocas, voltarOrbita);
		
		
		melhorarCargo.setOnAction(new MelhorarCargo()); 
		healLandingCraft.setOnAction(new HealLandingCraft()); 
		contratarMilitar.setOnAction(new ContratarMilitar()); 
		upgradeWeapons.setOnAction(new UpgradeWeapons()); 
		comprarDrone.setOnAction(new ComprarDrone()); 
		
		voltarOrbita.setOnAction(new VoltarOrbita());
		
		this.setVisible(false);
	}
	
	class MelhorarCargo implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.MercadoEstacaoEspacial(1);
        }        
    }
	
	class HealLandingCraft implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.MercadoEstacaoEspacial(2);
        }        
    }
	
	class ContratarMilitar implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.MercadoEstacaoEspacial(3);
        }        
    }
	
	class UpgradeWeapons implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.MercadoEstacaoEspacial(4);
        }        
    }
	
	class ComprarDrone implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.MercadoEstacaoEspacial(5);
        }        
    }
	
	class VoltarOrbita implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	jobs.MercadoEstacaoEspacial(0);
        }        
    }

	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		setVisible(jobs.getEstado() instanceof EstadoMercadoNaEstacaoEspacial ); 
		
	}

}

