package tppaMD_logicaDados;

import java.io.Serializable;

//esta classe é responsavel pelos recursos e tudo relacionadom com eles.
//esta classe é considerada como sendo de "mais baixo nvel", ou seja, NÃO VERIFICA OS VALORES e NÃO INCLUI OUTRAS CLASSES
public class Recursos implements Serializable {

	private int preto;
	private int vermelho;
	private int verde;
	private int azul;
	
	public Recursos(int preto, int vermelho, int verde, int azul) 
	{
		
		this.preto = preto;
		this.vermelho = vermelho;
		this.verde = verde;
		this.azul = azul;
		
	}

	public int getPreto() {
		return preto;
	}
	public void setPreto(int preto) {
		this.preto = preto;
	}
	public int getVermelho() {
		return vermelho;
	}
	public void setVermelho(int vermelho) {
		this.vermelho = vermelho;
	}
	public int getVerde() {
		return verde;
	}
	public void setVerde(int verde) {
		this.verde = verde;
	}
	public int getAzul() {
		return azul;
	}
	public void setAzul(int azul) {
		this.azul = azul;
	};
	
	public void UpgradeCargo()
	{
		preto++;
		vermelho++;
		verde++;
		azul++;
		
	}
	public void DiminuiTodosRecursos(int n)
	{
		preto -= n;
		vermelho -= n;
		verde -= n;
		azul -= n;
	}
	
	public void UsaPreto(int n)
	{
		preto-=n;
	}
	public void UsaVermelho(int n)
	{
		vermelho -= n;
	}
	public void UsaVerde(int n)
	{
		verde-=n;
	}
	public void usaAzul(int n)
	{
		azul-=n;
	}
	
	
	
	//funcao que tansfora um recuso noutro.
	//1- preto;
	//2- vermelho;
	//3- verde;
	//4- azul;
	//o recurso que vai ser dado deverá ter o valor de -1
	//o recuso que vai ser ganhado deverá ter o valor de 1
	//os recursos que não entram, deverão ter o valor de 0
	//WARNING: a funcao não verifica se os valores dos recusos permitem que isto aconteça

	public void ConverteRecurso(int r1, int r2)
	{
		if(r1 ==1)
			preto--;
		else if(r1 == 2)
			vermelho--;
		else if(r1 == 3)
			verde--;
		else if(r1 == 4)
			azul--;
		
		if(r2 ==1)
			preto++;
		else if(r2 == 2)
			vermelho++;
		else if(r2 == 3)
			verde++;
		else if(r2 == 4)
			azul++;
	}


	@Override
	public String toString()
	{
		String str= new String("");
		
		str += "Preto: " + preto + "\n";
		str += "Vermelho: " + vermelho + "\n";
		str += "Verde: " + verde + "\n";
		str += "Azul: " + azul + "\n";
		
		return str;
	}
	
	//devolve numero de recursos totais existentes
	public int ContaRecursos()
	{
		return preto + azul + vermelho + verde;
	}


}
