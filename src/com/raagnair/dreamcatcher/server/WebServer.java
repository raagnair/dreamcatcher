package com.raagnair.dreamcatcher.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.raagnair.dreamcatcher.config.Configurator;
import com.raagnair.dreamcatcher.config.WebConstant;

public class WebServer
{
    public static final Logger       LOGGER       = Logger.getLogger(WebServer.class.getSimpleName());
    private static final ClassLoader CLASS_LOADER = WebServer.class.getClassLoader();
    private static final int         PORT         = getPort();
    private static final String      WEBAPP_DIR   = Configurator.getProperty(WebConstant.DIRECTORY_WEBAPP);
    private static final String      PATH_REST    = Configurator.getProperty(WebConstant.PATH_REST);
    private static final String      PATH_HTML    = Configurator.getProperty(WebConstant.PATH_HTML);
    private static final String      PACKAGE_REST = Configurator.getProperty(WebConstant.PACKAGE_REST);

    public static void main(String[] args) throws Exception
    {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(prepareStaticHandler(), PATH_HTML);
        contextHandler.addServlet(prepareRESTHandler(), PATH_REST);
        Server server = new Server(PORT);
        server.setHandler(contextHandler);

        try
        {
            server.start();
            LOGGER.info("WebServer has started up.");

            server.join();
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, "Server terminated.", e);
        }
        finally
        {
            try
            {
                server.stop();
            }
            catch (Exception e)
            {
                LOGGER.log(Level.SEVERE, "Server termination failed.", e);
            }
            finally
            {
                server.destroy();
            }
        }
    }

    private static ServletHolder prepareRESTHandler()
    {
        ResourceConfig resourceConfig = new ResourceConfig();

        resourceConfig.packages(PACKAGE_REST);

        return new ServletHolder(new ServletContainer(resourceConfig));
    }

    private static ServletHolder prepareStaticHandler()
    {
        ServletHolder holder = new ServletHolder(new DefaultServlet());
        holder.setInitParameter("resourceBase", CLASS_LOADER.getResource(WEBAPP_DIR)
                .toExternalForm());
        return holder;
    }

    private static int getPort()
    {
        return Integer.parseInt(Configurator.getsEnvVariable(WebConstant.PORT));
    }
}
