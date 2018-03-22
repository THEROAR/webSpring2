package com.example.user.service.impl;

import com.example.common.dao.UUserMapper;
import com.example.common.model.UUser;
import com.example.user.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UUserServiceImpl implements UUserService {

    @Autowired
    UUserMapper userMapper;

    public int updateByPrimaryKeySelective(UUser record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UUser record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public UUser login(String email, String pswd) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("pswd", pswd);
        UUser user = userMapper.login(map);
        return user;
    }


}
