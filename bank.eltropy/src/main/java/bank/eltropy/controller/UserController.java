package bank.eltropy.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

	private final UserFacade userFacade;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody UserRequest userRequest) {
		CreateUserCommand createUserCommand = createUserCommand(userRequest);
		userFacade.createUserWithNewBankAccount(createUserCommand);
	}

	private CreateUserCommand createUserCommand(UserRequest userRequest) {
		return CreateUserCommand.builder().login(userRequest.getLogin()).firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName()).build();
	}
}