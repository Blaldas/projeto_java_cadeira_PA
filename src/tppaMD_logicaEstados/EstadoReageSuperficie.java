package tppaMD_logicaEstados;

import java.util.concurrent.ThreadLocalRandom;

import tppaMD_logica.*;
import tppaMD_logicaDados.*;



public class EstadoReageSuperficie extends EstadoAdapter{
	
	
	private Posicao alien;
	private Posicao drone;
	private Posicao recurso;
	private Posicao Inicial;

	//------------------------------__
	
	private int recursoApanhado;
	
	
	public EstadoReageSuperficie ( Jogo jogo)
	{
		super(jogo);
	}
	
	@Override
	public IEstado reagirNoPlaneta()
	{
		if(jogo.getFluel() <= 0)
		{
			jogo.addLogApanhaRecurso("Não tem fuel para descer");
			return this;
		}
		if(!jogo.SaberEstadoOficial(3))
		{
			jogo.addLogApanhaRecurso("Não tem Oficial de pousagem");
			return this;
		}
		if(!jogo.saberSeDroneEstaIntacto())
		{
			jogo.addLogApanhaRecurso("Não tem Drone");
			return this;
		}
		if(jogo.PlanetaDizNumeroDeCoisasDeInteresse() == 0)
		{
			jogo.addLogApanhaRecurso("O planeta não tem mais recursos");
			return this;
		}
	
		int rec = ApanhaRecursos();
		if(rec == -1)
		{
			jogo.addLogApanhaRecurso("o drone morreu, F");
			return this;
		}
		
		jogo.apanhaRecurso(rec);
		
		
		if(rec == 1)
			jogo.addLogApanhaRecurso("recurso preto apanhado");
		else if(rec == 2)
			jogo.addLogApanhaRecurso("recurso vermelho apanhado");
		else if(rec == 3)
			jogo.addLogApanhaRecurso("recurso verde apanhado");
		else if(rec == 4)
			jogo.addLogApanhaRecurso("recurso azul apanhado");
		else if(rec == 5)
			jogo.addLogApanhaRecurso("artefacto apanhado");
		
			
		return this;
	}
	
	
//faz automaticamente toda a simulacao do caminho de ida e volta do drone para apanhar
	//gasta fluel
		//return -1: o drone morreu
		//return [1; 5]: apanhou recurso
		/**
		 *
		 */
		
		public int ApanhaRecursos()
		{
			jogo.addLog("ReageSuperficie: Entrou na superficie, a gerar ambiente");
			
			Spawns();
			jogo.GastarFluel(1);
			
			//caminho para o recurso
			while(!VerificaEstaEmCimaDoRecurso())
			{
				int rand = ThreadLocalRandom.current().nextInt(1,7);
				for(int i=0; i< rand; i++)
				{	
					MovimentaDroneParaOsRecursos();
					if(VerificaEstaEmCimaDoRecurso())
						break;
					
					if(VerificaEstaAoLadoDoAlien())
					{
						//luta
						if(fight() != 1)
						{
							setRecursoApanhado(-1);
							return -1;
						}
					}
				}
				//fazer parar quando estiver em cima do recurso
				rand = ThreadLocalRandom.current().nextInt(1,7);
				for(int i=0; i< rand; i++)
				{
					MovimentaAlienParaODrone();
					//caso esteja ao lado do alien
					if(VerificaEstaAoLadoDoAlien())
					{
						//luta
						if(fight() != 1)
						{
							setRecursoApanhado(-1);
							return -1;
						}
					}
				}
			}
			
			//caso esteja em cima do recurso
			setRecursoApanhado(PegaRecurso());
			
			
			//caminho de volta
			while(!VerificaEstaNaPosicaoInicial())
			{
				int rand = ThreadLocalRandom.current().nextInt(1,7);
				for(int i=0; i< rand; i++)
				{
					MovimentaAlienParaODrone();
					if(VerificaEstaAoLadoDoAlien())
					{
						//luta
						if(fight() != 1)
						{
							setRecursoApanhado(-1);
							return -1;
						}
						break;
					}
				}
				
				rand = ThreadLocalRandom.current().nextInt(1,7);
				for(int i=0; i< rand; i++)
				{
					MovimentaDroneParaPosicaoOriginal();
					
					if(VerificaEstaNaPosicaoInicial())
						break; 
					if(VerificaEstaAoLadoDoAlien())
					{
						//luta
						if(fight() != 1)
						{
							setRecursoApanhado(-1);
							return -1;
						}
					}
				}
				
			}

			return recursoApanhado;
		}
		
