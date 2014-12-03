package ku.shipment.server.main;



import ku.shipment.server.service.DaoFactory;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;


public class JettyMain {
	/**
	 * The default port to listen on. Typically 80 or 8080. On Ubuntu or MacOS
	 * if you are not root then you must use a port > 1024.
	 */
	static final int PORT = 8080;
	static Server server;
	static final String RESOURCE_PACKAGE = "sts.resource";

	/**
	 * Start Server.
	 * 
	 * @param args
	 *            not used
	 * @throws Exception
	 *             if Jetty server encounters any problem
	 */
	public static void main(String[] args) throws Exception {
		startServer(PORT);
		System.out.println("Server started.  Press ENTER to stop it.");
		int ch = System.in.read();
		stopServer();
	}

	/**
	 * Create a Jetty server and a context, add Jetty ServletContainer which
	 * dispatches requests to JAX-RS resource objects, and start the Jetty
	 * server.
	 * 
	 * @param port running port of server
	 * @return path of server
	 * @throws Exception if Jetty server encounters any problem
	 */
	public static String startServer(int port) throws Exception {
		server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		ServletHolder holder = new ServletHolder(org.glassfish.jersey.servlet.ServletContainer.class);
		holder.setInitParameter(ServerProperties.PROVIDER_PACKAGES,RESOURCE_PACKAGE);
		holder.setInitParameter(ServerProperties.JSON_PROCESSING_FEATURE_DISABLE, "false");
		context.addServlet(holder, "/*");
		server.setHandler(context);
		System.out.println("Starting Jetty server on port ");
		server.start();
//		return server.getURI().toString();
		//	Error because ku-win network 
		return "http://localhost:"+port+"/";
	}

	/**
	 * Stop the Jetty server and shutdown Dao.
	 */
	public static void stopServer() {
		System.out.println("Stopping server.");
		DaoFactory.getInstance().shutdown();
		System.out.println("Shutted down.");
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}