package cn.king.auth.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author wlh by 2018-09-06
 *
 */
@Entity
@Table(name = "auth_user")
public class AuthUser implements UserDetails {

	private static final long serialVersionUID = -903601836008649699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<AuthRole> roles;
	// 该账户是否过期
	private boolean accountExpired;
	// 查询该账户是否被锁定
	private boolean accountLocked;
	// 该账户密码是否过期
	private boolean credentialsExpired;
	// 该账户被删除
	private boolean enabled;

	public AuthUser(String username, String password, List<AuthRole> roles, boolean accountExpired,
			boolean accountLocked, boolean credentialsExpired, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		for (AuthRole role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}