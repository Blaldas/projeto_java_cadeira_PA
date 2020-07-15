package tppaMD_logica;

import tppaMD_logicaDados.*;
import tppaMD_logicaEstados.*;

import java.io.Serializable;

public class MaqEstados implements Serializable {

	private Jogo jogo;			//dados do jogo
	private IEstado estado;		//estado lógico do jogo
	//private Naves nave;
	
	
	public MaqEstados()
	{
		jogo = new Jogo();
		estado = new EstadoSelectVeicle(jogo);
	}
	
	
	public Jogo getJogo()
	{
		return jogo;
	}
	
	public IEstado getEstado() {
		return estado;
	}
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}
	
	public void AvancaNoEspaco() {
		
		estado = estado.AvancaNoEspaco();
		//proximo = !proximo;

		VerificaSeOJogoContinua();
	}






	public void ReageEvento() {
		estado = estado.executa();

		IrParaEstadoEsperaMover();
		
	}

	public void  ReageEvento(int i) {
		estado = estado.AplicaEvento(i);
		
		//return estado.getNomeEvento();
		
	}

	public void EntrarNoPlaneta() {

		estado = estado.EntrarNoPlaneta();
		estado = estado.reagirNoPlaneta();;
		estado = estado.voltaParaOrbitaPlaneta();

	}
	
	
	//faz as trocas no mercaso
	public void MercadoEstacaoEspacial(int n) {
		if(n == 0)
			estado = estado.voltaParaOrbitaPlaneta();
		else		
			jogo.MercadoSS(n);	//podia devolver boolean diretamente, isto é estupido
		
	}
	
	
	//manter
	//return true: o jogo acaba
	//return false:o jogo vai para o inicio
	public void FimOuRecomeco(int i) {
	
		estado = estado.FimOuRecomeco(i);
		jogo = estado.getJogo();
	}

	//serve para escolher nave no primeiro estado
	public void EscolheNave(int tipo)
	{
		jogo.defineTipoNave(tipo);
		estado = estado.comecaJogo();
	}


	public void IrParaEstadoEsperaMover() {
		estado = estado.comecaJogo();
		
		VerificaSeOJogoContinua();
	}

	
	//verifica se tem o combustivel/ recursos para continuar
	public void VerificaSeOJogoContinua() {
		estado = estado.VerificaSeOJogoContinua();
		
	}

	//coloca no estado da SS
	public void EntraEstacaoEspacial() {
		estado = estado.EntraNoMercadoSS();
		
	}

	//volta a orbita apos sair da SS
	public void VoltarAOrbita() {
		estado = estado.voltaParaOrbitaPlaneta();	
	}


	public void TrocarRecursos(int opt, int r1, int r2) {
	
		jogo.TrocarRecursos(opt, r1, r2);
	}
	
	public boolean comprarCombustivel()
	{
		return jogo.ComprarFluel();
	}



}
