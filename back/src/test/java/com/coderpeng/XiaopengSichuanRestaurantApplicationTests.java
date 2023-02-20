package com.coderpeng;

import com.coderpeng.entity.Menu;
import com.coderpeng.entity.User;
import com.coderpeng.service.IMenuService;
import com.coderpeng.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@SpringBootTest
class XiaopengSichuanRestaurantApplicationTests {

    @Autowired
    private IMenuService iMenuService;

    @Test
    void contextLoads() {
        List<Menu> list = iMenuService.getMenuListByUserId(1L);
        System.out.println(list);
    }

}