		//funcao da luta
		//garante que há balas na ara
		//return 1: o alien foi destuido
		//return 0: o drone foi destruido
		//return -1: nao há balas pelo que o drone foio destruido
		private int fight() {

			jogo.addLog("ReageSuperficie: Luta entre alien e drone:");
			int d;
			if(jogo.planetagetCorID() == 4)		//planeta preto
			{
				do {
				
					d = ThreadLocalRandom.current().nextInt(1,7);
					jogo.addLog("ReageSuperficie: numero do dado: " +d);
					if(d == 1)			//alien ataca
					{
		
						
						int dano = jogo.CausaDanoDrone();
						jogo.addLog("ReageSuperficie: drone sofreu dano");
						
						if(dano==6)	//caso drone n tenha resistencia para continuar
						{	
							jogo.addLog("ReageSuperficie: drone foi destruido com o dano que recebeu");
							return -1;						
						}
					
					}
					else if(d == 5 || d == 6){	//drone pode fazer ataque
						jogo.addLog("ReageSuperficie: drone pode fazer ataque");
						 if(!jogo.FazerAtaque())
						 {
							 jogo.addLog("ReageSuperficie: drone nao tem municao para lutar, pelo que é destruido");
							 jogo.DestroiDrone();
							 return 0;
						 }
						int x;
						int y;
						
						do {	//spawn novo alien
							x = ThreadLocalRandom.current().nextInt(1,7);
							y = ThreadLocalRandom.current().nextInt(1,7);
					
							alien = new Posicao("alien", x, y);
							
						}while((x == drone.getX() && y == drone.getY()) || VerificaEstaAoLadoDoAlien() || (x == recurso.getX() && y == recurso.getY()));
						
						jogo.addLog("ReageSuperficie: alien foi destruido");
						jogo.addLog("ReageSuperficie: novo grupo alien spawnou. Coordenadas (x,y): (" + alien.getX()+ ";" + alien.getY() + ")" );
					}
					
				}while(( d != 5 && d != 6) & jogo.saberSeDroneEstaIntacto());//faz enquanto alien nao morrer e drone nao morrer
			}
			else if(jogo.planetagetCorID() == 2)	//planeta verde
			{
				do {
			
					d = ThreadLocalRandom.current().nextInt(1,7);
					jogo.addLog("ReageSuperficie: numero do dado: " +d);

					if(d == 1 || d==2)	//alien ataca
					{

						int dano = jogo.CausaDanoDrone();
						jogo.addLog("ReageSuperficie: drone sofreu dano");
						
						if(dano==6)		//caso drone n tenha resistencia para continuar
						{
							jogo.addLog("ReageSuperficie: drone foi destruido com o dano que recebeu");
							return -1;
						}
					}
					else if(d >= 4 || d <=6 ){		//drone pode fazer ataque
						jogo.addLog("ReageSuperficie: drone pode fazer ataque");

						if(!jogo.FazerAtaque())		//caso nao haja municao
						 {
							 jogo.addLog("ReageSuperficie: drone nao tem municao para lutar, pelo que é destruido");
							 jogo.DestroiDrone();
							 return 0;
						 }
						 
						int x;
						int y;
						
						do {		//spawna novo alien
							x = ThreadLocalRandom.current().nextInt(1,7);
							y = ThreadLocalRandom.current().nextInt(1,7);
							
							alien = new Posicao("alien", x, y);
							
						}while((x == drone.getX() && y == drone.getY()) || VerificaEstaAoLadoDoAlien() || (x == recurso.getX() && y == recurso.getY()));
						
						jogo.addLog("ReageSuperficie: alien foi destruido");
						jogo.addLog("ReageSuperficie: novo grupo alien spawnou. Coordenadas (x,y): (" + alien.getX()+ ";" + alien.getY() + ")" );
					}
				}while( (d < 4 && d > 6) & jogo.saberSeDroneEstaIntacto());
				
				
			}
			else if(jogo.planetagetCorID() == 1)	//planeta azul
			{
				do {
					d = ThreadLocalRandom.current().nextInt(1,7);
					jogo.addLog("ReageSuperficie: numero do dado: " +d);

					if(d == 3 || d==4 || d==5)	//alien ataca e drone faz tambem ataque-> segundo as regras lmao
					{
						
						jogo.addLog("ReageSuperficie: drone sofreu dano");
						if(jogo.CausaDanoDrone()==6)	//caso drone tenha sido destruido
						{
							jogo.addLog("ReageSuperficie: drone foi destruido com o dano que recebeu");
							return -1;
						}
						
						jogo.addLog("ReageSuperficie: drone pode fazer ataque");
						 if(!jogo.FazerAtaque())		//caso nao tenha municao
						 {
							 jogo.addLog("ReageSuperficie: drone nao tem municao para lutar, pelo que é destruido");
							 jogo.DestroiDrone();
							 return 0;
						 }
						int x;
						int y;
						
						do {
							x = ThreadLocalRandom.current().nextInt(1,7);
							y = ThreadLocalRandom.current().nextInt(1,7);
							
							alien = new Posicao("alien", x, y);
							
						}while((x == drone.getX() && y == drone.getY()) || VerificaEstaAoLadoDoAlien() || (x == recurso.getX() && y == recurso.getY()));
						jogo.addLog("ReageSuperficie: alien foi destruido");
						jogo.addLog("ReageSuperficie: novo grupo alien spawnou. Coordenadas (x,y): (" + alien.getX()+ ";" + alien.getY() + ")" );

					}
				}while( (d != 3 && d != 4 && d != 5) & jogo.saberSeDroneEstaIntacto());
				
				
			}
			else if(jogo.planetagetCorID() == 3)	//planeta vermelho
			{
				do {
						 d = ThreadLocalRandom.current().nextInt(1,7);
						jogo.addLog("ReageSuperficie: numero do dado: " +d);

						if(d == 5 || d == 6)	//alien ataca
						{
						
							jogo.addLog("ReageSuperficie: drone sofreu dano");
							if(jogo.CausaDanoDrone()==6)		//caso drone seja destruido
							{
								jogo.addLog("ReageSuperficie: drone foi destruido com o dano que recebeu");
								return -1;
							}
						}
						else if(d == 1 || d == 2)
						{
						 	jogo.addLog("ReageSuperficie: drone pode fazer ataque");
							 if(!jogo.FazerAtaque())	//caso nao tenha municao
							 {
								 jogo.addLog("ReageSuperficie: drone nao tem municao para lutar, pelo que é destruido");
								 jogo.DestroiDrone();
								 return 0;
							 }
							 
							int x;
							int y;
							
							do {
								x = ThreadLocalRandom.current().nextInt(1,7);
								y = ThreadLocalRandom.current().nextInt(1,7);
			
								alien = new Posicao("alien", x, y);
								
							}while((x == drone.getX() && y == drone.getY()) || VerificaEstaAoLadoDoAlien() || (x == recurso.getX() && y == recurso.getY()));
							
						}
				}while( (d != 1 && d != 2) & jogo.saberSeDroneEstaIntacto());
				jogo.addLog("ReageSuperficie: alien foi destruido");
				jogo.addLog("ReageSuperficie: novo grupo alien spawnou. Coordenadas (x,y): (" + alien.getX()+ ";" + alien.getY() + ")" );

			}
			
			return 1;
		}

		
		//cria as posições de spawn aleatórias
		//garante que todos têm posições diferentes
		public void Spawns ()
		{
			//drone
			int x = ThreadLocalRandom.current().nextInt(1,7);
			int y = ThreadLocalRandom.current().nextInt(1,7);
			
			drone = new Posicao("Drone", x, y);
			Inicial = new Posicao("Inicial", drone);
			
			//alien
			do {
				x = ThreadLocalRandom.current().nextInt(1,7);
				y = ThreadLocalRandom.current().nextInt(1,7);
			
				alien = new Posicao("alien", x, y);
				
			}while((x == drone.getX() && y == drone.getY()) || VerificaEstaAoLadoDoAlien());
			
		
		
			
			//recurso
			do {
			x = ThreadLocalRandom.current().nextInt(1,7);
			y = ThreadLocalRandom.current().nextInt(1,7);
			
			}while((x == drone.getX() && y == drone.getY()) || x == alien.getX() && y == alien.getY());
			recurso = new Posicao("recurso", x, y);
			
			
			jogo.addLog("ReageNaSuperficie: posicao inicial do drone (x, y): " + drone.getX() + ";" + drone.getY());
			jogo.addLog("ReageNaSuperficie: posicao inicial do alien (x, y): " + alien.getX() + ";" + alien.getY());
			jogo.addLog("ReageNaSuperficie: posicao do recurso (x, y): " + recurso.getX() + ";" + recurso.getY());


		}
			
		
		//verifica se o alien e o drone estao em casas adjecentes
		//return true: estao em casas adjecentes
		//returnfalse: não estao em casas adjecentes
		public boolean VerificaEstaAoLadoDoAlien()
		{
			if(alien.getX() == drone.getX())
			{
				if((alien.getY() - drone.getY()) == -1 || (alien.getY() - drone.getY()) == 1)
					return true;
			}
			else if(alien.getY() == drone.getY())
			{
				if((alien.getX() - drone.getX()) == -1 || (alien.getX() - drone.getX()) == 1)
					return true;
			}
			
			return false;
		}
		
