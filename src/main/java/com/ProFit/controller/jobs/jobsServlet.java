
//Servlet 撰寫步驟
//
//  1.繼承 HttpServlet 類別：
//  所有的 Servlet 都必須繼承 HttpServlet 類別，這個類別提供了處理 HTTP 請求和響應的基本方法。

//  2.覆寫 doGet() 或 doPost() 方法：
//  doGet() 方法用於處理 GET 請求，doPost() 方法用於處理 POST 請求。
//  在這些方法中，你會寫入處理請求的邏輯，並使用 HttpServletResponse 對象生成回應。

//  3.部署 Servlet：
//  將 Servlet 類別編譯成 class 檔案，並將其打包成 WAR 檔案。
//  將 WAR 檔案部署到 Web 容器（如 Tomcat、Jetty）中。

package com.ProFit.controller.jobs;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ProFit.bean.Jobs;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.usersDao.HUserDao;
import com.ProFit.dao.usersDao.IHUserDao;
import com.ProFit.hibernateutil.HibernateUtil;
import com.ProFit.jobService.JobsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//指定 URL 路徑為 /jobsServlet
//會告诉 Web 容器（如 Tomcat），此 Servlet 類（class jobsServlet）应当映射到 /jobsServlet，
//使得訪問URL 路徑时，Web 容器创建 jobsServlet的實例来處理request
@WebServlet("/jobsServlet")
public class jobsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    private JobsService jobsService;
    private IHUserDao userDao;
    private Session session;
    
    
    // init方法在初始化時
    @Override
	public void init() {
    	session = HibernateUtil.getSessionFactory().openSession();
        jobsService = new JobsService(session);
        userDao = new HUserDao(session); //Users表
    }
    

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");// 从request中獲得 變數"action"的参数
        //getParameter就是獲取?action=listJobs中listJobs的參數

        if (action == null) {
            action = "list";
        }

        // 根据action的值选择执行的操作
        switch (action) {
        case "add":
            addJob(request, response);
            break;
        case "delete":
            deleteJob(request, response);
            break;
        // 如果 action 為 update，調用 updateJob 方法（updates）來更新
        case "update":
            updateJob(request, response);
            break;
        // 如果 action 是view，調用 showEditForm 方法(findById)来展示單一
        case "view":
            showEditForm(request, response);
            break;
        // 如果 'action' 不是以上任何一个值，调用 listJobs 方法(findAl)来展示全部列表
        case "edit":
            editjob(request, response);
            break;

        default:// 上面case都不符合的話，就會執行default
            listJobs(request, response);
            break;

        }
    }

    // 查詢
    private void listJobs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Jobs> jobsList = jobsService.findAll();

        request.setAttribute("jobsList", jobsList);
        request.getRequestDispatcher("/jobsVIEW/jobsList.jsp").forward(request, response);
    }

    // 單筆查詢
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Jobs existingJob = jobsService.findById(id);

        // 這裡使用JobEdit，if判斷id如果有值就做什麼做什麼，空值就不用做事
        List<Jobs> jobsList = new ArrayList<>();// 把集合（jobsList）設變數實體化
        jobsList.add(existingJob);// 將existingJob加入到集合（jobsList）裡

        request.setAttribute("jobsList", jobsList);
        request.getRequestDispatcher("/jobsVIEW/jobsList.jsp").forward(request, response);
    }

    // 新增
    private void addJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jobs newJob = new Jobs();
        // Set job properties from request parameters
