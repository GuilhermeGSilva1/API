package api_teste.ds.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import api_teste.ds.models.User;
import api_teste.ds.repositories.UserRepository;
import api_teste.ds.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return new UserSpringSecurity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getProfiles()
        );
    }
}