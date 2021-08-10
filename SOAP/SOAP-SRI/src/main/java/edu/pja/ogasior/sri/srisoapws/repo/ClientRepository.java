package edu.pja.ogasior.sri.srisoapws.repo;

import edu.pja.ogasior.sri.srisoapws.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
}
