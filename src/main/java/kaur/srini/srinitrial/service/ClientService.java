package kaur.srini.srinitrial.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import kaur.srini.srinitrial.data.dbobjects.Client;
import kaur.srini.srinitrial.data.dbobjects.Country;
import kaur.srini.srinitrial.data.dbobjects.SriniUser;
import kaur.srini.srinitrial.data.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired 
	CountryService countryService;

	public List<Client> getClientsByUserId(Long userId) {
		return clientRepository.findAllBySriniUserId(userId);
	}

	public Client saveClient(Client client, SriniUser user) throws MethodArgumentNotValidException {
		validateAndSetCountry(client);
		client.setSriniUser(user);
		return clientRepository.save(client);
	}

	public Client getClient(Long id) {
		return clientRepository.findById(id).get();
	}

	public void validateAndSetCountry(Client client) throws MethodArgumentNotValidException {
		if (client.getCountry() != null && client.getCountry().getCode() != null) {
			Optional<Country> cOptional = countryService.getCountryByCode(client.getCountry().getCode());
			if (cOptional.isPresent()) {
				client.setCountry(cOptional.get());
				return;
			}
		}
		BeanPropertyBindingResult  bindingResult = new BeanPropertyBindingResult(client, "client");
		bindingResult.addError(new FieldError("client", "country", "country is not valid"));
		MethodParameter methodParameter = null;
		try {
			methodParameter = new MethodParameter(this.getClass().getMethod("validateAndSetCountry", Client.class), 0);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		throw new MethodArgumentNotValidException(methodParameter, bindingResult);
	}
}
