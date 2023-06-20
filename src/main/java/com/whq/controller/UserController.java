package com.whq.controller;

import com.whq.domain.User;
import com.whq.Utils.PageUtil;
import com.whq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(@RequestParam Map<String, String> map, Model model) {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        List<User> users = userService.findAll();
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 2, users);
        model.addAttribute("users", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);

        return "index";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/user/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());

        return "create_user";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user) {
        userService.save(user);

        return "redirect:/";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "edit_user";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);

        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/user/findByName")
    public String findByName(@RequestParam Map<String, String> map,Model model) {
        String keywords = map.get("keywords");

        map.put("currentPage", "1");

        List<User> users = userService.findByName(keywords);
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 2, users);
        model.addAttribute("users", pageInfo.getList());
        model.addAttribute("keywords", keywords);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }
}
