package net.zhenghao.zh.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysPageController {

    /**
     * 页面跳转
     * @param module
     * @return
     */
    /*@RequestMapping("{module}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        //return module + "/" +url + ".html";
        return module + "/" +url + ".html";
    }*/

    @RequestMapping("{module}.html")
    public String page(@PathVariable("module") String module) {
        return module;
    }
}
