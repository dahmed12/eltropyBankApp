package bank.eltropy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.eltropy.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);
}
