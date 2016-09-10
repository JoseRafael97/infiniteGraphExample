package br.edu.ifpb.infinitiGraphexample;

import java.util.Calendar;

import com.infinitegraph.ConfigurationException;
import com.infinitegraph.StorageException;

import br.edu.ifpb.infinitiGraphexample.dao.GrafoDAO;
import br.edu.ifpb.infinitiGraphexample.dao.InfinitiGraphFactory;
import br.edu.ifpb.infinitiGraphexample.entidades.ChatDeBatePapo;
import br.edu.ifpb.infinitiGraphexample.entidades.Membro;
import br.edu.ifpb.infinitiGraphexample.entidades.MensagemPrivada;

public class MainCriaGrafoApp {
	public static void main(String[] args) throws StorageException, ConfigurationException {
	
		InfinitiGraphFactory.deleteGrafo();
		InfinitiGraphFactory.criarDataBase();
		
		GrafoDAO dao = new GrafoDAO();

		dao.iniciarTransacao();

		Membro me1 = new Membro("Rafael", "Masculino", 16, 16, "ralf");
		dao.adicionaPessoa(me1);
		System.out.println("CRIADO PESSOA 1");

		Membro me2 = new Membro("Rafael2", "Masculino", 16, 16, "ralf2");
		dao.adicionaPessoa(me2);
		System.out.println("CRIADO PESSOA 2");

		Membro me3 = new Membro("Rafael3", "Masculino", 16, 16, "ralf3");
		dao.adicionaPessoa(me3);
		System.out.println("CRIADO PESSOA 3");

		Membro me4 = new Membro("Rafael4", "Masculino", 16, 16, "ralf4");
		dao.adicionaPessoa(me4);
		System.out.println("CRIADO PESSOA 4");

		Membro me5 = new Membro("Rafael5", "Masculino", 16, 16, "ralf5");
		dao.adicionaPessoa(me5);
		System.out.println("CRIADO PESSOA 5");

		// Operações com membros criados.

		ChatDeBatePapo chatDeBatePapo = new ChatDeBatePapo("Chart iniciado", 500, Calendar.getInstance());
		dao.adicionarConexaoCharUnidericional(me1, me2, chatDeBatePapo);

		MensagemPrivada mensagemPrivada = new MensagemPrivada("Olá Cara, Só curtindo as férias ?", "Só assistindo",
				Calendar.getInstance());
		dao.adicionarConexaoMensagemUnidericional(me1, me2, mensagemPrivada);

		MensagemPrivada mensagemPrivada2 = new MensagemPrivada("E ai truta e paradas?", "Ta tudo tranquilo, ta favorável",
				Calendar.getInstance());
		dao.adicionarConexaoMensagemBidericional(me1, me4, mensagemPrivada2);

		MensagemPrivada mensagemPrivada3 = new MensagemPrivada("Caramba como ta ?",
				"Porra, não to bem", Calendar.getInstance());
		dao.adicionarConexaoMensagemUnidericional(me1, me3, mensagemPrivada3);

		MensagemPrivada mensagemPrivada4 = new MensagemPrivada("Banco de dados vai passar?",
				"Depende disso kk!", Calendar.getInstance());
		dao.adicionarConexaoMensagemBidericional(me4, me5, mensagemPrivada4);

		MensagemPrivada mensagemPrivada5 = new MensagemPrivada("Mano doido",
				"Vai...", Calendar.getInstance());
		dao.adicionarConexaoMensagemUnidericional(me4, me1, mensagemPrivada5);
		
		//Adicionar Nó Raiz
		dao.colocarNoComoRaiz(me1);
		
		System.out.println("Operações Realizadas com Sucesso!");
		

		dao.fecharConexao();
	}
}
