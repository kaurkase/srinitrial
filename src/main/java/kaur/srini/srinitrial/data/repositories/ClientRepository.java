package kaur.srini.srinitrial.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kaur.srini.srinitrial.data.dbobjects.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	List<Client> findAllBySriniUserId(final Long userId);

}
