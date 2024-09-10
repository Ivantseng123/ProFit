package com.ProFit.dao.servicesCRUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ProFit.bean.servicesBean.ServiceBean;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.majorsBean.UserMajorPK;

public class HServiceDAO {

    private Session session;

    public HServiceDAO(Session session) {
        this.session = session;
    }

    // 新增服務
    public boolean insertService(ServiceBean service) {
        session.persist(service);
        return true;
    }

    // 更新現有服務
    public boolean updateService(ServiceBean service) {
        session.merge(service);
        return true;
    }

    // 刪除服務
    public boolean deleteService(int id) {
        ServiceBean service = session.get(ServiceBean.class, id);
        if (service != null) {
            session.remove(service);
            return true;
        }
        return false;
    }

    // 尋找所有服務
    public List<ServiceBean> findAllServices() {
        return session.createQuery("FROM ServiceBean", ServiceBean.class).list();
    }

    // 按 ID 尋找服務
    public ServiceBean findServiceById(int id) {
        return session.get(ServiceBean.class, id);
    }

    // 搜尋服務（可按標題、內容、專業ID和使用者ID搜尋）
    public List<ServiceBean> searchServices(String titleKeyword, String contentKeyword, Integer majorId, Integer userId) {
        StringBuilder hql = new StringBuilder("FROM ServiceBean s WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (titleKeyword != null && !titleKeyword.isEmpty()) {
            hql.append(" AND s.serviceTitle LIKE :title");
            params.put("title", "%" + titleKeyword + "%");
        }
        if (contentKeyword != null && !contentKeyword.isEmpty()) {
            hql.append(" AND s.serviceContent LIKE :content");
            params.put("content", "%" + contentKeyword + "%");
        }
        if (majorId != null) {
            hql.append(" AND s.userMajor.id.major.majorId = :majorId");
            params.put("majorId", majorId);
        }
        if (userId != null) {
            hql.append(" AND s.userMajor.id.user.userId = :userId");
            params.put("userId", userId);
        }

        Query<ServiceBean> query = session.createQuery(hql.toString(), ServiceBean.class);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.list();
    }

    // 取得所有用戶
    public Map<Integer, String> getAllUsers() {
        List<Users> users = session.createQuery("FROM Users", Users.class).list();
        Map<Integer, String> userMap = new HashMap<>();
        for (Users user : users) {
            userMap.put(user.getUserId(), user.getUserName());
        }
        return userMap;
    }

    // 取得所有專業
    public Map<Integer, String> getAllMajors() {
        List<MajorBean> majors = session.createQuery("FROM MajorBean", MajorBean.class).list();
        Map<Integer, String> majorMap = new HashMap<>();
        for (MajorBean major : majors) {
            majorMap.put(major.getMajorId(), major.getMajorName());
        }
        return majorMap;
    }

    // 取得特定使用者的所有專業
    public Map<Integer, String> getMajorsByUserId(int userId) {
        String hql = "SELECT um.id.major FROM UserMajorBean um WHERE um.id.user.userId = :userId";
        List<MajorBean> majors = session.createQuery(hql, MajorBean.class)
                                        .setParameter("userId", userId)
                                        .list();
        Map<Integer, String> majorMap = new HashMap<>();
        for (MajorBean major : majors) {
            majorMap.put(major.getMajorId(), major.getMajorName());
        }
        return majorMap;
    }

    // 根據id取得username
    /*
    public String getUserNameById(int userId) {
        Users user = session.get(Users.class, userId);
        return user != null ? user.getUserName() : "Unknown User";
    }
    */
    // 根據id取得majorname
    /*
    public String getMajorNameById(int majorId) {
        MajorBean major = session.get(MajorBean.class, majorId);
        return major != null ? major.getMajorName() : "Unknown Major";
    }
    */
}