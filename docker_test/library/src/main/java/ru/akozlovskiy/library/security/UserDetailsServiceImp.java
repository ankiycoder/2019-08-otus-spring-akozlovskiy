package ru.akozlovskiy.library.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.library.domain.User;
import ru.akozlovskiy.library.domain.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private UserRepository userRepository;

	public UserDetailsServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Optional<User> userOpt = userRepository.findByLogin(login);

		return userOpt.map(u -> {
			UserBuilder builder = null;
			builder = org.springframework.security.core.userdetails.User.withUsername(login);			
			builder.password(u.getPassword());
			builder.roles(u.getRole());
			return builder.build();
		}).orElseThrow(() -> new UsernameNotFoundException("User not found."));
	}
}