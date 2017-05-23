package com.epam.finalweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import com.epam.finalweb.service.PoolService;
import com.epam.finalweb.service.exception.ServiceException;
import com.epam.finalweb.service.factory.FactoryService;

/**
 * Application Lifecycle Listener implementation class ControllerListener
 *
 */
public class ControllerListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ControllerListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		PoolService poolService = FactoryService.INSTANCE.getPoolService();
		poolService.detroyPool();

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {

		PropertyConfigurator.configure("src//main//resources//log4jconfig.properties");

		PoolService poolService = FactoryService.INSTANCE.getPoolService();
		try {
			poolService.poolIntialise();
		} catch (ServiceException e) {
			throw new RuntimeException("Cannot intialise Connection Pool", e);

		}

	}

}
