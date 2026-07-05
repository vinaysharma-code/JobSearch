package com.jobSearch.entity;


import com.jobSearch.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "users" )
public class User {

@Id
private ObjectId id ;
@NonNull
@Indexed(unique = true)
private String userName ;
private String password ;
@Indexed(unique = true)
private String email ;
private Role role ;
private boolean isActive ;
private LocalDateTime createdAt ;
private LocalDateTime updatedAt ;

}
