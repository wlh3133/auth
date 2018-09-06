package cn.king.auth.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author wlh by 2018-09-06
 *
 */
@Entity
@Table(name = "auth_role")
public class AuthRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String commet;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommet() {
		return commet;
	}
	public void setCommet(String commet) {
		this.commet = commet;
	}
	
	
}