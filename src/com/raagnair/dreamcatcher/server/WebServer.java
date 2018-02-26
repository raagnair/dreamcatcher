package com.raagnair.dreamcatcher.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class WebServer {
	public static int DEFAULT_PORT = 8080;
	public static String PORT_ENV_VAR = "PORT";

	public static void main(String[] args) throws Exception {
		String portStr = System.getenv(PORT_ENV_VAR);
		int port = DEFAULT_PORT;
		if (portStr != null)
			port = Integer.parseInt(portStr);
		Server server = new Server(port);

		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);

		handler.addServletWithMapping(HelloServlet.class, "/*");

		server.start();
		server.join();
	}

	@SuppressWarnings("serial")
	public static class HelloServlet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println("<h1>Hello from HelloServlet</h1>");
		}
	}
}
