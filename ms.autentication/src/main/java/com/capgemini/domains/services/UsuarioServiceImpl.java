package com.capgemini.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.domains.contracts.repositories.UsuarioRepository;
import com.capgemini.domains.contracts.services.UsuarioService;
import com.capgemini.domains.entities.Usuario;
import com.capgemini.exceptions.DuplicateKeyException;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.exceptions.NotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private UsuarioRepository dao;
	
	public UsuarioServiceImpl(UsuarioRepository dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Usuario> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Iterable<Usuario> getAll(Sort sort) {
		return dao.findAll(sort);
	}
	@Override
	public Page<Usuario> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByIdUsuarioIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByIdUsuarioIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByIdUsuarioIsNotNull(pageable, type);
	}

	@Override
	public Usuario getOne(Integer id) throws NotFoundException {
		var item = dao.findById(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}
	
	@Override
	public Usuario add(Usuario item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdUsuario()).isPresent())
			throw new DuplicateKeyException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public Usuario change(Usuario item) throws NotFoundException, InvalidDataException  {
		if(item == null)
			throw new IllegalArgumentException();
		if(dao.findById(item.getIdUsuario()).isEmpty())
			throw new NotFoundException();
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		return dao.save(item);
	}
	@Override
	public void delete(Usuario item) {
		if(item == null)
			throw new IllegalArgumentException();
		deleteById(item.getIdUsuario());
		
	}
	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