		//verifica se o drone está em cima dos recursos (para os popder retirar)
		//return true: está em cima dos recursos
		//returnfalse: não está em cima dos recursos
		public boolean VerificaEstaEmCimaDoRecurso()
		{
			if(recurso.getX() == drone.getX() && recurso.getY() == drone.getY())
				return true;
			return false;
			
		}
		
		//funcao que movimenta o drone para os recursos
		//WARNING: apos esta funcao deve verificar-se se estão na mesma casa
		public void MovimentaDroneParaOsRecursos()
		{
				//se x maior
			if(drone.getX() > recurso.getX())
			{
				drone.setX(drone.getX()-1);
			}
				//se x menor
			else if(drone.getX() < recurso.getX())
			{
				drone.setX(drone.getX()+1);
			}
				//se x igual
			else
			{		//se x igual e y maior
				if( drone.getY() > recurso.getY() )
				{
					drone.setY( drone.getY() - 1 );
				}
					//se x igual e y menor
				else if( drone.getY() < recurso.getY() )
				{		
					drone.setY ( drone.getY() + 1 );
				}
					//se x igual e y igual			<- a funcao não deve ser chamada, uma vez que devera verificar-se se ja estao na mesma posicao cada vez que se chama esta funcao
				else
					;
			}
			jogo.addLog("ReageNaSuperficie: posicao do drone (x, y): " + drone.getX() + ";" + drone.getY());

		}

