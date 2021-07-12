package com.example.Eval_Poyatos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Eval_Poyatos.entity.Client;
import com.example.Eval_Poyatos.entity.Item;


@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

	Iterable<Item> findAllByClients(Client client);
	
}
