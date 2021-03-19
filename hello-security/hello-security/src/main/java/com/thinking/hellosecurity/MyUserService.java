package com.thinking.hellosecurity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class MyUserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePWD(String pwd) {
        return passwordEncoder.encode(pwd);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> roles = null;

        //those infos should come from DB
        switch (username) {
            case "Yong":
                roles = AuthorityUtils.commaSeparatedStringToAuthorityList("user,admin");
                return new User("Yong", encodePWD("123456"), roles);
            case "Guo":
                roles = AuthorityUtils.commaSeparatedStringToAuthorityList("user");
                return new User("Guo", encodePWD("654321"), roles);
            default:
                return null;
        }
    }
}
