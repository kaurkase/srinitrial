package kaur.srini.srinitrial.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kaur.srini.srinitrial.data.dbobjects.Country;
import kaur.srini.srinitrial.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;
	@GetMapping("/list")
	public List<Country> getAllCountries() {
		return countryService.getAllCountries();
	}
}
