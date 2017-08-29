package com.rudra.aks.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CurrentUser extends org.springframework.security.core.userdetails.User{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserBO user;

    public CurrentUser(UserBO user) {
        super(user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));
        this.user = user;
    }

    public UserBO getUser() {
        return user;
    }

    public int getUserId() {
        return user.getUserid();
    }

    public Collection<Role> getRoles() {
        return user.getRoles();
    }

    private final static Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private final static List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }
        
        return privileges;
    }

    private final static List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    
    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
