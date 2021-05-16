package com.fsantos.unittest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.fsantos.unittest.dtos.ProdutoDto;
import com.fsantos.unittest.models.ProdutoModel;
import com.fsantos.unittest.repositories.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
	
	@Mock
	private ProdutoRepository produtoRepository;	
	
	private ProdutoService underTest;
	
	@BeforeEach
	void setUp() {		
		underTest = new ProdutoService(produtoRepository);
	}
	
	@Test
	void testFindAll() {
		//when
		underTest.findAll();	
		
		//then
		verify(produtoRepository).findAll();
		// verify method from Mockito checks if the method called after (.findAll()) was triggered.
		// we can edit it to check if it triggered two or more times.	
		// Note: This will not return a list or some value
	}

	@Test
	@Disabled
	void testFindOne() {
		
	}

	@Test	
	void testSave() {
		//given		
		ProdutoDto product1 = new ProdutoDto(1234, "Xbox 360", 20D, 5);	
		ProdutoModel produto = new ProdutoModel(1234, "Xbox 360", 20D, 5, null);
		
		//when
		when(produtoRepository.save(Mockito.any(ProdutoModel.class))).thenReturn(produto);
		underTest.save(product1);
		
		//then
		verify(produtoRepository).save(Mockito.any(ProdutoModel.class));
		//the first checks if the method was triggered at least one tome.
		
		assertEquals(produto.toDTO(), product1);
		// the second one checks if the mocked returned value is the same of the argument passed
		
		
		//The commented code only works (for now) if the save method works only with entity and not DTO.
//		ProdutoModel prod = new ProdutoModel(1234, "Xbox 360", 20D, 5, null);		
//		underTest.saveEntity(prod);
//		ArgumentCaptor<ProdutoModel> produtoArgumentCaptor = ArgumentCaptor.forClass(ProdutoModel.class);	
//		verify(produtoRepository).save(produtoArgumentCaptor.capture());		
//		ProdutoModel capturedProduct = produtoArgumentCaptor.getValue();	
//		assertEquals(capturedProduct, prod);
	}

	@Test
	@Disabled
	void testUpdate() {
		
	}

	@Test
	@Disabled
	void testDelete() {
		
	}

	@Test
	@Disabled
	void testFindByName() {
		
	}

}
