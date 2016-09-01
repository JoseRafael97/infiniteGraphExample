package infinitiGraphexample.dao;

import com.infinitegraph.ConfigurationException;
import com.infinitegraph.GraphDatabase;
import com.infinitegraph.GraphFactory;
import com.infinitegraph.StorageException;

/**
 * Classe responsável por criar novo grafo
 * @author rafaelfeitosa
 *
 */
public class InfinitiGraphFactory {
	
	private static final String GRAFO_DBNOME = "chat" ;
	private static final String PATH_CONF_PROPRIEDADES = "configuracoes/confLocalDosDados.properties";

	
	/**
	 * Método que cria um novo grafo de acordo com as constantes de nome e path de configuração.
	 * @throws StorageException
	 * @throws ConfigurationException
	 */
	public static void criarDataBase() throws StorageException, ConfigurationException{
		GraphFactory.create(GRAFO_DBNOME, PATH_CONF_PROPRIEDADES);
	}
	
	/**
	 * Recupera o grafo com nome presente na constante.
	 * @return
	 * @throws ConfigurationException 
	 * @throws StorageException 
	 */
	public static GraphDatabase getGraph() throws StorageException, ConfigurationException{
		if(GraphFactory.open(GRAFO_DBNOME, PATH_CONF_PROPRIEDADES) == null){
			criarDataBase();
		}
		return GraphFactory.open(GRAFO_DBNOME, PATH_CONF_PROPRIEDADES);
	}

}
