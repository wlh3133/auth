package cn.king.auth.core.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wlh by 2018-09-06
 *
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
	
	AuthUser findByUsername(String username);
	List<AuthUser> findAll();

}
