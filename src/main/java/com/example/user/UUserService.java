package com.example.user;


import com.example.common.model.UUser;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface UUserService {



    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);
    
    UUser login(String email, String pswd);


}
