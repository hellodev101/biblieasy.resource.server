package biblieasy.lekker.resource.server.api;

import biblieasy.lekker.resource.server.entity.Role;
import biblieasy.lekker.resource.server.entity.RoleToUserForm;
import biblieasy.lekker.resource.server.entity.User;
import biblieasy.lekker.resource.server.repository.RoleRepo;
import biblieasy.lekker.resource.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleAPIController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepo roleRepo;

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public void addRoleToUser(@RequestParam Long userId,
                               @RequestParam Long id) {
        roleRepo. addRoleToUser(userId, id);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userService.getRoles() );
    }

    @DeleteMapping(path ="/delete/role/{id}")
    public void deleteRole(@PathVariable("id") Long id){
        userService.deleteRole(id);
    }

    @PutMapping(path = "/update/role/{id}")
    public void updateRole(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name){
        userService.updateRole(id, name);

    }

}
