package app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;

public class App {
    public static void main(String[] args) throws Exception {
    	
    	final ResourceConfig application = new ResourceConfig()
    			.packages("wsrest")
    			.register(JacksonFeature.class);
    	application.register(new CORSFilter());
    	
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(9000);
        jettyServer.setHandler(context);
        
        ServletHolder jerseyServlet = new ServletHolder(new
                org.glassfish.jersey.servlet.ServletContainer(application));
        jerseyServlet.setInitOrder(0);
        context.addServlet(jerseyServlet, "/*");        
        
        /*jerseyServlet.setInitParameter(
           "jersey.config.server.provider.classnames",
           WSAluno.class.getCanonicalName());*/

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            jettyServer.destroy();
        }
    }
}
