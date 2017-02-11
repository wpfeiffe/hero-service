package com.bpcs.hero.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * JsTree test controller
 */
@Controller
class JstreeController
{
    @RequestMapping("/jstree")
    ModelAndView showJstree()
    {
        ModelAndView mav = new ModelAndView()
        mav.setViewName("jstree-test")
        mav.addObject("pageName", "JSTree Test Page")
        return mav
    }
}
