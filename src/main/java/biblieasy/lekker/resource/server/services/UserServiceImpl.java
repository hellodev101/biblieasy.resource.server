package biblieasy.lekker.resource.server.services;

import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.Role;
import biblieasy.lekker.resource.server.entity.User;
//import biblieasy.lekker.resource.server.repository.LoanRepository;
import biblieasy.lekker.resource.server.repository.RoleRepo;
import biblieasy.lekker.resource.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private final RoleRepo roleRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.info("User found in the database: {}",username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }



    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
    @Override
    public User findById(long id) {
        User user = userRepo.findById(id).get();
        return user;
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepo.existsById(userId);
        if(!exists){
            throw new IllegalStateException(
                    "user with id " + userId + " does not exists");
        }
        userRepo.deleteById(userId);
    }

@Override
    @Transactional
    public void updateUser(Long userId, String name, String username, String password) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + userId + " does not exist"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(user.getName() , name)){
            user.setName(name);
        }

        if (password != null &&
                password.length() > 0 &&
                !Objects.equals(user.getPassword() , password)){
            user.setPassword(passwordEncoder.encode(password));
        }

        if(username != null &&
                username.length() > 0 &&
                !Objects.equals(user.getUsername(), username)){
            User userOptional = userRepo
                    .findByUsername(username);
            user.setUsername(username);
        }
    }


    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }


    public List<Role> getRoles(String roleName) {
        log.info("Fetching all users");
     return (List<Role>) roleRepo.findByName(roleName);
    }

    @Override
    public void deleteRole(Long id) {
        boolean exists = roleRepo.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "user with id " + id + " does not exists");
        }
        roleRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void updateRole(Long id, String name) {
        Role role = roleRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "role with id " + id + " does not exist"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(role.getName() , name)){
            role.setName(name);
        }
    }


}

