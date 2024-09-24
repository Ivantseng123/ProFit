package com.ProFit.controller.majors;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;
import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.service.majorService.IUserMajorService;
import com.ProFit.service.userService.IUserService;
import com.ProFit.service.majorService.IMajorService;

@Controller
@RequestMapping("/userMajor")
public class UserMajorController {

    @Autowired
    private IUserMajorService userMajorService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMajorService majorService;

    @GetMapping("/")
    public String listAllUserMajors(Model model) throws SQLException {
        List<UserMajorBean> allUserMajors = userMajorService.findAllUserMajors();
        Map<Integer, String> allUsers = userMajorService.getAllUsers();
        Map<Integer, String> allMajors = userMajorService.getAllMajors();

        model.addAttribute("allUserMajors", allUserMajors);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allMajors", allMajors);

        return "majorsVIEW/AllUserMajorList";
    }

    @GetMapping("/userMajors")
    public String listUserMajors(@RequestParam("userId") Integer userId, Model model) throws SQLException {
        Users user = userService.getUserInfoByID(userId);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "redirect:/userMajor/";
        }

        List<UserMajorBean> userMajors = userMajorService.findMajorsByUserId(userId);
        Map<Integer, String> allMajors = userMajorService.getAllMajors();
        Map<Integer, String> availableMajors = new HashMap<>(allMajors);

        for (UserMajorBean userMajor : userMajors) {
            availableMajors.remove(userMajor.getId().getMajor().getMajorId());
        }

        model.addAttribute("userMajors", userMajors);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("availableMajors", availableMajors);

        return "majorsVIEW/UserMajorList";
    }

    @GetMapping("/majorUsers")
    public String listMajorUsers(@RequestParam("majorId") Integer majorId, Model model) {
        MajorBean major = majorService.findMajorById(majorId);
        if (major == null) {
            model.addAttribute("error", "Major not found");
            return "redirect:/userMajor/";
        }

        List<UserMajorBean> majorUsers = userMajorService.findUsersByMajorId(majorId);
        model.addAttribute("majorUsers", majorUsers);
        model.addAttribute("majorId", majorId);
        model.addAttribute("majorName", major.getMajorName());

        return "majorsVIEW/MajorUserList";
    }

    @PostMapping("/add")
    public String addUserMajor(@RequestParam("userId") Integer userId, 
                               @RequestParam("majorId") Integer majorId,
                               RedirectAttributes redirectAttributes) {
        Users user = userService.getUserInfoByID(userId);
        MajorBean major = majorService.findMajorById(majorId);

        if (user == null || major == null) {
            redirectAttributes.addFlashAttribute("error", "User or Major not found");
            return "redirect:/userMajor/";
        }

        UserMajorPK id = new UserMajorPK(user, major);
        UserMajorBean userMajor = new UserMajorBean(id);

        UserMajorBean insertedUserMajor = userMajorService.insertUserMajor(userMajor);
        if (insertedUserMajor == null) {
            redirectAttributes.addFlashAttribute("error", "Failed to add User-Major association");
        } else {
            redirectAttributes.addFlashAttribute("message", "User-Major association added successfully");
        }

        return "redirect:/userMajor/userMajors?userId=" + userId;
    }

    @GetMapping("/delete")
    public String deleteUserMajor(@RequestParam("userId") Integer userId, 
                                  @RequestParam("majorId") Integer majorId,
                                  RedirectAttributes redirectAttributes) {
        boolean deleted = userMajorService.deleteUserMajor(userId, majorId);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "User-Major association deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to delete User-Major association");
        }

        return "redirect:/userMajor/";
    }
}