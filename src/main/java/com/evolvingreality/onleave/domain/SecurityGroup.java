/**
 * 
 */
package com.evolvingreality.onleave.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * @author Derek Reynolds
 * @since 1.0
 */
@Table(name="SECURITY_GROUP")
@Entity
public class SecurityGroup extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @NotNull
    @Size(max = 100)
    @Column(name = "group_name")
	private String groupName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="securityGroup")
    private List<SecurityGroupAuthority> authorities = new LinkedList<SecurityGroupAuthority>();
    
    
    
	public List<SecurityGroupAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<SecurityGroupAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
