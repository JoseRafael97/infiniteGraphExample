package br.edu.ifpb.infinitiGraphexample;

import com.infinitegraph.ConfigurationException;
import com.infinitegraph.StorageException;

import br.edu.ifpb.infinitiGraphexample.dao.GrafoDAO;

public class MainConsultasApp {

	public static void main(String[] args) throws StorageException, ConfigurationException {

		GrafoDAO grafoDAO = new GrafoDAO();

		grafoDAO.iniciarTransacao();
		
		System.out.println("PESSOA: " + grafoDAO.buscarTodos());
		
		grafoDAO.fecharConexao();
	}

}
