package gui.estados;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tppaMD_interface.Constantes;
import tppaMD_interface.JogoObservavel;
import tppaMD_logica.MaqEstados;

public class MostrarRecursos extends Stage implements Constantes{
	
	JogoObservavel jogoObservavel;
	
	public MostrarRecursos (JogoObservavel jogoObservavel) {
		
		this.jogoObservavel = jogoObservavel;
		
		criaLayout();

	}

	private void criaLayout() {
		MaqEstados mq = jogoObservavel.getMaqEstados();
		
		String azul = Integer.toString(mq.getJogo().getPlaneta().getRecursoAzul());
		String preto = Integer.toString(mq.getJogo().getPlaneta().getRecursoPreto()); 
		String vermelho = Integer.toString(mq.getJogo().getPlaneta().getRecursoVermelho());
		String verde = Integer.toString(mq.getJogo().getPlaneta().getRecursoVerde());
		String artefacto = Integer.toString(mq.getJogo().getPlaneta().getArtefacto());
		String cor = mq.getJogo().getPlaneta().getCor();
		
		
		
		
		Node tab[][]= {
			{ new TextField("Cor"), 	new TextField(cor)		},
			{ new TextField("Azul"), 	new TextField(azul)		},
			{ new TextField("Preto "), new TextField(preto) 	},
			{ new TextField("Vermelho"), new TextField(vermelho)},
			{ new TextField("Verde"), new TextField(verde)		},
			{ new TextField("Artefacto"), new TextField(artefacto)		}
		};
		
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(5);
		
		for(int i =0; i<tab.length; i++)
			for(int n = 0; n < tab[i].length; n++)
			{
				GridPane.setColumnIndex(tab[i][n], n);
				GridPane.setRowIndex(tab[i][n], i);
				grid.getChildren().add(tab[i][n]);
			}
			
		ColumnConstraints cc = new ColumnConstraints(50, 100, 150);
		cc.setHalignment(HPos.CENTER);
		grid.getColumnConstraints().addAll(cc,cc,cc);
		
		
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(grid);
		
		
		Scene scene = new Scene(root, JANELA_TROCA_LARGURA, JANELA_TROCA_ALTURA);
		this.setScene(scene);
	
	}
}
