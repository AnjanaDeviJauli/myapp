package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeControler {

    @GetMapping(value={"/index"})
    public String login(){
        return "index";
    }
}
