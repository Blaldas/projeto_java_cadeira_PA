package tppaMD_logicaEstados;


import tppaMD_logica.*;
import tppaMD_logicaDados.Jogo;
import tppaMD_logicaDados.Naves;
import tppaMD_logicaDados.Planeta;
import tppaMD_logicaDados.SpaceStation;

public abstract class EstadoAdapter implements IEstado{

	protected Jogo jogo;
	//protected Naves nave;
	//proximo indica se é evento ou planeta
	//true: planeta;
	//false: evento
	
	public Jogo getJogo() {
		return jogo;
	}

	
	public EstadoAdapter(Jogo jogo) 
	{
		this.jogo = jogo;
	}

	@Override
	public IEstado AvancaNoEspaco() {
		// TODO Auto-generated method stub
		return this;
	}
	

	
	@Override
	public IEstado executa() {
		return this;
	}


	
	
	@Override
	public IEstado comecaJogo() {
		
		return new EstadoEsperaMover(jogo);
	}
	
	@Override
	public IEstado FimOuRecomeco(int i)
	{
		return null;
	}
	

	@Override
	public IEstado AplicaEvento(int rand)
	{
		return this;
	}
	
	@Override
	public IEstado voltaParaOrbitaPlaneta() 
	{
		return this;
	}

	@Override
	public IEstado EntrarNoPlaneta()
	{
		return new EstadoReageSuperficie(jogo);
	}

	@Override
	public IEstado reagirNoPlaneta()
	{
		//System.out.println("isto esta a ser mal chamado: func reagirNoPlaneta \ta ser chamado em estadoadapter.java");
		return null;
	}




	//verifica se tem o combustivel/ recursos para continuar
	//return true: o jogo continuar
	//return false: o jogo deverá passar para o estado fimdejogo()
	@Override
	public IEstado VerificaSeOJogoContinua()
	{
		if(jogo.VerificaSeTemCombustiovelOuRecursos())
			return this;
		else
			return new EstadoFimDeJogo(jogo);
	}

	@Override
	public IEstado EntraNoMercadoSS()
	{
		return new EstadoMercadoNaEstacaoEspacial(jogo);
	}

	















































}