		//funcao que movimenta o alien para o drone
		//WARNING: apos esta funcao deve verificar-se se estão em casas adjecentes
		public void MovimentaAlienParaODrone() 
		{
			if(alien.getX() > drone.getX())
				alien.setX(alien.getX()-1);
				//se x menor
			else if(alien.getX() < drone.getX())
				alien.setX(alien.getX()+1);
				//se x igual
			else
			{		//se x igual e y maior
				if( alien.getY() > drone.getY() )
					alien.setY( alien.getY() - 1 );
					//se x igual e y menor
				else if( alien.getY() < drone.getY() )
					alien.setY ( alien.getY() + 1 );
			
				jogo.addLog("ReageNaSuperficie: posicao do alien (x, y): " + alien.getX() + ";" + alien.getY());

			}
		}
		
		//pega um recurso existente no planeta aleatorio
		//return a cor do recurso pegado
			//1- preto;
			//2- vermelho;
			//3- verde;
			//4- azul;
			//5- artifact
		//garante que o planeta tem o recurso para tal
		public int PegaRecurso()
		{
			int rand;
		
				do {	
					 rand = ThreadLocalRandom.current().nextInt(1, 6);

				}while(!jogo.planetagetByNum(rand));
		
				jogo.planetaretiraRecurso(rand);
				jogo.addLog("ReageNaSuperficie: recurso encontrado no planeta, inidce de recurso: " + rand);

				return rand;
		
		}


		public void MovimentaDroneParaPosicaoOriginal()
		{
				//se x maior
			if(drone.getX() > Inicial.getX())
			{
				drone.setX(drone.getX()-1);
			}
				//se x menor
			else if(drone.getX() < Inicial.getX())
			{
				drone.setX(drone.getX()+1);
			}
				//se x igual
			else
			{		//se x igual e y maior
				if( drone.getY() > Inicial.getY() )
				{
					drone.setY( drone.getY() - 1 );
				}
					//se x igual e y menor
				else if( drone.getY() < Inicial.getY() )
				{		
					drone.setY ( drone.getY() + 1 );
				}
					//se x igual e y igual			<- a funcao não deve ser chamada, uma vez que devera verificar-se se ja estao na mesma posicao cada vez que se chama esta funcao
				else
					;
			}	
			
			jogo.addLog("ReageNaSuperficie: posicao do drone (x, y): " + drone.getX() + ";" + drone.getY());

		}

		public boolean VerificaEstaNaPosicaoInicial()
		{
			if(Inicial.getX() == drone.getX() && Inicial.getY() == drone.getY())
				return true;
			return false;
			
		}

		public int getRecursoApanhado() {
			return recursoApanhado;
		}

		public void setRecursoApanhado(int recursoApanhado) {
			this.recursoApanhado = recursoApanhado;
		}

		@Override
		public IEstado voltaParaOrbitaPlaneta() 
		{
			return new EstadoOrbitaPlaneta(jogo, 1);
		}
}
