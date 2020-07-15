package tppaMD_logicaDados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import tppaMD_logica.*;
import tppaMD_logicaEstados.EstadoOrbitaPlaneta;

public class Jogo implements Serializable{

	private Planeta planeta;
	private Boolean proximo;	//define se é planeta ou SS
	
	private Naves nave;
	private SpaceStation ss;
	private boolean SpaceStation;
	
	private List<String> msgLog = new ArrayList<>();;	//log de mensagens
	private List<String> LogEventos = new ArrayList<>();;	//log de mensagens
	private List<String> LogComprasEstacaoEspacial = new ArrayList<>();;	//log de vompras na estacao espacial
	private List<String> LogApanharRecurso = new ArrayList<>();;	//log de recurso apanhado
	private List<String> logComentarios = new ArrayList<>();
	
	
	//
	public Jogo()
	{
		setNave(null);
		setPlaneta(null);
		setSs(null);
		
		proximo = true;
	}
	

	public String getLastLogComentarios() {
		
		return logComentarios.get(logComentarios.size()-1);
	}

	public int getLogComentariosSize() {
		
		return logComentarios.size();
	}
	
	public void addLogComentarios(String s) {
		logComentarios.add(s);
		msgLog.add(s);
	}
	
	
	
	
	public String getLastLog() {
		return msgLog.get(msgLog.size()-1);
	}

	public void addLog(String s)
	{
		msgLog.add(s);
	}

	
	public String getLastLogEventos() {
		return LogEventos.get(LogEventos.size()-1);
	}
	
	public void addLogEventos(String s)
	{
		LogEventos.add(s);
		logComentarios.add(s);
		msgLog.add(s);
	}
	
		
	public String getLastLogComprasNaEstacaoEspacial() {
		return LogComprasEstacaoEspacial.get(LogComprasEstacaoEspacial.size()-1);
	}

	public void addLogComprasNaEstacaoEspacial(String s)
	{
		LogComprasEstacaoEspacial.add(s);
		logComentarios.add(s);
		msgLog.add(s);
	}
	

	public String getLastLogApanhaRecurso() {
		return LogApanharRecurso.get(LogApanharRecurso.size()-1);
	}

	public void addLogApanhaRecurso(String s)
	{
		LogApanharRecurso.add(s);
		logComentarios.add(s);
		msgLog.add(s);
	}

	
	public boolean boolSpacestation()
	{
		return SpaceStation;
	}
	
	public Planeta getPlaneta() {
		return planeta;
	}

	public void setPlaneta(Planeta planeta) {
		this.planeta = planeta;
	}

	public Naves getNave() {
		return nave;
	}

	public void setNave(Naves nave) {
		this.nave = nave;
	}

	public SpaceStation getSs() {
		return ss;
	}

	public void setSs(SpaceStation ss) {
		this.ss = ss;
	}
	
	
	//recebe um identificador der nave e controi-a baseado nisso
	public void defineTipoNave(int tipo)
	{
		if(tipo == 1)	//nave militar
		{
			addLogComentarios("Tipo de nave escolhido: Militar");
			nave = new Naves("Militar");			
		}
		else if(tipo == 2)  //nave mineira
		{
			addLogComentarios("Tipo de nave escolhido: Mineira");
			nave = new Naves("Mineira");
		}
		else
			addLog("\nerro a enviar o tipo de nave: jogo.java: funco defineTipoNave em jogo.java");
	}

	public int getFluel() {

		return nave.getFluel();
	}

	//nao verifica se o combustivel é insuficiente ou não
	public int GastarFluel(int i) {
		return nave.GastarFluel(i);
		
	}

	
	public boolean SaberEstadoOficial(int i) {
		
		return nave.SaberEstadoOficial(i);
	}

	public int getEscudo() {

		return nave.getEscudo();
	}

	public void UsarEscudo() {
		nave.UsarEscudo();
	}

	public int killRandomOfficial() {
		int i= nave.killRandomOfficial();
		return i;
	}

	public boolean TemOficiaisVivos() {
		return nave.TemOficiaisVivos();
	}

	public int ModificaRecursos(int recurso, int quantidade) {
		return nave.ModificaRecursos(recurso, quantidade);
	}

	public void MudaEstadoOficial(int i) {
		nave.MudaEstadoOficial(i);
		
	}

	public int ganhououPerdeu() {
		return nave.VerificaFimdeJogo();
	}

	public boolean isSpaceStation() {
		return SpaceStation;
	}

	public void setSpaceStation(boolean spaceStation) {
		SpaceStation = spaceStation;
	}

	public void createPlaneta() {
		planeta = new Planeta();
		
	}

	public void criaEstacaoEspacial() {
		SpaceStation = true;
		ss = new SpaceStation();
		
		
	}

	public void naoTemEstacaoEspacial() {
		SpaceStation = false;
		ss = null;		

	}

	public String toStringPlaneta() {

		return planeta.toString();
	}

	public boolean SSisCargoUpgrade() {
		return ss.isCargoUpgrade();
	}

	public boolean UpgradeMaxCargo() {
		return nave.UpgradeMaxCargo();
	}

	public void SSsetCargoUpgrade(boolean b) {
		ss.setCargoUpgrade(b);
		
	}

	public boolean ssisHealLandingCraft() {
		return ss.isHealLandingCraft();
	}

	public boolean HealLandingCraft() {
		if(ssisHealLandingCraft())
		{
			if(nave.HealLandingCraft())
			{
				sssetHealLandingCraft(false);
				return true;
			}
			
		}
		return false;

	}

	public void sssetHealLandingCraft(boolean b) {
		ss.setHealLandingCraft(b);
		
	}

	public boolean ContrartarOficial() {
		return nave.ContrartarOficial();
	}

