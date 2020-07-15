package tppaMD_interface;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tppaMD_logica.MaqEstados;

import java.awt.Label;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EditarRecursos extends Stage implements Constantes{
	
		
	JogoObservavel jogoObservavel;
	
	private ComboBox comboDar, comboReceber;
	private Button btnTroca, btnSair;
	private Stage stage;
	
	
	public EditarRecursos( JogoObservavel jogoObservavel) {
	
		this.jogoObservavel = jogoObservavel;
	
		criaLayout();
	
	}
	
	private void criaLayout() {
		
		MaqEstados mq = jogoObservavel.getMaqEstados();
	
		//stage = new Stage();

        // Create a label 
       // Label description_label = new Label("This is a combo box example "); 
  
        // Weekdays 
        String rDar[] = { "Vermelho", "Preto", "Verde", "Azul"}; 
  
        
        
        VBox vDar = new VBox(10);
        VBox vReceber = new VBox(10);
        
        Text tDar = new Text();
        Text tReceber = new Text();
        
        tDar.setText("Recurso a transformar");
        tReceber.setText("Recurso Transformado");
        
        // Create a combo box 
        comboDar = new ComboBox(FXCollections.observableArrayList(rDar)); 
        comboReceber = new ComboBox(FXCollections.observableArrayList(rDar)); 
        
        
        
        
        vDar.getChildren().addAll(tDar, comboDar);
        vDar.setAlignment(Pos.CENTER);
        vReceber.getChildren().addAll(tReceber,comboReceber);
        vReceber.setAlignment(Pos.CENTER);
        
        // Create a tile pane 
        //TilePane tile_pane = new TilePane(combo_box); 
        HBox hbox = new HBox(20);
        
        hbox.getChildren().addAll(vDar, vReceber);
        
        
        btnSair = new Button("Sair");
        btnTroca = new Button("Trocar");
        //btnSair.setAlignment(Pos.TOP_CENTER);
        //btnTroca.setAlignment(Pos.TOP_LEFT);
        
        btnTroca.setOnAction(new Troca());
        btnSair.setOnAction(new Sair());
        

        HBox btns = new HBox(10);
        btns.setAlignment(Pos.CENTER);
        btns.getChildren().addAll(btnTroca, btnSair);
        
        
        VBox todo = new VBox(10);
        todo.getChildren().addAll(hbox, btns);
        todo.setAlignment(Pos.CENTER);
        
        // Create a scene 
        Scene scene = new Scene(todo, 200, 200); 
        
		

        this.setHeight(JANELA_TROCA_ALTURA);
        this.setMinHeight(JANELA_TROCA_ALTURA);
        this.setMaxHeight(JANELA_TROCA_ALTURA);	
 
        this.setWidth(JANELA_TROCA_LARGURA);
        this.setMinWidth(JANELA_TROCA_LARGURA);
        this.setMaxWidth(JANELA_TROCA_LARGURA);
       
        
        
        this.setScene(scene);
	
	}
		
	
	
	class Troca implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	
        	int r1 = 0, r2 = 0;
        	//1- preto;
        	//2- vermelho;
        	//3- verde;
        	//4- azul;
        	
        	if(comboDar.getValue() == null || comboReceber.getValue() == null)
        	{
        		jogoObservavel.trocarRecursos(-1, 0, 0);
        		return;
        	}
        	
        	
        	if(comboDar.getValue().equals("Vermelho"))
        		r1 = 2;
        	else if(comboDar.getValue().equals("Verde"))
        		r1 = 3;
        	else if(comboDar.getValue().equals("Preto"))
        		r1 = 1;
        	else if(comboDar.getValue().equals("Azul"))
        		r1 = 4;
        		
        	if(comboReceber.getValue().equals("Vermelho"))
        		r2 = 2;
        	else if(comboReceber.getValue().equals("Verde"))
        		r2 = 3;
        	else if(comboReceber.getValue().equals("Preto"))
        		r2 = 1;
        	else if(comboReceber.getValue().equals("Azul"))
        		r2 = 4;
        	
        	
        	jogoObservavel.trocarRecursos(4, r1, r2);
        	
        	
        	
        }        
    }
	
	
	class Sair implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e){
        	EditarRecursos.super.close();
        }
	}
	

}
