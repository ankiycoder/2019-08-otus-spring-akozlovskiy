package ru.akozlovskiy.springdz12.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz12.domain.User;
import ru.akozlovskiy.springdz12.domain.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Optional<User> userOpt = userRepository.findByLogin(login);

		if (userOpt.isPresent()) {
			
			System.out.println("***User is found!");
			
			User user = userOpt.get();

			UserBuilder builder = null;

			builder = org.springframework.security.core.userdetails.User.withUsername(login);
			String passwordEncode = new BCryptPasswordEncoder().encode(user.getPassword());
			builder.password(passwordEncode);
			builder.roles(user.getRole());

			return builder.build();
		}
		throw new UsernameNotFoundException("User not found.");
	}
}