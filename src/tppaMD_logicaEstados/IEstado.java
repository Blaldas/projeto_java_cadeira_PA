	package tppaMD_logicaEstados;

import java.io.Serializable;

import tppaMD_logicaDados.Jogo;
import tppaMD_logicaDados.Naves;
import tppaMD_logicaDados.Planeta;
import tppaMD_logicaDados.SpaceStation;

public interface IEstado  extends Serializable{
	
	
	public IEstado comecaJogo();
	public IEstado AvancaNoEspaco();	
	public IEstado executa();
	public IEstado AplicaEvento(int rand);
	//public IEstado MercadoSS(int n);
	public IEstado FimOuRecomeco(int i);
	//public IEstado recomecarJogo();
	public IEstado EntrarNoPlaneta();
	public IEstado reagirNoPlaneta();
	public IEstado voltaParaOrbitaPlaneta();
	public IEstado VerificaSeOJogoContinua();
	public IEstado EntraNoMercadoSS();
	//public IEstado TrocarRecursos(int opt, int r1, int r2);
	public Jogo getJogo();


}



