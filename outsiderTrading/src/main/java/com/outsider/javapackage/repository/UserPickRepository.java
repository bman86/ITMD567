package com.outsider.javapackage.repository;

import com.outsider.javapackage.domain.UserPick;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the UserPick entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPickRepository extends JpaRepository<UserPick, Long>, JpaSpecificationExecutor<UserPick> {

    @Query("select user_pick from UserPick user_pick where user_pick.user.login = ?#{principal.username}")
    List<UserPick> findByUserIsCurrentUser();

}
