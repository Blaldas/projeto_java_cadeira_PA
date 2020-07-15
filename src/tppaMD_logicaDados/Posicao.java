package tppaMD_logicaDados;

import java.io.Serializable;

public class Posicao implements Serializable{
	
	private int x;
	private int y;
	private String nome;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Posicao(String nome, int x, int y)
	{
		this.nome = new String(nome);
		this.x = x;
		this.y = y;
		
	}
	public Posicao(String nome, Posicao drone) {
		nome = new String(nome);
		this.y = drone.getY();
		this.x = drone.getX();
	}
	
}
