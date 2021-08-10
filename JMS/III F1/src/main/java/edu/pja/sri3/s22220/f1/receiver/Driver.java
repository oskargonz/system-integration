package edu.pja.sri3.s22220.f1.receiver;
//Driver odbiera wiadomości od routera oraz w losowy sposób wysyła wiadomości do inżyniera wyścigowego (kierownika)

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pja.sri3.s22220.f1.config.JmsConfig;
import edu.pja.sri3.s22220.f1.model.Bolid;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


@RequiredArgsConstructor
@Component
public class Driver {
    //Odbieranie wiadomości od routera
    @JmsListener(destination = JmsConfig.QUEUE_DRIVER, containerFactory = "queueConnectionFactory")
    public void receiveMessage(@Payload Bolid convertedMessage,
                                    @Headers MessageHeaders messageHeaders,
                                    Message message) {
        System.out.println("Driver.receiveMessage, message: " + convertedMessage.getMessage());
    }

    //Wysyłanie wiadomości do inżyniera wyścigowego (kierownika)
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    //W celu testowania proponuję zminić 15000 na 1000
    @Scheduled(fixedRate = 15000)
    public void sendAndReceive() throws JMSException, JsonProcessingException {
        Bolid msgToEngineer = Bolid.builder()
                .message("Mate, my rear is gone!")
                .build();

        //Nie zawsze kietowca wysyła wiadomość do inżyniera, dlatego zastosowałem losowanie
        double losowanie = Math.random() * 5;
        if (losowanie < 1){
        TextMessage responseMessage = (TextMessage) jmsTemplate.sendAndReceive(
                JmsConfig.QUEUE_ENGINEER, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage plainMessage = session.createTextMessage();
                        try {
                            plainMessage.setText(objectMapper.writeValueAsString(msgToEngineer));
                            plainMessage.setStringProperty("_type",
                                    Bolid.class.getName());
                            return plainMessage;
                        } catch (JsonProcessingException e) {
                            throw new JMSException("conversion to json failed: " +
                                    e.getMessage());
                        }
                    }
                });

        String responseText = responseMessage.getText();
        Bolid responseConverted = objectMapper.readValue(responseText,
                Bolid.class);
        System.out.println("Driver.sendAndReceive got response: "+responseConverted.getMessage());
        }
    }
}
