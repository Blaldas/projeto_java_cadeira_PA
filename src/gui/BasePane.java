package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import gui.estados.EscolheNavePane;
import gui.estados.EsperaMoverPane;
import gui.estados.EstacaoEspacialPane;
import gui.estados.FimDeJogoPane;
import gui.estados.OrbitaPlanetaPane;
import gui.estados.ReageEventoPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logicaEstados.EstadoSelectVeicle;

public class BasePane extends BorderPane implements Constantes, PropertyChangeListener {

	private final JogoObservavel observableGame; 
		//os panes dos vários estados
	
	private EscolheNavePane escolheNave;
	private EsperaMoverPane esperaMover;
	private EstacaoEspacialPane ss;
	private FimDeJogoPane fimDeJogo;
	private OrbitaPlanetaPane orbitaPlaneta;
	private ReageEventoPane reageEventoPane;

	private HBox comentario;
	private Text texto;
	
	private int logSize;
	
	public BasePane(JogoObservavel observableGame)
	{
		this.observableGame = observableGame;
		this.observableGame.addPropertyChangeListener(this);		//adicionar funcao
		
		
		CriaListeners();
		CriaLayout();
		
		propertyChange(null);  
	}

	private void CriaListeners() {
		
		//setPrefSize(JANELA_LARGURA, JANELA_ALTURA);
	    //setMinSize(JANELA_MIN_LARGURA, JANELA_MIN_ALTURA);
	    
	    escolheNave = new EscolheNavePane(observableGame);
	    esperaMover= new EsperaMoverPane(observableGame);
	    ss = new EstacaoEspacialPane(observableGame);
	    fimDeJogo= new FimDeJogoPane(observableGame);
	    orbitaPlaneta = new OrbitaPlanetaPane(observableGame);
	    reageEventoPane = new ReageEventoPane(observableGame);
	    
	}
	
	
	private void CriaLayout() {
		logSize =0;

		StackPane centro = new StackPane(escolheNave, esperaMover, orbitaPlaneta, ss, fimDeJogo, reageEventoPane);
		centro.setAlignment(Pos.CENTER);
		
		centro.setPrefSize(JANELA_LARGURA , CENTRO_ALTURA );
		centro.setMinSize(JANELA_LARGURA , CENTRO_ALTURA );
		centro.setMaxSize(JANELA_LARGURA , CENTRO_ALTURA);
		
		this.setCenter(centro);
		
		
		//trata do menu de comentarios em baixo
		comentario = new HBox(10);
		texto = new Text();
		
		
		comentario.setAlignment(Pos.TOP_CENTER);
		comentario.setPrefSize(JANELA_LARGURA, BOTTOM_ALTURA);
		comentario.getChildren().add(texto);
		comentario.setBackground(new Background(new BackgroundFill(Color.rgb(229, 115, 0), CornerRadii.EMPTY, Insets.EMPTY)));		
		
		texto.setTextAlignment(TextAlignment.JUSTIFY);
	 	texto.setText("Bem vindo ao jogo");
	 	texto.setFont(new Font(30));
	 	texto.setFill(Color.WHITE);
		
	 	this.setBottom(comentario);
		
	
	}
	
	 @Override
	    public void propertyChange(PropertyChangeEvent evt) {   
		 if(observableGame.getEstado() instanceof EstadoSelectVeicle || observableGame.getjogoObservavel().getJogo() == null)
			 return;
		 
		 //pausa por 1 segundo para que as mensagens sejam vistas, se houver novas mensagens
		 if(observableGame.getjogoObservavel().getJogo().getLogComentariosSize() != logSize)
		 { 
			 texto.setText(observableGame.getjogoObservavel().getJogo().getLastLogComentarios());
			 logSize = observableGame.getjogoObservavel().getJogo().getLogComentariosSize();
			 JogoObservavel.pausa();
		 }
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
}
