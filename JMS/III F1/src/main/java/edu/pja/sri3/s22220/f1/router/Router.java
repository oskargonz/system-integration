package edu.pja.sri3.s22220.f1.router;
//Router dostaje wiadomość z bolidu i w zależności od warunków przesyła do kierowcy lub do kierowcy i do mechaników

import edu.pja.sri3.s22220.f1.config.JmsConfig;
import edu.pja.sri3.s22220.f1.model.Bolid;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


import javax.jms.JMSException;
import javax.jms.Message;


@Component
@RequiredArgsConstructor
public class Router {
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.TOPIC_F1)
    public void receiveAndRespond(@Payload Bolid convertedMessage,
                                  @Headers MessageHeaders headers,
                                  Message message) throws JMSException {

        //Kontrolka temperatury silnika - powiadomienie kierowcy
        if(convertedMessage.getEngineTemperature() < 90){
            Bolid msg = Bolid.builder()
                    .message("Max, engine temperature is to low!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }else if (convertedMessage.getEngineTemperature() > 105){
            Bolid msg = Bolid.builder()
                    .message("Max, STOP THE CAR, we have an engine issue.")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
            Bolid msgMechanic = Bolid.builder()
                    .message("Guys, we are out, engine issue.")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_MECHANIC, msgMechanic);
        }

        //Kontrolka cisnienia opon - powiadomienie kierowcy i mechaników
        if (convertedMessage.getTyresPresure() < 2){
            Bolid msg = Bolid.builder()
                    .message("Max, box box, we have a puncture!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
            Bolid msgMechanic = Bolid.builder()
                    .message("Guys, we have a puncture, BOX BOX, prepare new medium tyres for Max now!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_MECHANIC, msgMechanic);
        }

        //Kontrolka cisnienia oleju - powiadomienie kierowcy
        if (convertedMessage.getOilPresure() < 0.95) {
            Bolid msg = Bolid.builder()
                    .message("Max, oil preasure is to low!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }else if (convertedMessage.getOilPresure() > 1.05) {
            Bolid msg = Bolid.builder()
                    .message("Max, oil preasure is to high!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }

        //Kontrolka temperatury hamulcow - powiadomienie kierowcy
        if (convertedMessage.getBreaksTemperature() < 500) {
            Bolid msg = Bolid.builder()
                    .message("Max, breaks are cold!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }else if (convertedMessage.getBreaksTemperature() > 900) {
            Bolid msg = Bolid.builder()
                    .message("Max, breaks are to hot!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }

        //Kontrolka temperatury opon - powiadomienie kierowcy
        if (convertedMessage.getTyresTemperature() < 80) {
            Bolid msg = Bolid.builder()
                    .message("Max, tyres are cold!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }else if (convertedMessage.getTyresTemperature() > 95) {
            Bolid msg = Bolid.builder()
                    .message("Max, tyres are to hot, slow down!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
        }

        //Kontrolka zuzycia opon - powiadomienie kierowcy i mechaników
        if (convertedMessage.getTyresDegradation() < 10) {
            Bolid msg = Bolid.builder()
                    .message("Max, push now, we are boxing next lap.")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_DRIVER, msg);
            Bolid msgMechanic = Bolid.builder()
                    .message("Guys, BOX BOX, prepare new medium tyres for Max now!")
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.QUEUE_MECHANIC, msgMechanic);
        }

    }
}
