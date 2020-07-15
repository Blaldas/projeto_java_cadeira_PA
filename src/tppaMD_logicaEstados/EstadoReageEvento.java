package tppaMD_logicaEstados;

import java.util.concurrent.ThreadLocalRandom;

import tppaMD_logica.*;
import tppaMD_logicaDados.*;

public class EstadoReageEvento extends EstadoAdapter{

	
	private int evento;
	private boolean sucesso;
	private String nomeEvento;
	
	
	public EstadoReageEvento(Jogo jogo)
	{
		super(jogo);
	}
	

	
	@Override
	public IEstado executa()
	{
	
		
		int rand = ThreadLocalRandom.current().nextInt(1,7);
		evento = rand;
		
		if(rand == 1)
		{
			CrewElementDeath();	
			setNomeEvento(new String("\nUm tripulante morreu"));

		}
		else if(rand == 2)
		{
			FindShip();
			setNomeEvento(new String("\nEncontrou uma nave abandonada"));


		}
		else if(rand == 3)
		{
			LoseCargo();
			setNomeEvento(new String("\nPerdeu cargo"));


		}
		else if(rand == 4)
		{
			LoseFuel();
			setNomeEvento(new String("Perdeu combustivel"));
	

		}
		else if(rand == 5)
		{
			setNomeEvento(new String("Não aconteceu nada"));
			return this;
			
		}
		else if(rand == 6)
		{
			setNomeEvento(new String("Encontou um oficial perdido"));
			GanharTripulante();		
		
		}
		else
		{
			setNomeEvento(new String("Evento desconhecido"));
		}
		
		//se tiver sobrevivido ao evento
		if(sucesso) {
			jogo.addLogEventos("sobreviveu ao evento: " +nomeEvento);
			return this;
		}
		else	//se não tiver sobrevivido ao evento
		{
			jogo.addLogEventos("não sobreviveu ao evento: " +nomeEvento);
			return new EstadoFimDeJogo(jogo);
		}
		
	}
	
	@Override
	public IEstado AplicaEvento(int rand)
	{
	
		evento = rand;
		
		if(rand == 1)
		{
			CrewElementDeath();
			setNomeEvento(new String("\nUm tripulante morreu"));
			

		}
		else if(rand == 2)
		{
			 FindShip();
			setNomeEvento(new String("\nEncontrou uma nave abandonada"));
			
		}
		else if(rand == 3)
		{
			LoseCargo();
			setNomeEvento(new String("\nPerdeu cargo"));
			
		}
		else if(rand == 4)
		{
			LoseFuel();
			setNomeEvento(new String("Perdeu combustivel"));

		}
		else if(rand == 5)
		{
			sucesso = true;
			setNomeEvento(new String("Não aconteceu nada"));
			
			
		}
		else if(rand == 6)
		{
			setNomeEvento(new String("Encontou um oficial perdido"));
			GanharTripulante();		
			

		}
		else
		{
			setNomeEvento(new String("Evento desconhecido"));
		}
		
		//se tiver sobrevivido ao evento
		if(sucesso) {
			jogo.addLogEventos("sobreviveu ao evento: " +nomeEvento);
			return this;
		}
		else	//se não tiver sobrevivido ao evento
		{
			jogo.addLogEventos("não sobreviveu ao evento: " +nomeEvento);
			return new EstadoFimDeJogo(jogo);
		}
	}
	
	
	//simula o evento de morte de um militar
	public void CrewElementDeath()
	{
		int n = jogo.killRandomOfficial();
		
		jogo.addLog("ReageEvento: oficial com indice" + n + "morreu");
		
		sucesso = true;
		
		if(!jogo.TemOficiaisVivos())
		{
			jogo.addLog("ReageEvento: não existem mais oficiaIS vivos");
			sucesso = false;
		}
		
	}
	
	//simula evento de encontra de nave abandonada
	public void FindShip()
	{
		int recurso = ThreadLocalRandom.current().nextInt(1,5);
		// 1- preto;
		//2- vermelho;
		//3- verde;
		//4- azul;
		
		int quantidade = ThreadLocalRandom.current().nextInt(1,7);
	
		int n= jogo.ModificaRecursos(recurso, quantidade);
		jogo.addLog("ReageEvento: recurso com indice " + recurso + " foi aumentado na quantidade " + quantidade);
		
		sucesso = true;		
		
	}
	
	//simula evento de encontra de nave abandonada
	public void LoseCargo()
	{
		int recurso = ThreadLocalRandom.current().nextInt(1,5);
		// 1- preto;
		//2- vermelho;
		//3- verde;
		//4- azul;
		
		int quantidade = ThreadLocalRandom.current().nextInt(1,4);
		
		quantidade *=-1;
		
		int n= jogo.ModificaRecursos(recurso, quantidade);
		jogo.addLog("ReageEvento: recurso com indice "+recurso+ "foi perdido na quantidade " + quantidade);
		sucesso = true;		

	}
	
	//simula o evento de gasto de fluel
	//diminui o fluel da nave em 1
	//devolve a qauntidaede de fluel restante
	//WARNING: nao verifica se o fluel esta a zero ou é negativo
	public void LoseFuel()
	{
		jogo.addLog("ReageEvento: foi perdido 1 de combustivel");
		if(jogo.GastarFluel(1)<0)
			sucesso = false;
		sucesso = true;
		
	}
	
	
	//simula evento onde ganha um tripulante
	public void GanharTripulante()
	{

		for (int i= 0; i< 6; i++)
		{
			
			if(!jogo.SaberEstadoOficial(i))
				{
				
					jogo.MudaEstadoOficial(i);
					jogo.addLog("ReageEvento: um tripulantte foi encontrado, vai ser colocado no inidce " + i);
					sucesso = true;
					return;
				}
		}
		jogo.addLog("ReageEvento: um tripulantte foi encontrado, porem nao existe lugar para o mesmo");
		sucesso = true;
		
	}
	
	
	
	public int getEvento()
	{
		return evento;
	}

	public boolean getSucesso()
	{
		return sucesso;
	}
	
	
	public String getNomeEvento() {
			return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
}
