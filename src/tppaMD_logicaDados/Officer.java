package tppaMD_logicaDados;

import java.io.Serializable;

public class Officer implements Serializable{
	
	private String nome;
	private boolean vivo;
	
	
	public Officer(String nome)
	{
		this.nome = nome;
		vivo = true;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isVivo() {
		return vivo;
	}
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	@Override
	public String toString()
	{
		String str = new String("Cargo do ofical: ");
		str += nome + "\n";
		if(isVivo())
			str+= "Estado: Vivo";
		else
			str+= "Estado: Morto";
		return str;		
	}
	
	
	
	
	
	
	
}
