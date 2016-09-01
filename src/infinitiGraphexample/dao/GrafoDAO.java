package infinitiGraphexample.dao;

import com.infinitegraph.AccessMode;
import com.infinitegraph.ConfigurationException;
import com.infinitegraph.GraphDatabase;
import com.infinitegraph.StorageException;
import com.infinitegraph.Transaction;

import infinitiGraphexample.entidades.Membro;
import infinitiGraphexample.entidades.Pessoa;

public class GrafoDAO {

	private GraphDatabase grafo;


	/**
	 * Adiciona no grafo um novo nó
	 * 
	 * @param membro
	 * @throws ConfigurationException 
	 * @throws StorageException 
	 */
	public void adicionaPessoa(Pessoa pessoa) throws StorageException, ConfigurationException {
		
		grafo = InfinitiGraphFactory.getGraph();
		Transaction tx = grafo.beginTransaction(AccessMode.READ_WRITE);
	
		try {
			grafo.addVertex(pessoa);
			tx.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.complete();
			grafo.close();
		}

	}

	/**
	 * Coloca o nó já criado como raiz para buscas.
	 * 
	 * @param pessoa
	 * @throws ConfigurationException 
	 * @throws StorageException 
	 */
	public void colocarNoComoRaiz(Pessoa pessoa) throws StorageException, ConfigurationException {
		grafo = InfinitiGraphFactory.getGraph();
		Transaction tx = grafo.beginTransaction(AccessMode.READ_WRITE);
	
		try {
			grafo.nameVertex(pessoa.getNome(), pessoa);
			tx.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			tx.complete();
			grafo.close();
		}
	}
	
	
	
}
