package tppaMD_logicaEstados;


import tppaMD_logica.*;
import tppaMD_logicaDados.*;

public class EstadoSelectVeicle extends EstadoAdapter{



	
	
	public EstadoSelectVeicle (Jogo jogo)
	{
		super(jogo);
	}
	
	
	@Override
	public IEstado comecaJogo() {
		
		return new EstadoEsperaMover(jogo);
	}
	

}
