package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import recursos.Imagens;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logica.MaqEstados;
import tppaMD_logicaEstados.EstadoEsperaMover;
import tppaMD_logicaEstados.EstadoFimDeJogo;
import tppaMD_logicaEstados.EstadoMercadoNaEstacaoEspacial;
import tppaMD_logicaEstados.EstadoOrbitaPlaneta;
import tppaMD_logicaEstados.EstadoReageEvento;
import tppaMD_logicaEstados.EstadoReageSuperficie;
import tppaMD_logicaEstados.EstadoSelectVeicle;
import tppaMD_logicaEstados.IEstado;

/*
 * contem os menus load e save.
 * Não creio que necessite de infoirmações
 * testar depois
 */
public class Base extends VBox  implements Constantes, PropertyChangeListener {

	private final JogoObservavel observableGame; 
	private final BasePane BPane;				//criar classe
	
	private BackgroundImage myBI;
	private BackgroundSize backgroundSize;
	 
	public Base(JogoObservavel observableGame) {
	    
	    this.observableGame = observableGame;
	    this.observableGame.addPropertyChangeListener(this);			//criar esta funcao
	 
	    
	   // int dimMin = Math.min(JANELA_LARGURA, JANELA_ALTURA);

	    backgroundSize = new BackgroundSize(JANELA_LARGURA, JANELA_ALTURA, false, false, false, false);
 
	    myBI= new BackgroundImage(Imagens.loadImage(CAPA), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	    
	    
	    
	    setBackground(new Background(myBI));
	   
	    
	    BPane = new BasePane(observableGame);					//cria a classe
	    
	    //BPane.setPrefHeight(this.getHeight());
	    //BPane.setPrefWidth(this.getWidth());
	   
    
	    getChildren().addAll(getMenuBar(), BPane);
	    
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	   IEstado state = observableGame.getEstado();				//criar funcao
	   
	   if(state instanceof EstadoSelectVeicle || state instanceof EstadoFimDeJogo )
		   myBI= new BackgroundImage(Imagens.loadImage(CAPA), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	   else if(state instanceof EstadoOrbitaPlaneta)
		   myBI= new BackgroundImage(Imagens.loadImage(ORBITA), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	   else if(state instanceof EstadoReageEvento)
		   myBI= new BackgroundImage(Imagens.loadImage(EVENTO), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	   else if(state instanceof EstadoMercadoNaEstacaoEspacial)
		   myBI= new BackgroundImage(Imagens.loadImage(SS), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	   else if(state instanceof EstadoEsperaMover)
		   myBI= new BackgroundImage(Imagens.loadImage(ESPERA), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	   else if(state instanceof EstadoReageSuperficie)
		   myBI= new BackgroundImage(Imagens.loadImage(SUPERFICIE), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
	   
	   setBackground(new Background(myBI));
	   //newMenuItem.setDisable(!(state instanceof AwaitBet));		disabilita o menu em estado especifico
	}
	
	private MenuBar getMenuBar() {        
	   
		//cria os itens
		MenuBar menuBar = new MenuBar();       
	    Menu menuFile = new Menu("Jogo");
	    MenuItem load = new MenuItem("Load");
	    MenuItem save = new MenuItem("Save");
	    
	    //adiciona atalhos
	    load.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
	    save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
	    
	    //inidca ações ao serem clicados
	    load.setOnAction(new LoadJogo());
	    save.setOnAction(new SalvarJogo());
	    
	    //acrescenta os itens aos respectivos pais
	    menuFile.getItems().addAll(load, save);       
	    menuBar.getMenus().add(menuFile);
	    
	    //devolve MenuBar
	    return menuBar;
	}
	
	
	class LoadJogo implements EventHandler<ActionEvent>  {
	    @Override
	    public void handle(ActionEvent e) {
	    	FileChooser fc = new FileChooser();
    		File f = fc.showOpenDialog(getScene().getWindow());
    		if(f == null) 
    			return;
    		
    		try {
    			
    			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
    			MaqEstados maqEstados = (MaqEstados) ois.readObject();
    			observableGame.setJogoObservavel(maqEstados);;
    			ois.close();
    			
    		}catch(Exception ex) {
    			Alert alerta = new Alert(Alert.AlertType.ERROR);
    			alerta.setContentText("nao deu para ir buscar");
    			alerta.setTitle("Erro!");
    			alerta.show();
    		}
    		
	    }
	}
	
	class SalvarJogo implements EventHandler<ActionEvent> {
	
	    @Override
	    public void handle(ActionEvent e) {
	    	FileChooser fc = new FileChooser();
    		File f = fc.showSaveDialog(getScene().getWindow());
    		if(f == null) 
    			return;
    		
    		try {
    			
    			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
    			oos.writeObject(observableGame.getMaqEstados());
    			oos.close();
    			
    		}catch(Exception ex) {
    			Alert alerta = new Alert(Alert.AlertType.ERROR);
    			alerta.setContentText("nao deu para ir guardar");
    			alerta.setTitle("Erro!");
    			alerta.show();
    		}
	    }
	}
}
