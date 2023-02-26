package com.ritesh.blog.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Enitity-creates the User class as an entity
@Entity
// we can use @Table if we want to name table other than the name of the class
@Table(name = "Users")

//using lombok for constructors--for creating the object of users

//Spring boot Lombok is the tool of the java library that was used to generate 
//code for minimizing the unused code. This library is replacing the unused code 
//by using annotation. It will replace a number of annotations like the setter 
//and getter method, equals, hashcode, constructor, etc.
@NoArgsConstructor

@Setter
@Getter
public class User implements UserDetails {

	// for making a primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// we use column attribution for additional properties
	@Column(name = "User_Name", nullable = false, length = 100)
	private String name;
	@Column(name = "Email", nullable = false, length = 100)
	private String email;
	@Column(name = "Password", nullable = false)
	private String password;
	@Column(name = "About", nullable = true)
	private String about;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

	@OneToMany(mappedBy = "usercomment", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = @JoinColumn(name ="user",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name= "role",referencedColumnName = "id"))
	private Set<Role> roles =new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
	List<SimpleGrantedAuthority> authorities =	this.roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
