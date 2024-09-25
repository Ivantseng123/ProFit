package com.ProFit.controller.jobs;

import com.ProFit.bean.jobsBean.Jobs;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.service.jobService.IJobsService;
import com.ProFit.service.userService.IUserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//已改
@Controller
@RequestMapping(JobsController.URL)
public class JobsController {
    public static final String URL = "/jobs";

    private final IJobsService jobsService;

    private final IUserService userService;

    public JobsController(IJobsService jobsService, IUserService userService) {
        this.jobsService = jobsService;
        this.userService = userService;
    }

    @GetMapping
    public String index(){
        return "jobsVIEW/jobsList";
    }

    //查詢全部
    @GetMapping("/all")
    public String listJobs(Model model){
        List<Jobs> jobsList = jobsService.findAll();
        model.addAttribute("jobsList", jobsList);
        return "jobsVIEW/jobsList";
    }

    // 單筆查詢
    @GetMapping("/findOne")
    public String showEditForm(@RequestParam("id") Integer id, Model model) {
        Jobs jobs = jobsService.findById(id);
        List<Jobs> jobsList = new ArrayList<>();
        jobsList.add(jobs);
        model.addAttribute("jobsList", jobsList);
        return "jobsVIEW/jobsList";
    }

    // 新增
    @PostMapping(value = "/add")
    public String addJob(@RequestParam("jobsUserId") Integer jobsUserId,
                         @RequestParam("jobsTitle") String jobsTitle,
                         @RequestParam("jobsPostingDate") String jobsPostingDate,
                         @RequestParam("jobsApplicationDeadline") String jobsApplicationDeadline,
                         @RequestParam("jobsDescription") String jobsDescription,
                         @RequestParam("jobsStatus") Byte jobsStatus,
                         @RequestParam("jobsRequiredSkills") String jobsRequiredSkills,
                         @RequestParam("jobsLocation") String jobsLocation,
                         @RequestParam("jobsMaxSalary") Integer jobsMaxSalary,
                         @RequestParam("jobsMinSalary") Integer jobsMinSalary,
                         @RequestParam("jobsWorktime") String jobsWorktime,
                         @RequestParam("jobsNumberOfOpenings") Integer jobsNumberOfOpenings) {
        Users user = userService.getUserInfoByID(jobsUserId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date postingDate = new Date();
        Date deadline = new Date();
        try {
            postingDate = formatter.parse(jobsPostingDate);
            deadline = formatter.parse(jobsApplicationDeadline);
        } catch (ParseException ignore) {
        }
        Jobs jobs = new Jobs();
        jobs.setUsers(user);
        jobs.setJobsTitle(jobsTitle);
        jobs.setJobsPostingDate(postingDate);
        jobs.setJobsApplicationDeadline(deadline);
        jobs.setJobsDescription(jobsDescription);
        jobs.setJobsStatus(jobsStatus);
        jobs.setJobsRequiredSkills(jobsRequiredSkills);
        jobs.setJobsLocation(jobsLocation);
        jobs.setJobsMaxSalary(jobsMaxSalary);
        jobs.setJobsMinSalary(jobsMinSalary);
        jobs.setJobsWorktime(jobsWorktime);
        jobs.setJobsNumberOfOpenings(jobsNumberOfOpenings);
        jobsService.save(jobs);
        return "jobsVIEW/jobsSuccess";
    }

    // 刪除
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        jobsService.delete(id);
        return ResponseEntity.ok().build();
    }

    //導向更新頁面
    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = false) Integer id, Model model){
        if (id != null) {
            model.addAttribute("job", jobsService.findById(id));
        } else {
            model.addAttribute("job", null);
        }
        return "jobsVIEW/jobsForm";
    }

    // 更新
    @PostMapping("/edit")
    public String edit(
                       @RequestParam("jobsId") Integer id,
                       @RequestParam("jobsUserId") Integer jobsUserId,
                       @RequestParam("jobsTitle") String jobsTitle,
                       @RequestParam("jobsPostingDate") String jobsPostingDate,
                       @RequestParam("jobsApplicationDeadline") String jobsApplicationDeadline,
                       @RequestParam("jobsDescription") String jobsDescription,
                       @RequestParam("jobsStatus") Byte jobsStatus,
                       @RequestParam("jobsRequiredSkills") String jobsRequiredSkills,
                       @RequestParam("jobsLocation") String jobsLocation,
                       @RequestParam("jobsMaxSalary") Integer jobsMaxSalary,
                       @RequestParam("jobsMinSalary") Integer jobsMinSalary,
                       @RequestParam("jobsWorktime") String jobsWorktime,
                       @RequestParam("jobsNumberOfOpenings") Integer jobsNumberOfOpenings){
        Users user = userService.getUserInfoByID(jobsUserId);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date postingDate = new Date();
        Date deadline = new Date();
        try {
            postingDate = formatter.parse(jobsPostingDate);
            deadline = formatter.parse(jobsApplicationDeadline);
        } catch (ParseException ignore) {
        }
        Jobs jobs = jobsService.findById(id);
        jobs.setUsers(user);
        jobs.setJobsTitle(jobsTitle);
        jobs.setJobsPostingDate(postingDate);
        jobs.setJobsApplicationDeadline(deadline);
        jobs.setJobsDescription(jobsDescription);
        jobs.setJobsStatus(jobsStatus);
        jobs.setJobsRequiredSkills(jobsRequiredSkills);
        jobs.setJobsLocation(jobsLocation);
        jobs.setJobsMaxSalary(jobsMaxSalary);
        jobs.setJobsMinSalary(jobsMinSalary);
        jobs.setJobsWorktime(jobsWorktime);
        jobs.setJobsNumberOfOpenings(jobsNumberOfOpenings);
        jobsService.update(jobs);
        return "redirect:all";
    }
}

