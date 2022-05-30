package it.pegasoft.usersecurity.service;

import it.pegasoft.usersecurity.filter.CustomAuthenticationFilter;
import it.pegasoft.usersecurity.model.Role;
import it.pegasoft.usersecurity.model.User;
import it.pegasoft.usersecurity.repository.RoleRepo;
import it.pegasoft.usersecurity.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    @Override
    public User saveUser(User user) {
        logger.info("Saving new user '{}' to db", user.getUsername());
//        user.setId(new Random().nextLong());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override @Transactional
    public void addRoleToUser(String username, String roleName) {

        logger.info("Adding role '{}' to user '{}'", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRole().add(role);
    }

    @Override
    public User getUser(String username) {
       
        logger.info("Fetching user: {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {

        logger.info("Fetching all user");
        return userRepo.findAll();
    }
    @Transactional
    public void deleteUser(Long id){

        userRepo.deleteUserById(id);
    }

    @Override
    public User findUser(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public Role saveRole(Role role) {
        logger.info("Saving new role '{}' to db", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            logger.error("User '{}' not found", username);
            throw new UsernameNotFoundException("User not found");
        } else{
            logger.info("User found: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    
}
