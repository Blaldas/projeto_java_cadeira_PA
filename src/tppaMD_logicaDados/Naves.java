package tppaMD_logicaDados;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;
import java.util.List;



//Não se usou herança para criar as naves militar/mineira um vez que não existe qualquer diferenca entre estas à excepção dos valores estatisticos das mesmas
public class Naves implements Serializable{

	
	
	String tipo;		//usado para identificar se a nave é militar ou mineira
	
	LandingCraft landcraft;
	
		//valores de dados ---> permitem fazer as cosias
	private Officer[] Militares;	//indica o dados dos militares para determinar quais estão disponiveis;
	private int escudo;			//indica o escudo
	private ArrayList<Integer> armas;			//indica as armas	
	private int fluel;			//indica o combustivel
	private Recursos cargo;			//indica a carga q leva;
	
	private int artfact = 0;
	
		//valores maximos das stats no momeno--> valores de dados nao podem superar estes;
	
	@SuppressWarnings("unused")
	private int MaxEscudo;		
	@SuppressWarnings("unused")
	private int MaxArmas;
	@SuppressWarnings("unused")
	private int MaxFluel;
	@SuppressWarnings("unused")
	private Recursos MaxCargo;		//unico valor modificavel;
	
	//inicializa os stats das naves
	//recebe o tipo de nave e coloca os devidos stats
	//tipo-> Militar//Mineira
	public Naves(String tipo)
	{
		System.out.println("criou nave");
		this.tipo = new String(tipo);
		
	
		if(tipo.compareTo("Mineira") == 0)	//atenção ao capslock quando for para criar as naves
		{
			
			Militares = new Officer[] {new Officer("Capitão"), new Officer("Navegação"), new Officer("Pousagem") , new Officer("Escudos"), new Officer("Armas"), new Officer("Carga")};
			
			MaxEscudo = 18;
			escudo = 18;
			armas = new ArrayList<Integer>();
			armas.add(9);
			MaxArmas = 9;
			fluel = 2;
			MaxFluel = 53;
			cargo = new Recursos(3,0,0,0);
			MaxCargo = new Recursos(4,4,4,4);
			
			landcraft = new LandingCraft(); 
			
		}
		
		if(tipo.compareTo("Militar") == 0)	//atenção ao capslock quando for para criar as naves
		{
			Militares = new Officer[] {new Officer("Capitão"), new Officer("Navegação"), new Officer("Pousagem") , new Officer("Escudos"), new Officer("Armas"), new Officer("Carga")};
	
			MaxEscudo = 9;
			escudo = 9;
			armas = new ArrayList<Integer>();
			armas.add(9);
			armas.add(9);
			MaxArmas = 9;
			fluel = 35;
			MaxFluel = 35;
			cargo = new Recursos(0,0,0,0);
			MaxCargo = new Recursos(2,2,2,2);
			
			landcraft = new LandingCraft(); 
			
		}
		
	}
	
	//recebe index da arma para atacar-> se for nave mineira index é sempre 0, se não é 0 ou 1
	//verifica se a arma selecionada tem munição
	//dimninui as stats necesarias caso tenha
	//devolve true se tiver atacado
	//devolve false se nao tiver mnunição
	public boolean FazerAtaque(int arma)	//diz que arma é, valor 0 ou 1-> para usar no array
	{
		if(armas.get(arma) > 0 && Militares[4].isVivo())
			arma--;
		else
			return false;
		return true;
	}
	
	//deve ser chamado quando se usa o escudo
	//verifica se tem municao de escudo e militar de escudo
	public boolean UsarEscudo()
	{
		if(escudo > 0 && Militares[4].isVivo())
			escudo--;
		else 
			return false;
		return true;
	}

	//funcao para aumentar max cargo em spacestation
	//warning:: deve garantir-se que se esta numa space station
	//a funcao garante que existe o oficial de carga e que há recursos
	public boolean UpgradeMaxCargo() {
 		if(Militares[5].isVivo() && cargo.getAzul() > 0 && cargo.getPreto() > 0 &&cargo.getVerde() > 0 && cargo.getVermelho() > 0)
		{
			MaxCargo.UpgradeCargo();
			cargo.DiminuiTodosRecursos(1);			
		}
		else 
			return false;
		return true;
					
	}
	
	
	
