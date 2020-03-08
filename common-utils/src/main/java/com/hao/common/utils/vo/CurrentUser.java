package com.hao.common.utils.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser implements Serializable {

	private static final long serialVersionUID = -3635129792340151916L;
	
	@JsonIgnore
    private String password;
    private String username;
    @JsonIgnore
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String userId;
    private String avatar;
    private String email;
    private String mobile;
    private String sex;
    private String deptId;
    private String deptName;
    private String roleId;
    private String roleName;
    private String tenantCode;
    @JsonIgnore
    private Date lastLoginTime;
    private String remark;
    private String status;
}
