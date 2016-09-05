package br.edu.ifpb.infinitiGraphexample.entidades;

import java.util.Calendar;

public class ChatDeBatePapo extends SuperAresta {

	private int duracao;

	public ChatDeBatePapo(String mensagem, int duracao, Calendar data) {
		super(mensagem, data);
		this.duracao = duracao;
	}

	public int getDuracao() {
		fetch();
		return duracao;
	}

	public void setDuracao(int duracao) {
		markModified();
		this.duracao = duracao;
	}
	
	

}
