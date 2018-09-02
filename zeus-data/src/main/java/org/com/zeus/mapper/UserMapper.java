package org.com.zeus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.com.zeus.common.base.entity.BaseResullt;
import org.com.zeus.common.dto.UserPermissionDTO;
import org.com.zeus.common.model.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User>{
	
	@Select("select d.permission from (select * from user where username=#{username}) a LEFT JOIN user_role b ON a.id=b.user_id LEFT JOIN role_permission c on b.role_id =c.role_id LEFT JOIN permission d on c.permission_id=d.id")
	List<String> getPermissionByUsername (User user);
	
	@Select("select role_name from (select * from user where username=#{username}) a LEFT JOIN user_role b ON a.id=b.user_id LEFT JOIN role c on b.role_id =c.id")
	List<String> getRoleByUsername (User user);

}
