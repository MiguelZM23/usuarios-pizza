package com.example.domains.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.application.dtos.UsuarioDTO;
import com.capgemini.domains.contracts.repositories.UsuarioRepository;
import com.capgemini.domains.entities.Funcion;
import com.capgemini.domains.entities.Usuario;
import com.capgemini.domains.services.UsuarioServiceImpl;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

class UsuarioServiceImplTest {
	List<Usuario> listado;
	List<Funcion> funciones;
	UsuarioRepository dao;
	
	@BeforeEach
	void setUp() throws Exception {
		listado = new ArrayList<Usuario>();
		listado.add(new Usuario(1, "pepe", "Gonzalez", "Guerra", "123456", "123456789", "pepe@pepe.com", funciones));
		listado.add(new Usuario(2, "Maria", "Gonzalez", "Guerra", "123456", "123456789", "maria@maria.com", funciones));
		dao = mock(UsuarioRepository.class);
	}

	@Test
	void testGetAll() {
		when(dao.findAll()).thenReturn(listado);
		var srv = new UsuarioServiceImpl(dao);
		
		var rslt = srv.getAll();
		
		assertNotNull(rslt);
		assertEquals(2, rslt.size());
	}

	@Test
	void testGetOne() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new UsuarioServiceImpl(dao);
		
		var rslt = srv.getOne(1);
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdUsuario());
	}
	@Test
	void testGetOneNotFound() throws NotFoundException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.getOne(1));
	}

	@Test
	void testAdd() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new UsuarioServiceImpl(dao);
		
		var rslt = srv.add(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdUsuario());
	}
	@Test
	void testAddNull() {
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.add(null));
	}
	@Test
	void testAddDuplicateKey() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(DuplicateKeyException.class, () -> srv.add(listado.get(0)));
	}
	/*
	@Test
	void testAddInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.add(new Usuario(1)));
	}*/

	@Test
	void testChange() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		when(dao.save(any())).thenReturn(listado.get(0));
		var srv = new UsuarioServiceImpl(dao);
		
		var rslt = srv.change(listado.get(0));
		assertNotNull(rslt);
		assertEquals(1, rslt.getIdUsuario());
	}

	@Test
	void testChangeNull() {
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(IllegalArgumentException.class, () -> srv.change(null));
	}
	@Test
	void testChangeNotFound() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(NotFoundException.class, () -> srv.change(listado.get(0)));
	}
	/*
	@Test
	void testChangeInvalidData() throws NotFoundException, DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(listado.get(0)));
		var srv = new UsuarioServiceImpl(dao);
		
		assertThrows(InvalidDataException.class, () -> srv.change(new Usuario(1)));
	}*/

	/*
	@Test
	void testDelete() {
		doNothing().when(dao).deleteById(1);
		var srv = new UsuarioServiceImpl(dao);
		srv.delete(new Usuario(1));
		verify(dao).deleteById(1);
	}*/

	@Test
	void testDeleteById() {
		doNothing().when(dao).deleteById(1);
		var srv = new UsuarioServiceImpl(dao);
		srv.deleteById(1);
		verify(dao).deleteById(1);
	}

}
