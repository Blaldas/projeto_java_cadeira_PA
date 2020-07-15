package tppaMD_logicaEstados;

import java.util.concurrent.ThreadLocalRandom;

import tppaMD_logica.*;
import tppaMD_logicaDados.*;


public class EstadoOrbitaPlaneta extends EstadoAdapter{



	boolean sucesso;
	
	
	
	//cria o "planeta" e determina se tem ou não se tem spacestation
	//se station = true ->		 tem estacao espcial
	//se station = false -> nao  tem estaçãom espacial
	public EstadoOrbitaPlaneta(Jogo jogo)
	{
		
		super(jogo);		
		jogo.createPlaneta();
		
		int rand = ThreadLocalRandom.current().nextInt(1,11);
		
		if(rand > 7)
		{
			jogo.criaEstacaoEspacial();
			//jogo.addLog("OrbitaPlaneta: planeta criado com estacao espacial");
		}
		else
		{
			jogo.naoTemEstacaoEspacial();
			//jogo.addLog("OrbitaPlaneta: planeta criado sem estacao espacial");
		}
		
	}
	
	//funcao usada para quandos e volta ao mesmo planeta
	public EstadoOrbitaPlaneta(Jogo jogo, int n)
	{
		super(jogo);
	}
	
	
	
	
	@Override
	public String toString()
	{
		String str = new String("");
		
		str += "Estação espacial: ";
		if (true == jogo.boolSpacestation())
			str += "Existe\n";
		else
			str += "Não existe\n";
		
		str += "Planeta:\n";
		str += jogo.toStringPlaneta();
		
		return str;		
	}
	

}
