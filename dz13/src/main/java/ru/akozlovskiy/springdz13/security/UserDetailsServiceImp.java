package ru.akozlovskiy.springdz13.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz13.domain.User;
import ru.akozlovskiy.springdz13.domain.repository.UserRepository;

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
			User user = userOpt.get();

			UserBuilder builder = null;

			builder = org.springframework.security.core.userdetails.User.withUsername(login);
			String passwordEncode = new BCryptPasswordEncoder().encode(user.getPassword());
			builder.password(passwordEncode);
			builder.roles(user.getRole());

			return builder.build();
		}).orElseThrow(() -> new UsernameNotFoundException("User not found."));
	}
}