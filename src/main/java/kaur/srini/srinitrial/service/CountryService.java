package kaur.srini.srinitrial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kaur.srini.srinitrial.data.dbobjects.Country;
import kaur.srini.srinitrial.data.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Cacheable("countries")
	public List<Country> getAllCountries() {
		return countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	@Cacheable("country")
	public Optional<Country> getCountryByCode(String code) {
		return countryRepository.findById(code);
	}
}