	//funcao quie serve para "curar" landingcraft
	//a funcao garante que existem reursos para tal e que a armor da landingcraft nao esta no maximo
	//waring:: so se pode chamar esta funcao quando se esta numa estacao espacial
	public boolean HealLandingCraft()
	{
		if(cargo.getAzul() > 0 && cargo.getPreto() > 0 &&cargo.getVerde() > 0 && cargo.getVermelho() > 0)
		{
			if(landcraft.getDamage() != 0)
			{
				cargo.DiminuiTodosRecursos(1);			
				landcraft.ServiceLandingCraft();
				return true;
			}
		}
		return false;
	}
	

	
	//converter um recurso noutro
	//1- preto;
	//2- vermelho;
	//3- verde;
	//4- azul;
	//diminui r1, aumenta r2
	//garante que Existem recursos suficientes no recurso que vai perder
	//garante que o recurso que vai ganhar não está ao maximo
	//garante que o oficial de carga esta vivo
	//return false: não há recursos, recurso ao maximo, oficial morto
	//return true: convertido com sucesso
	public boolean ConverterRecurso(int r1, int r2)
	{
		
		
		if(r1 == 1)
			if(cargo.getPreto() < 1)
				return false;
		if(r1 == 2)
			if(cargo.getVermelho() < 1)
				return false;	
		if(r1 == 3)
			if(cargo.getVerde() < 1)
				return false;
		if(r1 == 4)
			if(cargo.getAzul() < 1)
				return false;
		
		if(r2 == 1)
			if(cargo.getPreto() == MaxCargo.getPreto())
				return false;
		if(r2 == 2)
			if(cargo.getVermelho() == MaxCargo.getVermelho())
				return false;	
		if(r2 == 3)
			if(cargo.getVerde() == MaxCargo.getVerde())
				return false;
		if(r2 == 4)
			if(cargo.getAzul() == MaxCargo.getAzul())
				return false;
		
		if(!Militares[5].isVivo())
			return false;
			
		cargo.ConverteRecurso(r1, r2);
		return true;
		
		
	}
	
	
	
	//contratar um oficial morto por 1 de cada recurso
	//a funcao garante que existem recursos para tal
	//waring:: so se pode chamar esta funcao quando se esta numa estacao espacial
	//return true se tiver contartado//devolve false se nao houver recursos/estiver vivo
	public boolean ContrartarOficial()
	{
		if(cargo.getAzul() > 0 && cargo.getPreto() > 0 && cargo.getVerde() > 0 && cargo.getVermelho() > 0 )
		{
			for(int n =5; n>0; n--)
			{
				if(!Militares[n].isVivo())
				{
					Militares[n].setVivo(true);
					cargo.DiminuiTodosRecursos(1);			
					return true;
				}	
			}
		}
		return false;
		
	}
	
	
	
	//melhorar o sistema de armas por 2 de cada recuso
	//return false se: ja tiver upgrade, não for do tipo militar ou não tiver recursos
	//return true: fez o upgrade e diminuiu os recursos necessarios
	public boolean UpgradeWeapons()
	{
		if(tipo == "Militar" &&  cargo.getAzul() > 1 && cargo.getPreto() > 1 &&cargo.getVerde() > 1 && cargo.getVermelho() > 1 && MaxArmas != 18)
		{
			MaxArmas = 18;
			cargo.DiminuiTodosRecursos(2);			
			return true;
		}
		return false;
	}
	
	
	
	//comprar um novo drone de mineracao por 3 de cada recurso
	
