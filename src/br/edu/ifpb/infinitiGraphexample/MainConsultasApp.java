package br.edu.ifpb.infinitiGraphexample;

import java.util.List;

import com.infinitegraph.ConfigurationException;
import com.infinitegraph.StorageException;

import br.edu.ifpb.infinitiGraphexample.dao.GrafoDAO;
import br.edu.ifpb.infinitiGraphexample.entidades.Pessoa;
import br.edu.ifpb.infinitiGraphexample.entidades.SuperAresta;

public class MainConsultasApp {

	public static void main(String[] args) throws StorageException, ConfigurationException {

		GrafoDAO grafoDAO = new GrafoDAO();

		grafoDAO.iniciarTransacao();
		System.out.println("------------------------------------------");

		System.out.println("BUSCAR POR NOME DO VÉRTICE:");
		Pessoa p1 =  grafoDAO.buscarPorNome("Rafael");
		Pessoa p2 = grafoDAO.buscarPorNome("Rafael4");
		System.out.println(p1);
		
		System.out.println("------------------------------------------");
		
		System.out.println("MENSAGENS TROCADAS POR DUAS PESSOAS:");
		List<SuperAresta> listP1 = grafoDAO.mensagemTrocadaDaEntreDuasPessoas(p1,p2);
		List<SuperAresta> listP2 = grafoDAO.mensagemTrocadaDaEntreDuasPessoas(p2,p1);
		
		for (int i = 0; i < listP1.size(); i++) {
			System.out.println("Mensagem "+i+" "+listP1.get(i).getMensagem());
		}
		System.out.println("______LISTA2__________");
		for (int i = 0; i < listP2.size(); i++) {
			System.out.println("Mensagem "+i+" "+listP2.get(i).getMensagem());
		}
		
		System.out.println("------------------------------------------");

		
		System.out.println("BUSCA TODOS OS VÉRTICES:");
		for (Pessoa m : grafoDAO.buscarTodos()) {
			System.out.println("NÓ {"+m+"}");
		}
		
		System.out.println("------------------------------------------");
		System.out.println("NODES RELACINADO DIRETO OU INDIRETAMENTE:");
		System.out.println(grafoDAO.buscarPessoasLigadasCollection(p1));
		
		
		grafoDAO.fecharConexao();
	}

}
