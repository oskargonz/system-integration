package edu.pja.sri3.s22220.f1.receiver;
//Logowanie wiadomości otrzymanych z bolidu

import edu.pja.sri3.s22220.f1.config.JmsConfig;
import edu.pja.sri3.s22220.f1.model.Bolid;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import javax.jms.Message;

@Component
public class PitStopLogsReceiver {
    @JmsListener(destination = JmsConfig.TOPIC_F1, containerFactory = "topicConnectionFactory")
    public void receiveHelloMessage(@Payload Bolid convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        System.out.println("PitStopReceiver: "+convertedMessage);
    }
}
