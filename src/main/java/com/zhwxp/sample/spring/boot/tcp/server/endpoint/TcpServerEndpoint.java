package com.zhwxp.sample.spring.boot.tcp.server.endpoint;

import com.zhwxp.sample.spring.boot.tcp.server.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class TcpServerEndpoint {

    private MessageService messageService;

    @Autowired
    public TcpServerEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    @ServiceActivator(inputChannel = "inboundChannel")
    public byte[] process(byte[] message) {
    	System.out.println("process - " + message);
        return messageService.processMessage(message);
    }

}
