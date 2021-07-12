package com.example.Eval_Poyatos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Eval_Poyatos.entity.Client;
import com.example.Eval_Poyatos.entity.Item;
import com.example.Eval_Poyatos.repository.ItemRepository;

@Transactional
@Service
public class ItemService {
	
	@Autowired
	ItemRepository repo;
	
	public List<Item> list() {
		return (List<Item>)repo.findAll();
	}
	
	public long create(Item item) {
		item.setId(null);
		item = repo.save(item);
		return item.getId();
	}
	
	public void update(Item item) {
		item = repo.save(item);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public void delete(Item item) {
		repo.delete(item);
	}

	public Item get(Long id) {
		Optional<Item> opt = repo.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}
	
	public Iterable<Item> getAllByClient(Client client){
        return repo.findAllByClients(client);
    }
}
