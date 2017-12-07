package com.konkerlabs.platform.registry.core.common.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "roles")
@Data
@Builder
public class Role {

	@Id
	private String id;
	private String name;
	
	@DBRef
	private List<Privilege> privileges;
}
