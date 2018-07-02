package com.blog.sjh.login.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements Serializable{
	
	private static final long serialVersionUID = -4482234764884103078L;
	private String id; 
	private String userName;
	private String passWord;
	private String salt;
	
}
