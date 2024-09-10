package com.ProFit.dao.jobsCRUD.impl;



import java.util.List;
import java.util.Map;


import org.hibernate.Session;

import com.ProFit.bean.Jobs;

import com.ProFit.dao.jobsCRUD.GenericDAO;


//抽象類別就是為了給別人繼承存在，他實作（implements）這個介面的5個方法
public abstract class HjobsDAO<T, ID> implements GenericDAO<T, ID> {

	private Session session;

	public HjobsDAO(Session session) {
		this.session = session;
	}
	
	
    @Override
    public int save(T entity) {

		return -1;
	}
    
    @Override
    public T findById(ID id){

		return (T) session.get(Jobs.class, id);
		//return null;
		
	}
    
    @Override
    public List<T> findAll(){

    	return null;
	}
    
    @Override
    public void update(ID id, Map<String, Object> updates){// void無返回值

	}
    
    @Override
    public void delete(ID id){// void無返回值

	}
	
	
	
	
	
	


    
//    @Override
//	public int save(Jobs entity) {
//
//		return -1;
//	}
//    
//    @Override
//    public Jobs findById(int jobsid){
//
//		return session.get(Jobs.class, jobsid);
//		
//	}
//    
//    @Override
//    public List<T> findAll(){
//
//    	return null;
//	}
//    
//    @Override
//    public void update(int jobsid, Map<String, Object> updates){// void無返回值
//
//	}
//    
//    @Override
//    public void delete(int jobsid){// void無返回值
//
//	}
    
 

}
