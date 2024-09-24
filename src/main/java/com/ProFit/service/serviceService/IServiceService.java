package com.ProFit.service.serviceService;

import java.util.List;
import java.util.Map;

import com.ProFit.bean.servicesBean.ServiceBean;

public interface IServiceService {

	// 新增服務
	boolean insertService(ServiceBean service);

	// 更新現有服務
	boolean updateService(ServiceBean service);

	// 刪除服務
	boolean deleteService(int id);

	// 尋找所有服務
	List<ServiceBean> findAllServices();

	// 按 ID 尋找服務
	ServiceBean findServiceById(int id);

	// 搜尋服務（可按標題、內容、專業ID和使用者ID搜尋）
	List<ServiceBean> searchServices(String titleKeyword, String contentKeyword, Integer majorId, Integer userId);

	// 取得所有用戶 map<id, name>
	public Map<Integer, String> getAllUsersMap();

	// 取得所有專業 map<id, name>
	public Map<Integer, String> getAllMajorsMap();

	// 取得特定使用者的所有專業 map<id, name>
	public Map<Integer, String> getMajorsMapByUserId(Integer userId);
}