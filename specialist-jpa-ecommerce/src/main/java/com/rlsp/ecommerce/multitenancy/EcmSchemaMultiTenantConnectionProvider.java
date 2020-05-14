package com.rlsp.ecommerce.multitenancy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.hikaricp.internal.HikariCPConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.service.spi.Startable;


/**
 * Classe responsavel por implementar a CONEXAO com o DB para uso em Multitenancy por SCHEMA
 * 
 * 	- MultiTenantConnectionProvider : responsavel pela conexao
 *  - ServiceRegistryAwareService : responsavel pelo metodos injectServices(), pegando as informacoes / propriedades do "Persistence.xml"
 *  - Startable :  responsavel pelo metodo start()
 *
 */
public class EcmSchemaMultiTenantConnectionProvider implements
        MultiTenantConnectionProvider, ServiceRegistryAwareService, Startable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> properties = null;

    private ConnectionProvider connectionProvider = null;

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = getAnyConnection();

        try {
            connection.createStatement().execute("use " + tenantIdentifier); // Usa o DB/Schema com o nome de "tenantIdentifier"
        } catch (SQLException e) {
            throw new HibernateException("Não foi possível alterar " +
                    "para o schema " + tenantIdentifier + ".", e);
        }

        return connection;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return connectionProvider.getConnection();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        releaseAnyConnection(connection);
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connectionProvider.closeConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return connectionProvider.supportsAggressiveRelease();
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return connectionProvider.isUnwrappableAs(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return connectionProvider.unwrap(unwrapType);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        this.properties = serviceRegistry
                .getService(ConfigurationService.class)
                .getSettings();
    }

    @Override
    public void start() {
        HikariCPConnectionProvider cp = new HikariCPConnectionProvider(); // Pega o POOL de Conexoes do HirakiCP
        cp.configure(this.properties);

        connectionProvider = cp;
    }
}