//        newJob.setJobsUserId(Integer.parseInt(request.getParameter("jobsUserId")));
        Users users = userDao.getUserInfoByID(Integer.parseInt(request.getParameter("jobsUserId")));
        newJob.setUsers(users);
        newJob.setJobsTitle(request.getParameter("jobsTitle"));
        newJob.setJobsPostingDate(Date.valueOf(request.getParameter("jobsPostingDate")));
        newJob.setJobsApplicationDeadline(Date.valueOf(request.getParameter("jobsApplicationDeadline")));
        newJob.setJobsDescription(request.getParameter("jobsDescription"));
        newJob.setJobsStatus(Byte.parseByte(request.getParameter("jobsStatus")));
        newJob.setJobsRequiredSkills(request.getParameter("jobsRequiredSkills"));
        newJob.setJobsLocation(request.getParameter("jobsLocation"));
        newJob.setJobsMaxSalary(Integer.parseInt(request.getParameter("jobsMaxSalary")));
        newJob.setJobsMinSalary(Integer.parseInt(request.getParameter("jobsMinSalary")));
        newJob.setJobsWorktime(request.getParameter("jobsWorktime"));
        newJob.setJobsNumberOfOpenings(Integer.parseInt(request.getParameter("jobsNumberOfOpenings")));
        System.out.println("newJob====" + newJob.getJobsTitle());
        System.out.println(newJob);
        jobsService.save(newJob);

        // request.setAttribute("newJobId", id);
        // request.getRequestDispatcher("/jobsVIEW/jobsSuccess.jsp").forward(request,
        // response);//getRequest帶著Request過去
        response.sendRedirect("jobsVIEW/jobsSuccess.jsp");// sendRedirect什麼東西都不帶
    }

    // 更新
    private void updateJob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("jobsId"));
        Jobs jobs = jobsService.findById(id);
        Users users = userDao.getUserInfoByID(Integer.parseInt(request.getParameter("jobsUserId")));
        jobs.setUsers(users);//USER表
//      jobs.setJobsUserId(Integer.parseInt(request.getParameter("jobsUserId")));
        jobs.setJobsTitle(request.getParameter("jobsTitle"));
        jobs.setJobsPostingDate(Date.valueOf(request.getParameter("jobsPostingDate")));
        jobs.setJobsApplicationDeadline(Date.valueOf(request.getParameter("jobsApplicationDeadline")));
        jobs.setJobsDescription(request.getParameter("jobsDescription"));
        jobs.setJobsStatus(Byte.parseByte(request.getParameter("jobsStatus")));
        jobs.setJobsRequiredSkills(request.getParameter("jobsRequiredSkills"));
        jobs.setJobsLocation(request.getParameter("jobsLocation"));
        jobs.setJobsMaxSalary(Integer.parseInt(request.getParameter("jobsMaxSalary")));
        jobs.setJobsMinSalary(Integer.parseInt(request.getParameter("jobsMinSalary")));
        jobs.setJobsWorktime(request.getParameter("jobsWorktime"));
        jobs.setJobsNumberOfOpenings(Integer.parseInt(request.getParameter("jobsNumberOfOpenings")));

//        Map<String, Object> updates = new HashMap<>();
       
//        updates.put("jobs_user_id", Integer.parseInt(request.getParameter("jobsUserId")));
//        updates.put("jobs_title", request.getParameter("jobsTitle"));
//        updates.put("jobs_posting_date", Date.valueOf(request.getParameter("jobsPostingDate")));
//        updates.put("jobs_application_deadline", Date.valueOf(request.getParameter("jobsApplicationDeadline")));
//        updates.put("jobs_description", request.getParameter("jobsDescription"));
//        updates.put("jobs_status", Byte.parseByte(request.getParameter("jobsStatus")));
//        updates.put("jobs_required_skills", request.getParameter("jobsRequiredSkills"));
//        updates.put("jobs_location", request.getParameter("jobsLocation"));
//        updates.put("jobs_max_salary", Integer.parseInt(request.getParameter("jobsMaxSalary")));
//        updates.put("jobs_min_salary", Integer.parseInt(request.getParameter("jobsMinSalary")));
//        updates.put("jobs_worktime", request.getParameter("jobsWorktime"));
//        updates.put("jobs_number_of_openings", Integer.parseInt(request.getParameter("jobsNumberOfOpenings")));
        jobsService.update(jobs);
        response.sendRedirect("jobsServlet?action=list");// sendRedirect什麼東西都不帶
    }

    // 刪除
    private void deleteJob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        jobsService.delete(id);
        response.sendRedirect("jobsServlet?action=list");// sendRedirect什麼東西都不帶
    }

    // 跳轉到更新頁面
    private void editjob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Jobs job = jobsService.findById(id);// jobsService的findById方法查找id的值，並存在 job 中。

            if (job != null) {// 如果job的值不為null，即透過ID找到了值（即 job 不为空）
                request.setAttribute("job", job);// 將job 的值保存到request中

            }
            request.getRequestDispatcher("/jobsVIEW/jobsForm.jsp").forward(request, response);
        }
    }

}
