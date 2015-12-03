/**
 * 
 */
package com.evolvingreality.onleave.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * @author Derek Reynolds
 * @since 1.0
 */
@Entity
@Table(name="SECURITY_GROUP_MEMBER")
public class SecurityGroupMember extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@JsonIgnore
    @NotNull
    @ManyToOne(optional = false, targetEntity = User.class)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;   

	@JsonIgnore
    @NotNull
    @ManyToOne(targetEntity = SecurityGroup.class)
    @JoinColumn(name="security_group_id", referencedColumnName="id")
    private SecurityGroup securityGroup;
    
    public SecurityGroupMember() {
    	
    }
    
    public SecurityGroupMember(User user, SecurityGroup securityGroup) {
    	this.user = user;
    	this.securityGroup = securityGroup;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SecurityGroup getSecurityGroup() {
		return securityGroup;
	}

	public void setSecurityGroup(SecurityGroup securityGroup) {
		this.securityGroup = securityGroup;
	}   

	
}
