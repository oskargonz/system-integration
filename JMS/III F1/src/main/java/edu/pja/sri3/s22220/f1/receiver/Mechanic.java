package edu.pja.sri3.s22220.f1.receiver;
//Odebranie wiadomości od routera jeśli jest awaria

import edu.pja.sri3.s22220.f1.config.JmsConfig;
import edu.pja.sri3.s22220.f1.model.Bolid;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class Mechanic {
    @JmsListener(destination = JmsConfig.QUEUE_MECHANIC, containerFactory = "queueConnectionFactory")
    public void receiveHelloMessage(@Payload Bolid convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        System.out.println("Mechanic.receiveMessage, message: " + convertedMessage.getMessage());
    }


}
