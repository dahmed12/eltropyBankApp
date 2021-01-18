package bank.eltropy.repository;


import org.springframework.stereotype.Service;

import bank.eltropy.domain.user.CreateUserClient;
import bank.eltropy.domain.user.User;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
class CreateUserPostgresClient implements CreateUserClient {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }
}
