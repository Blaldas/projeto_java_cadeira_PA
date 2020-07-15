package tppaMD_interface;

import java.util.Scanner;

import tppaMD_logica.*;
import tppaMD_logicaEstados.*;


public class UItexto {

	  private MaqEstados jogo;
	  private boolean sair = false;
	  
	
	  public UItexto(MaqEstados jogo)
	  {
		  this.jogo = jogo; 
		  
	  }
	  
	  public void corre()
	  {

		  
		  while(jogo.getEstado() != null)
		  {
			   
			  IEstado estado = jogo.getEstado();
			  
			  if(estado instanceof EstadoSelectVeicle)
			  {
				  StateSelecionaVeiculo();
			  }
			  else if( estado instanceof EstadoEsperaMover)
			  {
				  StateEsperaMover();
			  }
			  else if( estado instanceof EstadoReageEvento)
			  {
				  StateReageEvento();
			  }
			  else if( estado instanceof EstadoOrbitaPlaneta)
			  {
				  StateOrbitaPlaneta();
			  }
			  else if( estado instanceof EstadoFimDeJogo)
			  {
				  StateFimDeJogo();
			  }
			  else if( estado instanceof EstadoMercadoNaEstacaoEspacial)
			  {
				  StateMercadoNaEstacaoEspacial();
			  }
			  
			  //verificar se tem combustivel
			  if(!(estado instanceof EstadoFimDeJogo))
				  jogo.VerificaSeOJogoContinua();	//nao precisa verificar se ja estiver no estado final 
		  }  		  
	  }
	  

	  private void StateMercadoNaEstacaoEspacial() {
		  Scanner scan = new Scanner(System.in);
		  int i;
			
			System.out.println("Mercado da estacao espacial:\n1- melhorar cargo hold\n2- HealLandingCraft \n3- ContratarMilitar\n4- UpgradeWeapons\n5- CompraDrone\n0- voltar para a orbita");
			i = scan.nextInt();
		
			
			//jogo.MercadoEstacaoEspacial(i);
			
			System.out.println(jogo.getJogo().getLastLogComprasNaEstacaoEspacial());
		
		
			
		
	}

	//antes de colocar neste estado é necessario garantir que não se pode fazer mais nada no jogo
	  //ou seja, que se ganhou, ou que se perdeu
	private void StateFimDeJogo() {
		int i = jogo.getJogo().ganhououPerdeu();
		
		if(i == -1)
			System.out.println("\nperdeste--------------------------------------");
		else if(i == 1)
			System.out.println("ganhaste--------------------------------------");
	  
		System.out.println("\nDeseja:1- recomecar\n2- sair\n3- ver log completo");
		Scanner scan = new Scanner(System.in);
		i = scan.nextInt();
		if(i == 3)
			System.out.println(jogo.getJogo().mostrarLog());
		jogo.FimOuRecomeco(i);	//funcao q decidade se recomeça ou sai do jogo
	    
	}

	private void StateOrbitaPlaneta() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Planeta:\n\nEscolha opção:\n1- Ignorar Planeta\n2- Ver recursos\n3 -Entrar no planeta");
		if(jogo.getJogo().isSpaceStation())
			System.out.println("4- entrar no mercado da estacao espacial");
	
		int ot = scan.nextInt();
	
		if(ot == 1)
		{
			jogo.IrParaEstadoEsperaMover();
		}
		else if(ot == 2)
		{	
			System.out.println(jogo.getJogo().getPlaneta().toString());
			
		}
		else if(ot == 3)
		{//ver se tem drone
			
			jogo.EntrarNoPlaneta();
			System.out.println(jogo.getJogo().getLastLogApanhaRecurso());
		
		}
		else if(ot == 4 && jogo.getJogo().isSpaceStation())
		{

				jogo.EntraEstacaoEspacial();

		}
	}

	//print apareceui evento
	  //faz evento e print o nome
	  //volta a colocar o estado como esperaMover -> volta ao estado anterior
	private void StateReageEvento() {
	
		System.out.println("\nApareceu um evento\n");
		
		//USAR PARA FAZER DEFESA
		
		Scanner scan = new Scanner(System.in);
		System.out.println("indique id do evento");
		int i = scan.nextInt();
		jogo.ReageEvento(i);
		
		System.out.println(jogo.getJogo().getLastLogEventos());
		
		
		
		//System.out.println(jogo.ReageEvento());
		jogo.IrParaEstadoEsperaMover();
	}

	private void StateEsperaMover() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nEscolha opção:\n1- Avancar no espaço\n2- Usar recursos\n3- Ver dados da nave\n5- ver log");
		int opt = scan.nextInt();
		
		//______________________________________________________________________________________________________________________________________________________________________
		
		if(opt == 1)	//AVANCAR PARA PLANETA/ EVENTO
		{
			
			
			jogo.AvancaNoEspaco();
			
			System.out.println(jogo.getJogo().getLastLog());
			

		}
		else if(opt == 3)	//ver dados da nave
		{
			System.out.println(jogo.getJogo().MostrarDadosNave());
			
		}
		else if(opt == 2)//usar recursos
		{
			int r1 = 0, r2 = 0;
			
			System.out.println("\nEscolha opção:\n1- Transformar recursos em escudo\n2- Transformar recursos em munição\n3 -Transformar recursos em combustivel\n4- Transformar recursos noutros recursos");
			opt = scan.nextInt();
			
			if(opt == 4)
			{
				
				System.out.println("recurso para ser convertido: \n1- preto\r\n" + 
						"	\n2- vermelho\r\n" + 
						"	\n3- verde\r\n" + 
						"	\n4- azul");
				
				r1 = scan.nextInt();
				
				System.out.println("recurso em que vai ser convertido: \n1- preto\r\n" + 
						"	\n2- vermelho\r\n" + 
						"	\n3- verde\r\n" + 
						"	\n4- azul");
				
				r2 = scan.nextInt();
			}
			
			jogo.TrocarRecursos(opt, r1, r2);
			System.out.println(jogo.getJogo().getLastLog());
			
		}
		else if(opt == 5)
		{
			System.out.println(jogo.getJogo().mostrarLog()); 
		}
	}


	
	private void StateSelecionaVeiculo() {
		
		jogo = new MaqEstados();
		
		System.out.println("seleciona veiculo------------");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\nTipo de nave:\n1- Militar\n2- Mineira");
		
		int opt = scan.nextInt();
		
		if(opt == 1 || opt == 2)
		{
			jogo.EscolheNave(opt);

		}
		else 
			return;
		
	}
	  
	  
	  
	   
	   
}