	//troca os recursos por 1 de escudo
	//a funcao garante que existem recursos para tal
	//se funcioar devolve true//devolve false se não houver recusos ouo escudo ja estiver no maximo
	public boolean ComprarEscudo()
	{
		if(cargo.getAzul() > 0 && cargo.getVerde() > 0 && cargo.getPreto() > 0 && escudo < MaxEscudo )
		{
			cargo.usaAzul(1);
			cargo.UsaVerde(1);
			cargo.UsaPreto(1);
			escudo++;
			return true;
		}
		return false;
	}

	
	//troca recursos por 1 de ammo
	//a funcao garante que existem recursos para tal
	//se funcionar devolve true// devolve false se: nao houver recusos ou o ammo ja estiver no maximo\aa
	public boolean ComprarAmmo()
	{
		if(cargo.getAzul() > 0 && cargo.getPreto() > 0 )
		{
			//caso seja militar
			if(tipo == "Militar" )
			{
				if(armas.get(0) < MaxArmas)
				{
					cargo.usaAzul(1);
					cargo.UsaPreto(1);
					armas.set(0, armas.get(0) + 1 );
					return true;
				}
				if(armas.get(1) < MaxArmas)
				{
					cargo.usaAzul(1);
					cargo.UsaPreto(1);
					armas.set(0, armas.get(0) + 1 );
					return true;
				}			
				return false;
			}
			
			else
			{
				if(armas.get(0) < MaxArmas)
				{
					cargo.usaAzul(1);
					cargo.UsaPreto(1);
					armas.set(0, armas.get(0) + 1 );
					return true;
				}
				return false;
				
			}
			
		}
		
		return false;
	}
	
	//troca recursos por 1 de fluel
	//a funcao garante que existem recursos para tal
	//se funcionar devolve true// devolve false se: nao houver recusos ou o fluel ja estiver no maximo\aa
	public boolean ComprarFluel()
	{
		if(fluel == MaxFluel)
			return false;
		if(cargo.getPreto() > 0 && cargo.getVerde() > 0 && cargo.getVermelho() > 0)
		{
			cargo.UsaPreto(1);
			cargo.UsaVerde(1);
			cargo.UsaVermelho(1);
			fluel++;
			return true;
		}
		else 
			return false;
	}
	
	
	//esta funcao verifica se é gameover ou não
	//retorna 1 se ganhar o jogo
	//retorna -1 se perder o jogo
	//retorna 0 se não for o fim do jogo
	//return 2 se nao tiver combustivel mas tiver recursos
	public int VerificaFimdeJogo()
	{
		if(artfact == 5)	//verifica se tem os artefactos
		{
			return 1;
		}
		if(fluel == 0)		//se nao tiver combustivel ve se pode transformar recursos em combustivel
		{	if(Militares[1].isVivo())
			{
				if(cargo.getPreto() > 0 && cargo.getVerde() > 0 && cargo.getVermelho() > 0)
					return 2;
				else 
					return -1;
			}
		}
		//se não tiver militares
		if(!Militares[0].isVivo())
			return -1;

		return 0;
	}
	
	
	//necessita legenda? lmao
	@Override
	public String toString()
	{
		String str= new String("A tua nave:\n");
		str+= "Tipo: " + tipo + "\n";
		
		str+= "Oficiais:\n";
		for(int i=0; i<6; i++)
		{
			str += Militares[i].toString() + "\n";
		}
		
		str += "drone:\n" +landcraft.toString() + "\n";
		
		str += "Escudo: " + escudo + " / " + MaxEscudo + "\n";
		
		if(tipo.compareTo("Militar") == 0)
		{
			str += "Armas:\n";
			str += "Canhão 1: " + armas.get(0) + " / " + MaxArmas + "\n"; 
			str += "Canhão 2: " + armas.get(1) + " / " + MaxArmas + "\n";		
		}
		else
		{
			str += "Armas:\n";
			str += "Canhão: " + armas.get(0) + "\n"; 
		}
		
		str += "Carga:\n";
		str += "Recurso Vermelho: " + cargo.getVermelho() + " / " + MaxCargo.getVermelho() + "\n";
		str += "Recurso Verde: " + cargo.getVerde() + " / " + MaxCargo.getVerde() + "\n";
		str += "Recurso Azul: " + cargo.getAzul() + " / " + MaxCargo.getAzul() + "\n";
		str += "Recurso Preto: " + cargo.getPreto() + " / " + MaxCargo.getPreto() + "\n";
		
		str += "Combustivel: " + fluel + " / " + MaxFluel + "\n";
		str += "Número de artefactos encontrados: " + artfact + "\n";
		
		return str;
	}
	
	
	//mata um militar random que esteja vivo  <- UPDATE: mata o militar com o maior inidice possivbel (e que anters estivesse vivo)
	//retorna o indice do militar mortono array
	public int killRandomOfficial()
	{
		int n;
		
		for(n = 5; n >= 0; n--)
			if(Militares[n].isVivo())
			{
				Militares[n].setVivo(false);
				break;
			}
		
		return n;
	}
	
