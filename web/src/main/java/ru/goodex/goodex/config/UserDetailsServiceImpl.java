package ru.goodex.goodex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.goodex.goodex.entity.Users;
import ru.goodex.goodex.repo.UsersRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByUserName(username);
        if(user == null) throw new UsernameNotFoundException("Could not find user");
        return new ru.goodex.goodex.config.UserDetails(user);
    }
}
