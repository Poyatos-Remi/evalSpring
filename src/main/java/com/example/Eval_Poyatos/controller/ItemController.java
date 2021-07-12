package com.example.Eval_Poyatos.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.Eval_Poyatos.entity.Client;
import com.example.Eval_Poyatos.entity.Item;
import com.example.Eval_Poyatos.service.ClientService;
import com.example.Eval_Poyatos.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService service;
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/item")
    public String list(Model model) {
        List<Item> list = service.list();
        model.addAttribute("items",list);
        return "list_items";
    }
	
	@GetMapping("/item/add/{id}")
	public String add(@PathVariable("id") long id, Model model) {
		
		Client client = clientService.get(1L);
				
		Item item = service.get(id);
		
		clientService.addItem(client, item);
						
		return "redirect:/panier";
		
	}

	@GetMapping("/panier")
	public String voirPanier(Model model) {
		
		Client client = clientService.get(1L);
		
		List<Item> items = client.getListItem();
		double total = 0;
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			total += item.getPrix();
		}
		model.addAttribute("items",items);
		model.addAttribute("prixTotal",total);
		return "panier";
	}
	
	
	@GetMapping("/item/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {

		Client client = clientService.get(1L);
		client.getListItem().remove(service.get(id));
		clientService.update(client);
		return "redirect:/panier";

	}
	
	@GetMapping("/paiement")
	public String voirPaiement(Model model) {
		Client client = clientService.get(1L);
		
		List<Item> items = client.getListItem();
		double total = 0;
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			total += item.getPrix();
		}
		model.addAttribute("prixTotal",total);
		return "paiement";
	}
	
	@GetMapping("/confirmCommand")
	public String payer(Model model) {
		Client client = clientService.get(1L);
		List<Item> items = client.getListItem();
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			service.delete(item);
		}
		
		return "redirect:/panier";
	}
	
	
}
