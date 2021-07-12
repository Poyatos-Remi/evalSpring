package com.example.Eval_Poyatos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Eval_Poyatos.entity.Client;
import com.example.Eval_Poyatos.entity.Item;
import com.example.Eval_Poyatos.repository.ClientRepository;

@Transactional
@Service
public class ClientService {
	
	@Autowired
	ClientRepository repo;
	
	public List<Client> list() {
		return (List<Client>)repo.findAll();
	}
	
	public long create(Client client) {
		client.setId(null);
		client = repo.save(client);
		return client.getId();
	}
	
	public void update(Client client) {
		client = repo.save(client);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public void delete(Client client) {
		repo.delete(client);
	}

	public Client get(Long id) {
		Optional<Client> opt = repo.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}
	
	public void addItem(Client client,Item item) {
		client.getListItem().add(item);
		client = repo.save(client);
	}
}
