package com.ritesh.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POST")
@NoArgsConstructor
@Getter
@Setter

public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_id;

	@Column(name = "TITLE", length = 100)
	private String title;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "IMAGE_NAME")
	private String imagename;
	// @Column(name = "ADDED DATE")
	// private Date addedDate;

	@ManyToOne
	@JsonIgnore
	private Category category;

	@ManyToOne
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

}
