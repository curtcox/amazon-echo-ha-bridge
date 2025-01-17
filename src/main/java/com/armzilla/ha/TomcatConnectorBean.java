package com.armzilla.ha;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by arm on 9/12/15.
 */
@Component

public class TomcatConnectorBean {
    @Value("${emulator.portbase}")
    private int portBase;
    @Value("${emulator.portcount}")
    private int portCount;
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = null;
        for(int i = 0; i < portCount; i ++) {
            if(tomcat == null){
                tomcat = new TomcatServletWebServerFactory(portBase + i);
            }else{
                tomcat.addAdditionalTomcatConnectors(createConnector(portBase + i));
            }
        }
        return tomcat;
    }

    private Connector createConnector(int portNumber) {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        connector.setScheme("http");
        connector.setPort(portNumber);
        return connector;
    }
}
