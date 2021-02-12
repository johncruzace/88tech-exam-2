package johncruz.tech.exam.minibank.service.impl;

import johncruz.tech.exam.minibank.model.UserSecurityDetails;
import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(s);
        user.orElseThrow(()-> new UsernameNotFoundException("Not Found : " + s));

        UserSecurityDetails localUserDetails = user.map(UserSecurityDetails::new).get();
        logger.info("encoded password -> {}",passwordEncoder.encode(localUserDetails.getPassword()));
        logger.info("authorities -> {}",localUserDetails.getAuthorities());
        return localUserDetails;
    }
}
