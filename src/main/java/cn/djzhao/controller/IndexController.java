package cn.djzhao.controller;

import cn.djzhao.model.Message;
import cn.djzhao.service.MessageService;
import cn.djzhao.util.PageBean;
import cn.djzhao.util.TableData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class IndexController {
    @Autowired
    private MessageService messageService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping({"index.html", "/", "index"})
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("login");
        Object isLogin = session.getAttribute("hasLogin");
        if (isLogin != null) {
            mv.setViewName("index");
        }
        return mv;
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, HttpSession session) {
        if ("ygf".equals(username) && "ygf123456!".equals(password)) {
            session.setAttribute("hasLogin", username);
        }
        return "redirect:/";
    }

    /**
     * 提交留言
     *
     * @param message
     * @return
     */
    @PostMapping("submitMessage")
    public ModelAndView submitMessage(Message message) {
        ModelAndView mv = new ModelAndView("success");
        if (StringUtils.isEmpty(message.getCompanyName())) {
            mv.addObject("msg", "Did not fill in the company name");
        } else if (StringUtils.isEmpty(message.getPersonalName())) {
            mv.addObject("msg", "No personal name");
        } else if (StringUtils.isEmpty(message.getEmail())) {
            mv.addObject("msg", "No email address");
        } else if (StringUtils.isEmpty(message.getMobile())) {
            mv.addObject("msg", "No mobile phone number");
        } else if (StringUtils.isEmpty(message.getContent())) {
            mv.addObject("msg", "Did not fill in the message content");
        } else {
            messageService.submitMessage(message);
            return mv;
        }
        mv.setViewName("error");
        return mv;
    }

    @GetMapping("/getTableData")
    @ResponseBody
    public TableData<Message> getTableData(PageBean pageBean) {
        if (pageBean.getLimit() == null) {
            pageBean.setLimit(10);
        }
        if (pageBean.getOffset() == null) {
            pageBean.setLimit(0);
        }
        TableData<Message> tableData = messageService.getTableData(pageBean);
        return tableData;
    }

    // ==========API==========
    @GetMapping("/getMessageList")
    @ResponseBody
    public List<Message> getMessageList() {
        return messageService.getMessage();
    }


}
