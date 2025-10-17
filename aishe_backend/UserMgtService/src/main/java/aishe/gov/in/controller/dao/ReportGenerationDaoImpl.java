package aishe.gov.in.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportGenerationDaoImpl implements ReportGenerationDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(ReportGenerationDaoImpl.class);


	@Override
	public Connection findConnection() {
		
		logger.info("getting connection object");
		Connection connection = null;
		try {
			connection = sessionFactory. getSessionFactoryOptions().getServiceRegistry().
			        getService(ConnectionProvider.class).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
