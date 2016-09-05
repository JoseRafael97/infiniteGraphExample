package br.edu.ifpb.infinitiGraphexample.entidades;

import java.util.Calendar;

public class MensagemPrivada extends SuperAresta{
	
	private String respostaMsg;

	public MensagemPrivada(String mensagem, String respostaMsg, Calendar data) {
		super(mensagem, data);
		this.respostaMsg = respostaMsg;
	}

	public String getRespostaMsg() {
		fetch();
		return respostaMsg;
	}

	public void setRespostaMsg(String respostaMsg) {
		markModified();
		this.respostaMsg = respostaMsg;
	}

	
}
