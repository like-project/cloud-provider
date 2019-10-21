package com.like.cloud.controller;

import com.like.cloud.vo.UserVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HystrixController
 * @Description Hystrix 服务熔断
 * @Author Ke
 * @Date 2019/10/21 15:24
 * @Version 1.0
 */
@RestController
public class HystrixController {


    @GetMapping("/api/hystrix/{id}")
    @HystrixCommand(fallbackMethod = "catchHystrix")
    public String demo(@PathVariable int id){
        if (id == 1){
            return "sucess";
        }
        throw new RuntimeException("id="+id);
    }

    public String catchHystrix(@PathVariable int id){
        return "传入id得值为："+id;
    }



    @GetMapping("/api/feignHystrix/{id}")
    public UserVO test(@PathVariable int id){
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName("return success:成功返回，正确案例");
        return userVO;
    }


}
