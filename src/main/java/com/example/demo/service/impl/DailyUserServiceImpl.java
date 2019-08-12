package com.example.demo.service.impl;

import com.example.demo.dao.mapper.DailyUserMapper;
import com.example.demo.dao.pojo.DailyUser;
import com.example.demo.service.DailyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class DailyUserServiceImpl implements DailyUserService {
    @Autowired
    private DailyUserMapper dailyUserMapper;

    @Override
    public DailyUser queryUser(String username, String password) throws SQLException {
        return dailyUserMapper.queryUser(username, password);
    }

    @Override
    public List<DailyUser> queryAll() throws SQLException {
        return dailyUserMapper.queryAll();
    }

    @Override
    public void changePassword(String password, Integer uid) throws SQLException {
        dailyUserMapper.changePassword(password, uid);
    }

}
