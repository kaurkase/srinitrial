package kaur.srini.srinitrial.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kaur.srini.srinitrial.data.dbobjects.SriniUser;

public interface UserRepository extends JpaRepository<SriniUser, Long> {
	SriniUser findByUsername(final String username);
}
