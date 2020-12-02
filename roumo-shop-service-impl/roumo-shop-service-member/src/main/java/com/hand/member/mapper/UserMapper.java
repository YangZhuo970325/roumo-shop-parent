package com.hand.member.mapper;

import com.hand.member.mapper.entity.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

public interface UserMapper {

    //@Insert("INSERT INTO `roumo_member` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, null, '1', null, null, null);")
    @Insert("INSERT INTO `roumo_member` VALUES (null,#{mobile}, 'yangzhuo-hd@139.com', #{password}, 'yz', null, null, null, null, '1', null, null, null);")
    int register(UserDo userEntity);

    @Select("SELECT * FROM `roumo_member` WHERE MOBILE=#{mobile};")
    UserDo existMobile(@Param("mobile") String mobile);

    @Select("SELECT  USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USERNAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID FROM `roumo_member` WHERE MOBILE=#{arg0} and password=#{arg1};")
    UserDo login(@Param("mobile") String mobile, @Param("password") String password);

    @Select("SELECT * FROM `roumo_member` WHERE user_Id=#{userId}")
    UserDo findByUserId(@Param("userId") Long userId);

}
