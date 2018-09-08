package org.com.zeus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.com.zeus.common.model.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User>{
	
	@Select("select d.permission_key from (select * from user where account=#{account}) a LEFT JOIN user_role b ON a.user_id=b.user_id LEFT JOIN role_permission c on b.role_id =c.role_id LEFT JOIN permission d on c.permission_id=d.permission_id")
	List<String> getPermissionByAccount(User user);
	
	@Select("select role_name from (select * from user where account=#{account}) a LEFT JOIN user_role b ON a.user_id=b.user_id LEFT JOIN role c on b.role_id =c.role_id")
	List<String> getRoleByAccount (User user);

}