	//indica se existem oficiais vivos
	//retorna true se existir pelo menos um oficial vivo
	//retoiorna false se não houver oficiais vivos
	public boolean TemOficiaisVivos()
	{
		for(int i=0; i<6; i++)
			if(Militares[i].isVivo())
				return true;
		return false;
	}
	
	
	
	//Esta funco serve para m,odar a qauntidade de recursos que a nave tem
	//envia-se o numero do recurso:
			//1- preto;
			//2- vermelho;
			//3- verde;
			//4- azul;
	//e a quantidade
	//a funcao pode adicionar ou retirar recusos
	//a funcao garante que a quantidade de recursos não supera a quantidade maxima e que tem quanridade suficiente para a operacao
	//se o numero do recurso for invalido, entao return -1
	//se a quantidade do existente for insuficiente (caso a quantidade seja negativa) retorna -2
	//se a quantidade for maior que zero estiver bem, retorna o numero de carga adicionada
	//se a quantidade for menor que zero estiver bem, retorna o numero de carga diminuida
	public int ModificaRecursos(int recurso, int quantidade)
	{
		
		//1- preto;
		//2- vermelho;
		//3- verde;
		//4- azul;
		if(recurso == 1)
		{
			int n= cargo.getPreto();
			cargo.setPreto(cargo.getPreto() + quantidade);
				
			if(cargo.getPreto() > MaxCargo.getPreto())
				cargo.setPreto(MaxCargo.getPreto());
				
			if(cargo.getPreto() < 0)
			{
				cargo.setPreto(n);
				return -2;
			}
			
			if(quantidade > 0)
				return cargo.getPreto() - n;
			return n - cargo.getPreto();
		}
		else if (recurso == 2)
		{
			int n= cargo.getVermelho();
			
			cargo.setVermelho(cargo.getVermelho() + quantidade);
			
			if(cargo.getVermelho() > MaxCargo.getVermelho())
				cargo.setVermelho(MaxCargo.getVermelho());
			
			if(cargo.getVermelho() < 0)
			{
				cargo.setVermelho(n);
				return -2;
			}
			
			if(quantidade > 0)
				return cargo.getVermelho() - n;
			return n - cargo.getVermelho() ;
			
		}
		else if (recurso == 3)
		{
			int n= cargo.getVerde();
			
			cargo.setVerde(cargo.getVerde() + quantidade);	

			if(cargo.getVerde() > MaxCargo.getVerde())
				cargo.setVerde(MaxCargo.getVerde());
			
			if(cargo.getVerde() < 0)
			{
				cargo.setVerde(n);
				return -2;
			}
			
			if(quantidade > 0)
				return cargo.getVerde() - n;
			return n - cargo.getVerde();

		}
		else if (recurso == 4)
		{
			int n= cargo.getAzul();
			
			cargo.setAzul(cargo.getAzul() + quantidade);
				

			if(cargo.getAzul() > MaxCargo.getAzul())
				cargo.setAzul(MaxCargo.getAzul());
				
			if(cargo.getAzul() < 0)
			{
				cargo.setAzul(n);
				return -2;
			}
			
			if(quantidade > 0)
				return cargo.getAzul() - n;
			return n - cargo.getAzul();

		}
		
		else return -1;
		
	}
	