	public boolean UpgradeWeapons() {
		return nave.UpgradeWeapons();
	}

	public boolean CompraDrone() {
		return nave.CompraDrone();
	}

	public int planetagetCorID() {
		return planeta.getCorID();
	}

	public int CausaDanoDrone() {
		return nave.CausaDanoDrone();
	}

	public boolean TemMunicao() {
		return nave.TemMunicao();
	}

	public void DestroiDrone() {
		nave.DestroiDrone();
		
	}

	public boolean saberSeDroneEstaIntacto() 
	{
		return nave.getDrone().isIntacta();
	}

	public boolean FazerAtaque() {
		return nave.FazerAtaque();
	}

	public boolean planetagetByNum(int rand) {
		return planeta.getByNum(rand);
	}

	public void planetaretiraRecurso(int rand) {
		planeta.retiraRecurso(rand);
		
	}

	
	public int PlanetaDizNumeroDeCoisasDeInteresse() {
		return planeta.DizNumeroDeCoisasDeInteresse();
	}

	
	public void apanhaRecurso(int rec) {
		nave.apanhaRecurso(rec);
	}

	
	public int getArtefacts() {

		return nave.getArtefacts();
	}

	//verifica se tem o combustivel/ recursos para continuar
	//return true: o jogo continuar
	//return false: o jogo deverá passar para o estado fimdejogo()
	public boolean VerificaSeTemCombustiovelOuRecursos() {
		int t = nave.VerificaFimdeJogo();
		if (t == 0)
			 return true;
		 
		else if(t == 1)
			addLogComentarios("Parabens, Ganhou o Jogo!");
		
		else if(t == -1)
			addLogComentarios("Perdeu o jogo");
		
		else if(t==2) {
			addLogComentarios("Não Tem Combustivel, Porém Pode Faze-lo A Partir Dos Recursos");
			return true;
		}
			
		return false;
		
	}

	public String MostrarDadosNave() {
		return nave.toString();
	}

	public int VerificaFimdeJogo() {
		return nave.VerificaFimdeJogo();
	}

	public boolean ComprarEscudo() {
			return nave.ComprarEscudo();
	}

	public boolean ComprarAmmo() {
		return nave.ComprarAmmo();
	}

	public boolean ComprarFluel() {
		boolean b = nave.ComprarFluel();
		
		if(b)
			addLogComentarios("Obteve Combustivel");
		else
			addLogComentarios("Não Tem Recursos Para Obter Combustivel");
		
		return b;
	}

	public boolean ConverterRecurso(int r1, int r2) {
		return nave.ConverterRecurso(r1, r2);
	}

	
	//Devolve todos os elemntos do log em uma string
	//dá clear ao log
	public String mostrarLog() {
		String str = new String();
		
		for(String s:msgLog )
		{
			str += s;
			str +="\n";
		}
		
		msgLog.clear();
		
		return str;
	}

	public boolean passaPorWormHole() {
		return nave.passaPorWormHole();
		
	}

	public Boolean vaiProximo() {
		boolean p= proximo;
		proximo = !proximo;
		return p;
	}

	public boolean UpgradeCargoHold() {
		
		if(SSisCargoUpgrade())
		{
			//retorna true se tiver os recursos e o militar estiver vivo
			if( UpgradeMaxCargo() )
			{
				SSsetCargoUpgrade(false);
				return true;
			}
			
		}
		return false;
	}

	public boolean TrocarRecursos(int opt, int r1, int r2) {
	
		boolean b = nave.TrocarRecursos(opt, r1, r2);
		
		if(b)
			addLogComentarios("Troca efetuada com sucesso;");
		else
			addLogComentarios("Troca requerida não pode ser efetuada");
		
		return b;
	}


	public List<String> getLstDadosNave() {
		return nave.getLstDados();
	}


	/*
	1- UPGRADE CARGO HOLD
	2- HEAL LANDING VEHICLE
	3- CONTRATAR OFFICIAL
	4-UPGRADE ARMAS
	5- NOVO LANDING VEHICLE
	*/
	public void MercadoSS(int n) {

		
		boolean sucesso;
		
		if(n == 1)
		{
			sucesso = UpgradeCargoHold() ;
			if(sucesso)
				addLogComprasNaEstacaoEspacial("Cargo Hold Foi Melhorado Com Sucesso");
			else
				addLogComprasNaEstacaoEspacial("Cargo Hold Nao Pode Ser Melhorado");
			return;
		}
		else if(n == 2)
		{
			sucesso = HealLandingCraft() ;
			if(sucesso)
				addLogComprasNaEstacaoEspacial("Drone Foi 'Curado' ");
			else
				addLogComprasNaEstacaoEspacial("Não Foi Possivel 'Curar' O Drone ");
			return;
		}
		else if(n == 3)
		{
			sucesso = ContrartarOficial();
			if(sucesso)
				addLogComprasNaEstacaoEspacial("Militar contratado com sucesso ");
			else
				addLogComprasNaEstacaoEspacial("nao foi possivel adquirir um militar ");
			return;
		}
		else if(n == 4)
		{
			sucesso = UpgradeWeapons();
			if(sucesso)
				addLogComprasNaEstacaoEspacial("armas melhoradas com sucesso' ");
			else
				addLogComprasNaEstacaoEspacial("nao foi possivel melhorar as armas ");
			return;
		}
		else if(n == 5)
		{
			sucesso = CompraDrone();
			if(sucesso)
				addLogComprasNaEstacaoEspacial("drone foi comprado com sucesso ");
			else
				addLogComprasNaEstacaoEspacial("nao foi possivel comprar o drone ");
			return;
		}
		
		
		
		
	//	-------------------------------------------------------
		else if(n == 0)
		{
		return;	
		}	
	}
}
