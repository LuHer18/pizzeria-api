package com.luher.luher_pizzeria.service;

import com.luher.luher_pizzeria.persistence.entity.UserEntity;
import com.luher.luher_pizzeria.persistence.entity.UserRoleEntity;
import com.luher.luher_pizzeria.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    private UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "no encontrado"));
        String[] roles = user.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .toArray(String[]::new);

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(this.grantedAuthorityList(roles))
                .accountLocked(user.getLocked())
                .disabled(user.getDisable())
                .build();
    }

    private String[] getAuthorities(String rol) {
        if ("ADMIN".equals(rol) || "CUSTOMER".equals(rol)) {
            return new String[]{"random_order"};
        }

        return new String[]{};
    }

    private List<GrantedAuthority> grantedAuthorityList(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String rol : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));
            for (String authority:this.getAuthorities(rol)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}
