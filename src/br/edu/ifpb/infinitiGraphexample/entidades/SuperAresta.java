package br.edu.ifpb.infinitiGraphexample.entidades;


import java.util.Calendar;

import com.infinitegraph.BaseEdge;

/**
 * Classe utilizada como para arestas extende a classe que representa a aresta base.
 * 
 * @author rafaelfeitosa
 *
 */

public class SuperAresta extends BaseEdge{
	
	private String mensagem;
	private long tempo;
	
	public SuperAresta(String mensagem, Calendar data) {
		this.mensagem = mensagem;
		this.tempo = data.getTimeInMillis();
	}
	
	public String getMensagem() {
		fetch();
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		markModified();
		this.mensagem = mensagem;
	}
	
	public long getTempo() {
		fetch();
		return tempo;
	}
	
	public void setTempo(long tempo) {
		markModified();
		this.tempo = tempo;
	}
	
	public Calendar getTempoCalendar()
	{
		fetch();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(tempo);
		return calendar;
	}
	
	

}
