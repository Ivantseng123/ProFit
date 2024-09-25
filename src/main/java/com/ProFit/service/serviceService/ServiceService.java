package com.ProFit.service.serviceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.majorsBean.MajorBean;
import com.ProFit.bean.majorsBean.UserMajorBean;
import com.ProFit.bean.servicesBean.ServiceBean;
import com.ProFit.bean.usersBean.Users;
import com.ProFit.dao.majorsDao.IHmajorDAO;
import com.ProFit.dao.majorsDao.IHuserMajorDAO;
import com.ProFit.dao.servicesCRUD.IHServiceDAO;
import com.ProFit.dao.usersDao.IHUserDao;

@Service
@Transactional
public class ServiceService implements IServiceService {

	@Autowired
	private IHServiceDAO serviceDAO;
	
	@Autowired
	private IHuserMajorDAO userMajorDAO;
	
	@Autowired
	private IHmajorDAO majorDAO;
	
	@Autowired
	private IHUserDao userDao;
	
	// 新增服務
		@Override
		public boolean insertService(ServiceBean service) {
			return serviceDAO.insertService(service);
		}

		// 更新現有服務
		@Override
		public boolean updateService(ServiceBean service) {
			return serviceDAO.updateService(service);
		}

		// 刪除服務
		@Override
		public boolean deleteService(int id) {
			return serviceDAO.deleteService(id);
		}

		// 尋找所有服務
		@Override
		public List<ServiceBean> findAllServices() {
			return serviceDAO.findAllServices();
		}

		// 按 ID 尋找服務
		@Override
		public ServiceBean findServiceById(int id) {
			return serviceDAO.findServiceById(id);
		}

		// 搜尋服務（可按標題、內容、專業ID和使用者ID搜尋）
		@Override
		public List<ServiceBean> searchServices(String titleKeyword, String contentKeyword, Integer majorId,
				Integer userId) {
			return serviceDAO.searchServices(titleKeyword, contentKeyword, majorId, userId);
		}
	
		/** dao沒有直接的方法，為了前端顯示方便寫的邏輯 **/
		
		// 取得所有用戶 map<id, name>
		public Map<Integer, String> getAllUsersMap() {
			
			Map<Integer, String> usersMap = new HashMap<>();
			List<Users> allUsers = userDao.getAllUserInfo();
			for (Users user : allUsers) {
				usersMap.put(user.getUserId(), user.getUserName());
			}
			return usersMap;
		}

		// 取得所有專業 map<id, name>
		public Map<Integer, String> getAllMajorsMap() {
			Map<Integer, String> majorsMap = new HashMap<>();
			List<MajorBean> allMajors = majorDAO.findAllMajors();
			for (MajorBean major : allMajors) {
				majorsMap.put(major.getMajorId(), major.getMajorName());
			}
			return majorsMap;
		}

		// 取得特定使用者的所有專業 map<id, name>
		public Map<Integer, String> getMajorsMapByUserId(Integer userId) {
	        if (userId != null) {
	        	Map<Integer, String> majorsMap = new HashMap<>();
	            List<UserMajorBean> userMajors = userMajorDAO.findMajorsByUserId(userId);
	            for (UserMajorBean userMajor : userMajors) {
	                MajorBean major = userMajor.getId().getMajor();
	                majorsMap.put(major.getMajorId(), major.getMajorName());
	            }
	            return majorsMap;
	        } else {
	            return this.getAllMajorsMap();
	        }
		}
}
