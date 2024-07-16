package biblieasy.lekker.resource.server.repository;

import biblieasy.lekker.resource.server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    //return role name from db
    Role findByName(String name);

    @Transactional
    @Modifying
    @Query( value = "INSERT INTO `bookdb`.`user_roles` (`User_userId`, `roles_id`) VALUES (:User_userId, :roles_id)", nativeQuery = true)
    void addRoleToUser(
            @Param("User_userId") Long User_userId,
            @Param("roles_id") Long roles_id);

}

