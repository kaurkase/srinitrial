package kaur.srini.srinitrial.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kaur.srini.srinitrial.data.dbobjects.Client;
import kaur.srini.srinitrial.data.dbobjects.SriniUser;
import kaur.srini.srinitrial.data.dto.ClientList;
import kaur.srini.srinitrial.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;

	@GetMapping("/byuser")
	public ClientList getClientsByUser(@AuthenticationPrincipal SriniUser user) {
		return new ClientList(clientService.getClientsByUserId(user.getId()));
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Client saveClient(@RequestBody @Valid Client client, @AuthenticationPrincipal SriniUser user) throws MethodArgumentNotValidException {
		client = clientService.saveClient(client, user);
		return client;
	}

	@GetMapping("/{id}")
	public Client getClientById(@PathVariable Long id) {
		return clientService.getClient(id);
	}
}
