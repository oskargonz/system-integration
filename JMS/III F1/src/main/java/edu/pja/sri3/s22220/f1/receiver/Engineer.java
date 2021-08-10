package edu.pja.sri3.s22220.f1.receiver;
//Odebranie wiadomości od Drivera, losowanie i wysłanie odpowiedz

import edu.pja.sri3.s22220.f1.config.JmsConfig;
import edu.pja.sri3.s22220.f1.model.Bolid;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class Engineer {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.QUEUE_ENGINEER)
    public void receiveAndRespond(@Payload Bolid convertedMessage,
                                  @Headers MessageHeaders headers,
                                  Message message) throws JMSException {
        System.out.println("Engineer.receiveAndRespond message: "+convertedMessage.getMessage());
        Destination replyTo = message.getJMSReplyTo();

        //Losowanie odpowiedzi
        double losowanie = Math.random() * 3;
        if (losowanie < 1){
            Bolid msgToDriver = Bolid.builder()
                    .message("OK Max, BOX BOX!")
                    .build();
            jmsTemplate.convertAndSend(replyTo, msgToDriver);
        }else if (losowanie < 2){
            Bolid msgToDriver = Bolid.builder()
                    .message("We extend the sprint. Plan A + 10.")
                    .build();
            jmsTemplate.convertAndSend(replyTo, msgToDriver);
        }else {
            Bolid msgToDriver = Bolid.builder()
                    .message("Rain expected in 5 minutes. Please extend the sprint.")
                    .build();
            jmsTemplate.convertAndSend(replyTo, msgToDriver);

        }
    }

}
