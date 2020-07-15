
package tppaMD_logicaEstados;


import tppaMD_logica.*;
import tppaMD_logicaDados.*;

public class EstadoMercadoNaEstacaoEspacial extends EstadoAdapter{

	boolean sucesso;
	
	
	//construtor da classe
	//recebe os dados na nave e coloca-os dentro de si
	public EstadoMercadoNaEstacaoEspacial(Jogo jogo)
	{
		super(jogo);

	}

	@Override
	public IEstado voltaParaOrbitaPlaneta() 
	{
		return new EstadoOrbitaPlaneta(jogo, 1);
	}
	
}
