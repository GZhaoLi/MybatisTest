package com.gui.dao;

import com.gui.domain.QueryVo;
import com.gui.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    void saveUser(User user);
    void userUpdate(User user);
    void userDelete(Integer userId);
    User findById(Integer userId);
    List<User>findByName(String username);
    int findTotal();
    /*
    * 根据queryVo中的条件模糊查询
    * */
    List<User> findUserByVo(QueryVo vo);
    /*
    *根据传入的参数条件，可能有用户名，可能有性别，可能都有或都没有
    * */
    List<User> findUserByCondition(User user);
    /*
    * 根据queryVo中的id集合，查询用户信息
    * */
    List<User> findUserInIds(QueryVo vo);
}
