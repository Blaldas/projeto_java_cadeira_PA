package tppaMD_interface;

import java.beans.PropertyChangeListenerProxy;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import gui.BasePane;
import tppaMD_logica.MaqEstados;
import tppaMD_logicaEstados.EstadoSelectVeicle;
import tppaMD_logicaEstados.IEstado;


//segue o medelo, acrescentar as funcoes conforme o modelo
public class JogoObservavel extends PropertyChangeSupport implements Constantes{

	private MaqEstados maqEstados;
	
	
	public JogoObservavel(MaqEstados maqEstados) {
		super(maqEstados);
		this.maqEstados = maqEstados;
		
		
		
		//paneOrganizer = new PaneOrganizer(maqEstados);
	}


	public List<String> getLstDadosNave() {
		return maqEstados.getJogo().getLstDadosNave();
	}
	
	
	public MaqEstados getMaqEstados() {
		return maqEstados;
	}


	public void trocarRecursos(int opt, int r1, int r2) {
			
		maqEstados.TrocarRecursos(opt, r1, r2);
		System.out.println(maqEstados.getJogo().getNave().getRecursoVermelho());
		firePropertyChange(null, null, null);
	}


	public void setJogoObservavel(MaqEstados maqEstados) {
		this.maqEstados =maqEstados;
		firePropertyChange(null, null, null);
	}


	public MaqEstados getjogoObservavel() {
		return maqEstados;
	}

	public IEstado getEstado() {
		return maqEstados.getEstado();
	}


	public void iniciaMineira() {
		maqEstados.EscolheNave(2);

		firePropertyChange(null, null, null);
	}

	public void iniciaMilitar() {
		maqEstados.EscolheNave(1);

		firePropertyChange(null, null, null);
	}


	public void avancaNoEspaço() {
		maqEstados.AvancaNoEspaco();
		
		firePropertyChange(null, null, null);
		
	}


	public void FimOuRecomeco(int i) {
		maqEstados.FimOuRecomeco(i);
		
		firePropertyChange(null, null, null);
	}


	public String mostrarLog() {
		return maqEstados.getJogo().mostrarLog();
	}


	public boolean temEstacaoEspacial() {
		return maqEstados.getJogo().isSpaceStation();
	}


	public void irParaEstadoEsperaMover() {
		maqEstados.IrParaEstadoEsperaMover();
		
		firePropertyChange(null, null, null);
	}


	public void entrarNoPlaneta() {
		maqEstados.EntrarNoPlaneta();
		firePropertyChange(null, null, null);
	}


	public void entraEstacaoEspacial() {
		maqEstados.EntraEstacaoEspacial();
		
		firePropertyChange(null, null, null);
	}


	public void MercadoEstacaoEspacial(int i) {
		maqEstados.MercadoEstacaoEspacial(i);
		
		firePropertyChange(null, null, null);
	}


	public void atualiza() {
		firePropertyChange(null, null, null);	
	}


	public static void pausa() {
		try {
			Thread.sleep(TEMPO_PAUSA);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro a fazer thread.sleep\tBasePane.java");
		}
		
	}


	public void comprarCombustivel() {
		maqEstados.comprarCombustivel();
		
		atualiza();
		
	}


	public void aplicaEvento() {
		maqEstados.ReageEvento();
		atualiza();
		
	}


	public void comprarAmmo() {
		maqEstados.TrocarRecursos(2, 0, 0);
		
		atualiza();		
	}


	public void comprarEscudo() {
		maqEstados.TrocarRecursos(1, 0, 0);
		
		atualiza();			
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
