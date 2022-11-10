package kaur.srini.srinitrial.data.dto;

import java.util.List;

import kaur.srini.srinitrial.data.dbobjects.Client;

public class ClientList {

	private List<Client> clients;

	public ClientList() {
	}

	public ClientList(List<Client> clients) {
		super();
		this.clients = clients;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
