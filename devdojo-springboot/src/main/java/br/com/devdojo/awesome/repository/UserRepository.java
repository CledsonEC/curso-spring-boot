package br.com.devdojo.awesome.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devdojo.awesome.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByUsername(String username);
}