	//nao verifica os valores q gastou, logo pode ficar com fluel < 0
	//gasta a quantidade de fuel indicada em n
	//devolve a quantidade de fluel restante
	public int GastarFluel(int n)
	{
		fluel -= n;
		return fluel;
	}
	
	
	//muda um militar de vivo para morto
	//se o militar estiver morto e reviver retorna false
	//se o militar estiver vivo e o matar retorna true
	public boolean MudaEstadoOficial(int n)
	{
		
		if(Militares[n].isVivo())
		{
			Militares[n].setVivo(false);
			return true;
		}
		else {
			Militares[n].setVivo(true);
			return false;
		}
		
	}
	
	//indica se o militar esta vivo ou morto
	//retorna true se o militar com indice n estiver vivo
	//retorna false se o militar com indice n estiver morto
	public boolean SaberEstadoOficial(int n)
	{
		
		return Militares[n].isVivo();
			
	}
	
	
	public boolean CompraDrone()
	{
		
		if(!landcraft.isIntacta() && cargo.getAzul() > 3 && cargo.getPreto() > 3 && cargo.getVerde() > 3 && cargo.getVermelho() > 3 )
		{
			landcraft.setIntacta(true);
			landcraft.setDamage(0);
			return true;
		
		}
		
		return false;
	}
	
	
	
	public Recursos getCargo()
	{
		return cargo;
	}
	
	public int getFluel()
	{
		return fluel;
	}

	//se o dano total for de 6 coloca como nao intacto
	//return dano total do drone
	public int CausaDanoDrone() {
		
		landcraft.setDamage(landcraft.getDamage() + 1);
		if(landcraft.getDamage() == 6)
			landcraft.setIntacta(false);
		
		return landcraft.getDamage();
	}

	//inidca se tem municao
	//return true: tem municao
	//return false: não tem municao
	public boolean TemMunicao() {
		if(tipo == "Militar")
		{
			if(armas.get(0) > 0)
				return true;
			if(armas.get(1) > 0)
				return true;
			return false;
		}
		else
		{
			if(armas.get(0) > 0)
				return true;
			return false;
		}
		
	}

	public void DestroiDrone() {
		landcraft.setDamage(6);
		landcraft.setIntacta(false);
		
	}	
	

	//recebe index da arma para atacar-> se for nave mineira index é sempre 0, se não é 0 ou 1
	//verifica se a arma selecionada tem munição
	//dimninui as stats necesarias caso tenha
	//devolve true se tiver atacado
	//devolve false se nao tiver mnunição
	public boolean FazerAtaque()	//diz que arma é, valor 0 ou 1-> para usar no array
	{
		if(armas.get(0) > 0 && Militares[4].isVivo())
			armas.set(0, armas.get(0)-1);
		else if(armas.get(1) > 0 && Militares[4].isVivo())
			armas.set(1, armas.get(0)-1);
		else
			return false;
		return true;
	}

	//simula colocar o recurso na nave depois de o ir buscar ao planeta
	//1- preto;
	//2- vermelho;
	//3- verde;
	//4- azul;
	//5- artifact
	//return 1: apanhou recurso/artefacto com sucesso
	//return 0: não tinha espaço para colocar mais recursos
	//return -1: fim do jogo
	public int apanhaRecurso(int rand) {
		
		if(rand != 5)
		{
			int a = ModificaRecursos(rand, 1);		
			if( a > 0)
				return 1;
			else
				return 0;
		}
		
		else
			artfact++;
		if(artfact == 5)
			return -1;
		return 1;
	}

	//retorna o drone
	public LandingCraft getDrone() {
		return landcraft;
	}

	public int getEscudo()
	{
		return escudo;
	}


	public int getArtefacts() {
		return artfact;
	}

