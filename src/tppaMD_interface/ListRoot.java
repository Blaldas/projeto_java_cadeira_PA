package tppaMD_interface;


import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import tppaMD_logica.MaqEstados;


public class ListRoot extends BorderPane {
		JogoObservavel jogoObservavel;
		ListView <String> DadosNave;
		Button trocarRecursos;
		Button fazCombustivel;
		Button fazEscudo;
		Button fazAmmo;
		
		
		
	    public ListRoot(JogoObservavel jogoObservavel) {
	    	this.jogoObservavel = jogoObservavel;
	    	
	        criaLayout();
	        registaListeners();
	        
	        jogoObservavel.addPropertyChangeListener((e)->{
	        	Platform.runLater(()->{
	        		actualiza();
	        	});
	        });
	        
	        
	        actualiza();
	    }
	    
	    void criaLayout() {
	    	DadosNave = new ListView<>();
	     
	    	trocarRecursos = new Button("Trocar Recursos");
	    	trocarRecursos.setPrefWidth(Double.MAX_VALUE);
	    	
	    	fazCombustivel = new Button("Fazer Combustivel");
	    	fazCombustivel.setPrefWidth(Double.MAX_VALUE);
	    	
	    	fazEscudo = new Button("Fazer Escudo");
	    	fazEscudo.setPrefWidth(Double.MAX_VALUE);
	    	
	    	fazAmmo = new Button("Fazer Munição");
	    	fazAmmo.setPrefWidth(Double.MAX_VALUE);
	    	
	    	VBox botoes = new VBox(0);
	    	
	    	botoes.getChildren().addAll(fazAmmo, fazEscudo, fazCombustivel, trocarRecursos);
	    	
	    	
	        this.setCenter(DadosNave);
	        this.setBottom(botoes);
	        
	        trocarRecursos.setVisible(false);	//quandoo jogo comeca nao faz sentido o botão ser mostrado
	        fazCombustivel.setVisible(false);	//quandoo jogo comeca nao faz sentido o botão ser mostrado
	        fazEscudo.setVisible(false);
	        fazAmmo.setVisible(false);
	    }

	    private void registaListeners() {
  
	        
	    	trocarRecursos.setOnMouseClicked((e)->{   		
	    		
	        	EditarRecursos edt = new EditarRecursos(jogoObservavel);		//criar classe que permite criar janela e deixa modificar os dados
	        	edt.initOwner(getScene().getWindow());
	        	edt.initModality(Modality.APPLICATION_MODAL);
	        	edt.showAndWait();
	    		
	        });
	    	
	    	fazCombustivel.setOnMouseClicked((e)->{   		
	    		
	        	jogoObservavel.comprarCombustivel();
	    		
	        });
	    	
	    	fazEscudo.setOnMouseClicked((e)->{   		
	    		
	        	jogoObservavel.comprarEscudo();
	    		
	        });
	    	
	    	fazAmmo.setOnMouseClicked((e)->{   		
	    		
	        	jogoObservavel.comprarAmmo();
	    		
	        });
     
	    }
	    
	    void actualiza() {		//por testar
	    	
	    	if(DadosNave == null)			
	    		return;
	    	
	    	if(jogoObservavel.getMaqEstados().getJogo().getNave() == null)
	    		return;
	    	
	    	trocarRecursos.setVisible(jogoObservavel.getjogoObservavel().getJogo().getNave() == null ? false : true);
	    	fazCombustivel.setVisible(jogoObservavel.getjogoObservavel().getJogo().getNave() == null ? false : true);
	    	fazAmmo.setVisible(jogoObservavel.getjogoObservavel().getJogo().getNave() == null ? false : true);
	    	fazEscudo.setVisible(jogoObservavel.getjogoObservavel().getJogo().getNave() == null ? false : true);

	    	
	    	DadosNave.getItems().clear();
	    	
	        for(String dado : jogoObservavel.getLstDadosNave())	
	        	DadosNave.getItems().add(dado);
	        

	    }
	    
}
