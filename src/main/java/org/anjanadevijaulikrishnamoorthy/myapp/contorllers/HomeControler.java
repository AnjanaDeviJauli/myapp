package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeControler {

    @GetMapping(value={"/login"})
    public String login(){
        return "login";
    }
    @GetMapping(value ={"/index"})
    public String home(){ return  "index";}

    @GetMapping("/403")
    public String access(){
        return "403";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

}