	public boolean passaPorWormHole() {

		//se tiver vivo
				if(SaberEstadoOficial(3))
				{
					if(getEscudo() >1 )	//se tiver escudo suficiente
					{
					//	jogo.addLog("wormhole: gasta 2 de escudo e 3 de combustivel");

						GastarFluel(3);
						UsarEscudo();
						UsarEscudo();
					}
					else {		//se nao tiver escudo mas tiver officer
						//addLog("wormhole: gasta 3 de combustivel e morre um oficial");

						GastarFluel(3);
						killRandomOfficial();
						
					}
				}
				else {	//se nao tiver oficial
					if(getEscudo() >3 )	//se tiver escudo suficiente
					{
						//addLog("wormhole: gasta 4 combustivel e 4 escudo");

						GastarFluel(4);
						UsarEscudo();
						UsarEscudo();
						UsarEscudo();
						UsarEscudo();
					}
					else
						return false;			//se nao tiver escudos 
				}
				if(getFluel() < 0)		//se nao tiver combustivel suficiente retorna falso
				{
					//jogo.addLog("wormhole: não tinha o combustivel requesitado");
					return false;		
				}
				if(!SaberEstadoOficial(0))	//se nao houver mais oficiais vivos
				{
					//jogo.addLog("wormhole: não tem oficiais vivos");
					return false;
				}
				return true;
	}

	public boolean TrocarRecursos(int opt, int r1, int r2) {
		if (opt == 1) 
		{
			return ComprarEscudo();
		}
		else if (opt == 2)
			return ComprarAmmo();
		else if (opt == 3)
			return ComprarFluel();
		else if (opt == 4)
			return ConverterRecurso(r1, r2);
		else return false;
								

	}

	public List<String> getLstDados() {

		List<String> str= new ArrayList<>();
		
		str.add("Tipo:\t"+tipo);
		
		str.add(" ");
		
		if(Militares[0].isVivo())
			str.add("Capitão:\t\t\tVivo");
		else
			str.add("Capitão:\t\t\tMorto");

		
		if(Militares[1].isVivo())
			str.add("Oficer Navegação:\tVivo");
		else
			str.add("Oficer Navegação:\tMorto");
		

		if(Militares[2].isVivo())
			str.add("Oficer Pousagem:\tVivo");
		else
			str.add("Oficer Pousagem:\tMorto");
		
		
		if(Militares[3].isVivo())
			str.add("Oficer Escudos:\tVivo");
		else
			str.add("Oficer Escudos:\tMorto");
		

		if(Militares[4].isVivo())
			str.add("Oficer Armas:\t\tVivo");
		else
			str.add("Oficer Armas:\t\tMorto");
		
		if(Militares[5].isVivo())
			str.add("Oficer Carga:\t\tVivo");
		else
			str.add("Oficer Carga:\t\tMorto");
		
		str.add(" ");
		
		if(landcraft.isIntacta()) {
			str.add("Drone:\t\t\tIntacto");
			str.add("Dano:\t\t\t"+ landcraft.getDamage());
		}
		else
			str.add("Drone:\t\t\tDestruido");
		
		str.add(" ");
		
		str.add("Escudo:\t\t\t"+ getEscudo() + " / "+ MaxEscudo);
		
		if(tipo.compareTo("Militar") == 0)
		{
			
			str.add("Canhão 1:\t\t" + armas.get(0) + " / " + MaxArmas); 
			str.add("Canhão 2:\t\t" + armas.get(1) + " / " + MaxArmas);		
		}
		else
		{
			str.add("Canhão:\t\t\t" + armas.get(0)); 
		}
		
		str.add(" ");
		
		str.add("Recurso Vermelho:\t" + cargo.getVermelho() + " / " + MaxCargo.getVermelho());
		str.add("Recurso Verde:\t" + cargo.getVerde() + " / " + MaxCargo.getVerde());
		str.add("Recurso Azul:\t\t" + cargo.getAzul() + " / " + MaxCargo.getAzul());
		str.add("Recurso Preto:\t\t" + cargo.getPreto() + " / " + MaxCargo.getPreto());
		
		str.add("Artefactos:\t\t" + artfact);
		
		str.add(" ");
		
		str.add("Combustivel:\t\t" + fluel + " / " + MaxFluel);
		return str;
	}

	
	public int getRecursoAzul() {
		return cargo.getAzul();
	}
	public int getRecursoVerde() {
		return cargo.getVerde();
	}
	public int getRecursoVermelho() {
		return cargo.getVermelho();
	}
	public int getRecursoPreto() {
		return cargo.getPreto();
	}
	
	
	
	
	
}

//LOG: apanhaRecurso
