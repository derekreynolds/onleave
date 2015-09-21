/**
 * 
 */
package com.evolvingreality.onleave.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Derek Reynolds
 * @since 1.0
 */
@Table(name="SECURITY_GROUP_AUTHORITY")
@Entity
public class SecurityGroupAuthority extends AbstractAuditingEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @NotNull
    @Size(max = 100)
    @Column(name = "authority")
	private String authority;

    @NotNull
    @ManyToOne(targetEntity = SecurityGroup.class)
    @JoinColumn(name="security_group_id", referencedColumnName="id")
    private SecurityGroup securityGroup;   
    
    
	public SecurityGroup getSecurityGroup() {
		return securityGroup;
	}

	public void setSecurityGroup(SecurityGroup securityGroup) {
		this.securityGroup = securityGroup;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
