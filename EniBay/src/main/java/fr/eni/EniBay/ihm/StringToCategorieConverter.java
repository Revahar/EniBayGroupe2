package fr.eni.EniBay.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.EniBay.bll.CategorieServiceImpl;
import fr.eni.EniBay.bo.Categorie;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie>{
	
	private CategorieServiceImpl service;
	
	@Autowired
	public void setCategorieService(CategorieServiceImpl service) {
		this.service = service;
	}
	
	@Override
	public Categorie convert(String id) {
		Integer theId = Integer.parseInt(id);
		return service.getMapCategories().get(theId);
	}
	
}
