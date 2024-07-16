package biblieasy.lekker.resource.server.repository;


import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);



    @Query(value = "SELECT * FROM User ",
            countQuery = "SELECT count(*) FROM User  ORDER BY userId",
            nativeQuery = true)
    Page<Map<String, Object>> pageAllUsers(Long userId, Pageable pageable);





}
