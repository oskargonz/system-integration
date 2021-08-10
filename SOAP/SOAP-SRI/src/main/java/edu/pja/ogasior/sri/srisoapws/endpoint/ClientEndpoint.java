package edu.pja.ogasior.sri.srisoapws.endpoint;

import edu.pja.ogasior.sri.srisoapws.model.Client;
import edu.pja.ogasior.sri.srisoapws.repo.ClientRepository;
import edu.pja.ogasior.sri.srisoapws.config.SoapWSConfig;
import edu.pja.ogasior.sri.srisoapws.clients.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class ClientEndpoint {
    private final ClientRepository clientRepository;

    @PayloadRoot(namespace = SoapWSConfig.CLIENT_NAMESPACE, localPart = "getClientsRequest")
    @ResponsePayload
    public GetClientsResponse getClients(@RequestPayload GetClientsRequest req){
        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> dtoList = clientList.stream()
                .map( this::convertToDto )
                .collect(Collectors.toList());
        GetClientsResponse res = new GetClientsResponse();
        res.getClients().addAll(dtoList);
        return res;
    }

    @PayloadRoot(namespace = SoapWSConfig.CLIENT_NAMESPACE, localPart = "getClientByIdRequest")
    @ResponsePayload
    public GetClientByIdResponse getClientById(@RequestPayload GetClientByIdRequest req){
        Long clientId = req.getClientId().longValue();
        Optional<Client> cli = clientRepository.findById(clientId);
        GetClientByIdResponse res = new GetClientByIdResponse();
        res.setClient(convertToDto(cli.orElse(null)));
        return res;
    }

    @PayloadRoot(namespace = SoapWSConfig.CLIENT_NAMESPACE, localPart = "addClientRequest")
    @ResponsePayload
    public AddClientResponse addClient(@RequestPayload AddClientRequest req){
        ClientDto cliDto = req.getClient();
        Client cli = convertToEntity(cliDto);
        clientRepository.save(cli);
        AddClientResponse response = new AddClientResponse();
        response.setClientId(new BigDecimal(cli.getId()));
        return response;
    }


    @PayloadRoot(namespace = SoapWSConfig.CLIENT_NAMESPACE, localPart = "setPeselRequest")
    @ResponsePayload
    public SetPeselResponse setPesel(@RequestPayload SetPeselRequest req){
        Pattern pattern = Pattern.compile("[0-9]{2}[0-1][0-9][0-3][0-9]{6}");
        Matcher matcher = pattern.matcher(req.getPESEL().toString());
        boolean matchFound = matcher.find();
        if(matchFound) {
            Long clientId = req.getClientId().longValue();
            Optional<Client> cli = clientRepository.findById(clientId);
            GetClientByIdResponse res = new GetClientByIdResponse();
            res.setClient(convertToDto(cli.orElse(null)));

            ClientDto cliDto = res.getClient();
            cliDto.setPESEL(new BigDecimal(req.getPESEL().longValue()));
            Client cli2 = convertToEntity(cliDto);
            clientRepository.save(cli2);

            SetPeselResponse response = new SetPeselResponse();
            response.setClient(convertToDto(cli2));
            return response;
        }
        return null;
    }

    @PayloadRoot(namespace = SoapWSConfig.CLIENT_NAMESPACE, localPart = "updateClientRequest")
    @ResponsePayload
    public UpdateClientResponse updateClient(@RequestPayload UpdateClientRequest req){
        Long clientId = req.getClient().getId().longValue();
        Optional<Client> cli = clientRepository.findById(clientId);
        GetClientByIdResponse res = new GetClientByIdResponse();
        res.setClient(convertToDto(cli.orElse(null)));
        if(res.getClient() == null){
            return null;
        }else {
            ClientDto cliDto = req.getClient();
            Client cli2 = convertToEntity(cliDto);
            clientRepository.save(cli2);
            UpdateClientResponse response = new UpdateClientResponse();
            response.setClientId(new BigDecimal(cli2.getId()));
            return response;
        }



    }

    private ClientDto convertToDto(Client c){
        if (c == null){
            return null;
        }
        ClientDto dto = new ClientDto();
        dto.setId(new BigDecimal(c.getId()));
        dto.setFirstName(c.getFirstName());
        dto.setLastName(c.getLastName());
        dto.setContractActive(c.getContractActive());
        dto.setCompanyName(c.getCompanyName());
        dto.setPESEL(new BigDecimal(c.getPESEL()));

        return dto;
    }

    private Client convertToEntity(ClientDto dto){
        return Client.builder()
                .id(dto.getId() != null ? dto.getId().longValue() : null)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .contractActive(dto.isContractActive())
                .companyName(dto.getCompanyName())
                .PESEL(dto.getPESEL().longValue())
                .build();
    }
}
