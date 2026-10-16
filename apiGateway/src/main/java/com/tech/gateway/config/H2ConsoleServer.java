package com.tech.gateway.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Starts H2 web console on a separate port so WebFlux-only app can still expose H2 console.
 */
@Component
public class H2ConsoleServer {

    private static final Logger logger = LoggerFactory.getLogger(H2ConsoleServer.class);
    private Server webServer;

    @PostConstruct
    public void start() {
        try {
            webServer = Server.createWebServer("-web","-webAllowOthers","-webPort","8082");
            webServer.start();
            logger.info("Started H2 web console at http://localhost:8082");
        } catch (SQLException e) {
            logger.error("Failed to start H2 web console", e);
        }
    }

    @PreDestroy
    public void stop() {
        if (webServer != null) {
            webServer.stop();
            logger.info("Stopped H2 web console");
        }
    }
}
