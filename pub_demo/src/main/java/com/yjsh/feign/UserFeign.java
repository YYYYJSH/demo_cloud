package com.yjsh.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yjsh.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 殷金帅
 * @date 2021-12-16 14:03
 **/
@FeignClient(
        name = "test-project"
)
public interface UserFeign {

        @GetMapping({"/userFeign/query"})
        IPage<User> listRecentRecord( @RequestParam("name") String name, @RequestParam("phone") String phone,
                                      @RequestParam("page")Integer page, @RequestParam("limit")Integer limit);

}
