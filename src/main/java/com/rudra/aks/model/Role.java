package com.rudra.aks.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleid;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserBO> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleid"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilegeid"))
    private Collection<Privilege> privileges;

    private String name;

    public Role() {
        super();
    }

    public Role(final String name) {
        super();
        this.name = name;
    }

    //

    public Long getId() {
        return roleid;
    }

    public void setId(final Long id) {
        this.roleid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<UserBO> getUsers() {
        return users;
    }

    public void setUsers(final Collection<UserBO> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
