package com.example.common.dao;

import com.example.common.model.UUser;

import java.util.List;
import java.util.Map;

public interface UUserMapper {

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

	UUser login(Map<String, Object> map);



}