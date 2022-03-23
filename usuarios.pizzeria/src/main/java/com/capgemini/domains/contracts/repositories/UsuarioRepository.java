package com.capgemini.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.capgemini.domains.entities.Usuario;

@RepositoryRestResource(exported = false)
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByNombre(String nombre);
	List<Usuario> findByNombreStartingWith(String prefijo);

	<T> List<T> findByIdUsuarioIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdUsuarioIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdUsuarioIsNotNull(Pageable pageable, Class<T> type);
}
