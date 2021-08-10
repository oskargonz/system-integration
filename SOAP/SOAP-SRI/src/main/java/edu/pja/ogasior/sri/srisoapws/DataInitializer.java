package edu.pja.ogasior.sri.srisoapws;

import edu.pja.ogasior.sri.srisoapws.model.Client;
import edu.pja.ogasior.sri.srisoapws.repo.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    private final ClientRepository clientRepository;

    private Long setter = new Long(00000000000);

    public void initData(){
        Client e1 = Client.builder()
                .firstName("Steve")
                .lastName("Jobs")
                .contractActive(true)
                .companyName("Apple")
                .PESEL(setter)
                .build();
        Client e2 = Client.builder()
                .firstName("Bill")
                .lastName("Gates")
                .contractActive(true)
                .companyName("Microsoft")
                .PESEL(setter)
                .build();
        Client e3 = Client.builder()
                .firstName("Elon")
                .lastName("Musk")
                .contractActive(false)
                .companyName("SpaceX")
                .PESEL(setter)
                .build();
        clientRepository.saveAll(Arrays.asList(e1,e2,e3));
        LOG.info("Data initailized");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){initData();}
}
