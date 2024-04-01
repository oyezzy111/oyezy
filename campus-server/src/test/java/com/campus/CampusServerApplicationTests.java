package com.campus;

import com.campus.service.AdminService;
import com.campus.service.impl.AdminBossServiceImpl;
import com.dto.AdminBossPageDto;
import com.dto.AdminBossSaveDto;
import com.dto.AdminBossUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampusServerApplicationTests {
    @Autowired
    private AdminBossServiceImpl adminBossService;
    @Autowired
    private AdminService adminService;
    @Test
    void contextLoads() {
        AdminBossSaveDto adminBossSaveDto = new AdminBossSaveDto();
        AdminBossUpdateDto adminBossUpdateDto = new AdminBossUpdateDto();
//        adminBossSaveDto.setAccount("123456");
        //adminBossService.getByName("123");
//        AdminBossPageDto adminBossPageDto = new AdminBossPageDto();
//        adminBossPageDto.setPageSize(10);
//        adminBossSaveDto.setNickname("boss");
        //adminBossSaveDto.setGender("女");
//        adminBossSaveDto.setTelephone("17729094253");
        adminBossUpdateDto.setId(1);
        adminBossUpdateDto.setGender("女");
//        adminBossSaveDto.setAccount("boss111");
       // adminBossService.save(adminBossSaveDto);

        //adminBossService.pageQuery(adminBossPageDto);
        adminBossService.update(adminBossUpdateDto);
        System.out.println(adminBossService.getById(1));
        //adminService.monthShopQuery(1);
        System.out.println("注销成功");
    }

}
