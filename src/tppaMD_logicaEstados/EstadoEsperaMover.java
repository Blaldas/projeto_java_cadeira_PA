package tppaMD_logicaEstados;

import java.util.concurrent.ThreadLocalRandom;

import tppaMD_logica.*;
import tppaMD_logicaDados.Jogo;
import tppaMD_logicaDados.*;

//import java.util.concurrent.ThreadLocalRandom;

public class EstadoEsperaMover extends EstadoAdapter {

	
	
	public EstadoEsperaMover(Jogo jogo)
	{
		super(jogo);
	}
	
	
	
	//												PODE MANTER-SE
	
	//verifica se pode avancar no espa�o
	//verifica se passa por buraco negro
	//verifica se morreu ou se pode continuar (caso passe no buraco negro)
	@Override
	public IEstado AvancaNoEspaco() {
		
		//para saber se vai para planeta ou evento
		Boolean proximo = jogo.vaiProximo();
	
		if(! this.VerificaPodeAvancar())
		{
			//a funcao chamada j� coloca as coisas no LOG
			return this;
		}
		
		int rand = ThreadLocalRandom.current().nextInt(1,9);
		if(rand == 1)
		{
			if(jogo.passaPorWormHole()== true)
			{
				jogo.addLogComentarios("passa por wormhole e sobrevive");
			
			}
			else
			{
				jogo.addLogComentarios("passa por wormhole e nao sobrevive");
				return new EstadoFimDeJogo(jogo);
			}
		}
		else
		{
			jogo.GastarFluel(1);	//combustivel gasto cao nao passe pelo wormhole
			jogo.addLog("n�o passa por wormhole");
		}
		
		;
		if(proximo)							//decide se vai para planeta ou evento
		{
			
			return new EstadoOrbitaPlaneta(jogo);
		
		}
		else
		{
			
			return new EstadoReageEvento(jogo);
		
		}
		
		

	}


	
	//verifica se a nave tem condi��es para avancar
	//return true: pode avancar
	//return false: nao pode avancar
	public boolean VerificaPodeAvancar() {
		
		if(jogo.SaberEstadoOficial(1))
		{	
			if(jogo.getFluel() > 0)
			{
				return true;
			}
			
			jogo.addLogComentarios("n�o tem combustivel");
		}

		jogo.addLogComentarios("n�o tem oficial de navega��o");

		return false;	
		
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
