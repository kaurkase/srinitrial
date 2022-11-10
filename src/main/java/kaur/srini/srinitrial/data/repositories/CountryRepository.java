package kaur.srini.srinitrial.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kaur.srini.srinitrial.data.dbobjects.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

}
