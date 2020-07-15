package tppaMD_logicaDados;

import java.io.Serializable;

//esta classe simula uma LandingCraft usada para viajar no planeta
//a variavel Damage simula o dano que a landingcraft ja recebeu na viagem
//intacta é uma variavel que indica se a LandingCraft esta ou nao destruida
//NOTA: de damage>= 6 entao a nave tem de ser destruida-> intacta = false
public class LandingCraft implements Serializable{

	
	private int Damage;  
	private boolean intacta = true;
	
	
	public LandingCraft()
	{
		Damage = 0;
	}
	
	
	public int getDamage() {
		return Damage;
	}
	public void setDamage(int damage) {
		Damage = damage;
	}
	public boolean isIntacta() {
		return intacta;
	}
	public void setIntacta(boolean intacta) {
		this.intacta = intacta;
	}
	
	//esta funcao é usada aquando se aumenta a cago hold numa estacao espacial
	//a funcao garane«te que a landingcraft esta intacta.
	//Devolde true se estiver intacta, devolve false se tiver destruida
	//warning: garantir que se esta uma estacao espacial!
	public boolean ServiceLandingCraft()
	{
		if( intacta == true)
		{
			Damage = 0;
			return true;
		}
		return false;
		
	}
	
	
	@Override
	public String toString()
	{
		String str = new String("");
		
		if(intacta)
		{
			str += "Estado: Intacta\nDano: " + Damage + " / 6";
			
		}
		else
			str += "Estado: Destrida";
		
		return str;
	}
	
	
	
}
