package com.fsantos.unittest.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fsantos.unittest.models.ProdutoModel;

@DataJpaTest
class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void itShouldLoadTwoProductsWithASpecificName() {
		//given
		ProdutoModel product1 = new ProdutoModel(1234, "Xbox 360", 20D, 5, null);
		ProdutoModel product2 = new ProdutoModel(1234, "Xbox", 20D, 5, null);
		underTest.saveAll(Arrays.asList(product1, product2));
		
		//when
		List<ProdutoModel> products = underTest.findByNomeProdContains("Xbox");
		
		//then
		assertEquals(products.get(0).getNomeProd(), product1.getNomeProd());
		assertEquals(products.get(1).getNomeProd(), product2.getNomeProd());
	}
	
	@Test
	void itShouldLoadAProductWithASpecificName() {
		//given
		ProdutoModel product = new ProdutoModel(1234, "Playstation 5", 20D, 5, null);
		underTest.save(product);
		
		//when
		List<ProdutoModel> products = underTest.findByNomeProdContains("Playstation");
		
		//then
		assertEquals(products.get(0).getNomeProd(), product.getNomeProd());
	}

}
