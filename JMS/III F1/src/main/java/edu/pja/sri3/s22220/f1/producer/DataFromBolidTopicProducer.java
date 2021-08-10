package edu.pja.sri3.s22220.f1.producer;
//Wysyłanie wiadomości z bolidu
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import edu.pja.sri3.s22220.f1.config.JmsConfig;
import edu.pja.sri3.s22220.f1.model.Bolid;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataFromBolidTopicProducer {
    private final JmsTemplate jmsTemplate;
    //W celu testowania proponuję zminić 15000 na 1000
    @Scheduled(fixedRate = 15000)
    public void sendHello() {
        Bolid message = Bolid.builder()
                .engineTemperature(Bolid.newEngineTemperature())
                .tyresPresure(Bolid.newTyresPresure())
                .oilPresure(Bolid.newOilPresure())
                .breaksTemperature(Bolid.newBreaksTemperature())
                .tyresTemperature(Bolid.newTyresTemperature())
                .tyresDegradation(Bolid.newTyresDegradation())
                .createdAt(LocalDateTime.now())
                .build();
        System.out.println("\n" + message.getCreatedAt());
        jmsTemplate.convertAndSend(JmsConfig.TOPIC_F1, message);
    }
}
