package com.ProFit.service.serviceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProFit.bean.servicesBean.ServiceBean;
import com.ProFit.dao.servicesCRUD.IHServiceDAO;

@Service
@Transactional
public class ServiceService implements IServiceService {

	@Autowired
	private IHServiceDAO serviceDAO;
	
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
	
}
