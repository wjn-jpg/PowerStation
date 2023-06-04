package com.ntdq.power_station.build.common.repository.rest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController implements InitializingBean {

//    @Autowired
//    private AdminRepository adminRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println(adminRepository);
//        List<Admin> all = adminRepository.findAll();
//        System.out.println(all);
    }
}
