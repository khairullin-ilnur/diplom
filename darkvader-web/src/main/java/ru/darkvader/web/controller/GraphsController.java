package ru.darkvader.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Khairullin on 14/03/16.
 *
 * @author Khairullin
 */

@Controller
@RequestMapping(value = "/graphs")
public class GraphsController {

    @RequestMapping(method = RequestMethod.GET)
    public String graphs() {
        return "graphs";
    }

}
