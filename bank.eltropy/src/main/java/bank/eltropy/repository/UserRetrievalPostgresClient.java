package bank.eltropy.repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import bank.eltropy.repository.UserRepository;
import bank.eltropy.repository.UserResponse;
import bank.eltropy.domain.user.User;
import bank.eltropy.domain.user.UserRetrievalClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class UserRetrievalPostgresClient implements UserRetrievalClient {
	private final UserRepository userRepository;

	@Override
	public User findById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public List<UserResponse> findAllUser() {
		List<User> users = userRepository.findAll();
		return users.stream().map(User::generateResponse).collect(Collectors.toList());
	}
}
