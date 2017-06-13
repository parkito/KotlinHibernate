package com.roommate.basecontrol.service.conf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Artem Karnov @date 13.06.2017.
 *         artem.karnov@t-systems.com
 */

@Controller
public class HomeController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home() {
        return "redirect:/swagger-ui.html";
    }

}
