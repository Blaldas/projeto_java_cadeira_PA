package tppaMD_logicaDados;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Planeta implements Serializable{

	
	private String cor;
	private Recursos recursos;
	private int artefact;
	private int corID;
	
	public Planeta()
	{
		
		//random para tirar tipo de planeta
		//1-azul
		//2-verde
		//3-vermelho
		//4-preto
		int rand = ThreadLocalRandom.current().nextInt(1,5);

		if(rand== 1)
		{
			cor =  new String("azul");
			recursos = new Recursos(1,0,1,1);
			artefact =1;
			corID = 1;
			
		}
		else if(rand== 2)
		{
			cor = new String("verde");
			recursos = new Recursos(0,1,1,0);
			artefact =0;
			corID = 2;
		}
		else if(rand== 3)
		{
			cor =  new String("vermelho");
			recursos = new Recursos(0,1,0,1);
			artefact =0;
			corID = 3;
		}
		else if(rand== 4)
		{
			cor =  new String("preto");
			recursos = new Recursos(1,0,0,1);
			artefact =0;
			corID = 4;
		}
		
		
		
		
		
	}
	
	@Override
	public String toString()
	{
		
		String str = new String("Dados do Planeta:\n");
		
		str += "Cor: " + cor;
		str += "Recursos do planeta:\n";
		str += recursos.toString() + "\n";
		str += "Artifact: ";
		if(artefact == 1)
			str += "Sim";
		else
			str += "Não";

		return str;
	}
	
	public int DizNumeroDeCoisasDeInteresse()
	{
		if (artefact == 1)
			return recursos.ContaRecursos() + 1;
		return recursos.ContaRecursos();
	}

	public String getCor()
	{
		return cor;
	}
	
	
	
	//verifica se contem os recursos
		//1- preto;
		//2- vermelho;
		//3- verde;
		//4- azul;
		//5- artefacto
		//return true: contem o recurso indicado
		//return false: nao contem o recurso indicado
		public boolean getByNum(int n)
		{
			//1- preto;
			//2- vermelho;
			//3- verde;
			//4- azul;
			if(n == 1)
			{	
				if(recursos.getPreto() != 0)
				{
					return true;
				}
				return false;
			}	
			if(n == 2)
			{	
				if(recursos.getVermelho() != 0)
				{
					return true;
				}
				return false;
			}	
			if(n == 3)
			{	
				if(recursos.getVerde() != 0)
				{
					return true;
				}
				return false;
			}	
			if(n == 4)
			{	
				if(recursos.getAzul() != 0)
				{
					return true;
				}
				return false;
			}	
			if(n == 5)
			{
				if(artefact == 1)
					return true;
				return false;
			}
			
			
			return false;
			
		}
		
		
		//tira 1 do recurso inidcado com n;
		public void retiraRecurso(int n)
		{
			if(n == 1)
			{	
				
				recursos.UsaPreto(1);
				
			}	
			else if(n == 2)
			{	
				recursos.UsaVermelho(1);
			
			}	
			else if(n == 3)
			{	
				recursos.UsaVerde(1);
			}	
			else if(n == 4)
			{	
				recursos.usaAzul(1);
			}	
			else if(n == 5)
				artefact--;
			return ;
		
		}

		public int getCorID() {
			return corID;
		}

		public void setCorID(int corID) {
			this.corID = corID;
		}
	
	
		public int getRecursoVerde() {
			return recursos.getVerde();
		}
	
		public int getRecursoVermelho() {
			return recursos.getVermelho();
		}
		public int getRecursoPreto() {
			return recursos.getPreto();
		}
		public int getRecursoAzul() {
			return recursos.getAzul();
		}
		
		public int getArtefacto() {
			return artefact;
		}
		
	
	
	
	
}
