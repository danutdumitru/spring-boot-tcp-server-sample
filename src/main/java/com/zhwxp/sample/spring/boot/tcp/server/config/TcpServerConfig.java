package com.zhwxp.sample.spring.boot.tcp.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayCrLfSerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TcpServerConfig {

	@Value("${tcp.server.port}")
	private int port;

	@Bean
	public AbstractServerConnectionFactory serverConnectionFactory() {
		TcpNioServerConnectionFactory serverConnectionFactory = new TcpNioServerConnectionFactory(port);
		serverConnectionFactory.setSerializer(new ByteArrayCrLfSerializer());
		serverConnectionFactory.setDeserializer(new ByteArrayCrLfSerializer());
		return serverConnectionFactory;
	}

	@Bean
	public MessageChannel inboundChannel() {
		return new DirectChannel();
	}

	@Bean
	public TcpInboundGateway inboundGateway(AbstractServerConnectionFactory serverConnectionFactory,
			MessageChannel inboundChannel) {
		TcpInboundGateway tcpInboundGateway = new TcpInboundGateway();
		tcpInboundGateway.setConnectionFactory(serverConnectionFactory);
		tcpInboundGateway.setRequestChannel(inboundChannel);
		return tcpInboundGateway;
	}

}
