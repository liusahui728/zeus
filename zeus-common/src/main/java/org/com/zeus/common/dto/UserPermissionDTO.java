package org.com.zeus.common.dto;

import lombok.Data;

@Data
public class UserPermissionDTO {
	private Long userId;
	private String userName;
	private String permission;
	
	
}
