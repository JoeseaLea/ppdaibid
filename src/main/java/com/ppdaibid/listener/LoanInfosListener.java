package com.ppdaibid.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ppdaibid.AutoBidManager;

/**
 * Application Lifecycle Listener implementation class LoanInfosListener
 *
 */
public class LoanInfosListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    		AutoBidManager.startLoanInfos();
    }
	
}
