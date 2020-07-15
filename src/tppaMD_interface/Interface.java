package tppaMD_interface;

import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tppaMD_logica.MaqEstados;


public class Interface extends Application implements Constantes {
	

	public static void main(String[] args) {
        launch(args);
    }

	
		
    @Override
    public void start(Stage primaryStage){

    	MaqEstados maqEstados = new MaqEstados();
    	JogoObservavel observed = new JogoObservavel(maqEstados);
    	
    	
    	//janela principal
    	PaneOrganizer organizer = new PaneOrganizer(observed); 
    	
        Scene main = new Scene(organizer.getRoot());
           
        primaryStage.setTitle("Jogo");
        primaryStage.setScene(main);
        
        primaryStage.setWidth(JANELA_LARGURA);
        primaryStage.setMinWidth(JANELA_LARGURA);
        primaryStage.setMaxWidth(JANELA_LARGURA);
       
        primaryStage.setHeight(JANELA_ALTURA);
        primaryStage.setMinHeight(JANELA_ALTURA);
        primaryStage.setMaxHeight(JANELA_ALTURA);	
 
        primaryStage.show();
       
 
        //janela que mostra os dadas da nave
        Stage DadosNave = new Stage();
        
        Scene nave= new Scene(new ListRoot(observed) );
  
        DadosNave.setTitle("Dados da Nave");
        DadosNave.setScene(nave);
        DadosNave.setX(primaryStage.getX()+primaryStage.getWidth());
        DadosNave.setY(primaryStage.getY());
        DadosNave.setHeight(JANELA_ALTURA);
        DadosNave.setWidth(JANELA_SECUNDARIA_LARGURA);
        DadosNave.show();

        
        //caso fechem alguma das janelas
        primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	DadosNave.close();
                Platform.exit();
            }
        });
        DadosNave.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	primaryStage.close();
                Platform.exit();
            }
        });
        
    }

	
	
	
}
