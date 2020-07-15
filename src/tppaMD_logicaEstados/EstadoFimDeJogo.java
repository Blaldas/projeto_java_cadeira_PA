package tppaMD_logicaEstados;

import javafx.application.Platform;
import tppaMD_interface.Constantes;
import tppaMD_logicaDados.*;


public class EstadoFimDeJogo extends EstadoAdapter implements Constantes{

	public EstadoFimDeJogo(Jogo jogo) {
		super(jogo);
	}


	//return true: o jogo acaba
	//return false: vai para o inicio
	@Override
	public IEstado FimOuRecomeco(int i) {
		if(i == 2)
		{
			
			jogo.addLog("FimDeJogo:o jogo vai ser reiniciado");
			return new EstadoSelectVeicle(new Jogo());
		
		}
		jogo.addLogComentarios("FimDeJogo: o jogo vai terminar");
		try {
			Thread.sleep(DESLIGAR);
		} catch (InterruptedException e) {
		}
		
		Platform.exit();
		return null;
	}

	
}
