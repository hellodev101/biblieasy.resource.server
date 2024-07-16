package biblieasy.lekker.resource.server.services;


import biblieasy.lekker.resource.server.entity.Role;
import biblieasy.lekker.resource.server.entity.User;

import java.util.List;



public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    User findById(long id);
    void deleteUser(Long userId);
    void updateUser(Long userId, String name, String username, String password);
    List<Role> getRoles();
    void deleteRole(Long id);
    void updateRole(Long id, String name);

}
