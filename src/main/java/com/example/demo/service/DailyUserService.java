package com.example.demo.service;

import com.example.demo.dao.pojo.DailyUser;

import java.sql.SQLException;
import java.util.List;

public interface DailyUserService {
    /**
     * 用户信息查询
     * @return
     * @throws SQLException
     */
    DailyUser queryUser(String username, String password) throws SQLException;

    List<DailyUser> queryAll() throws SQLException;

    void changePassword(String password, Integer uid) throws SQLException;
}
