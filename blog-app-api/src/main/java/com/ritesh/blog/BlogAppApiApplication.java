package com.ritesh.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.bytebuddy.asm.Advice.This;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	public void run(String... args) throws Exception{
		System.out.println(this.passwordEncoder.encode("abcdef"));
	}

}

// note:-this is the main package from where the execution of the program starts
// program starts executing from the main function