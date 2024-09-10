package com.ProFit.dao.jobsCRUD;

import java.util.List;
import java.util.Map;
//1.
//創造一個介面，包含5個方法
//只要實現這個介面的都要實作這5個方法

import com.ProFit.bean.Jobs;


public interface GenericDAO<T, ID> {
    //T是泛型，代表JAVA BEAN，可以理解成一個設定好的格式，代表資料庫的表
    //ID也是泛型，代表這張表的PK的資料型態
    //必須在實作這個介面（implements GenericDAO<T, ID>）的類別或其子類，override以下5個方法
    public int save(T entity);
    public T findById(ID id);
    public List<T> findAll();
    public void update(ID id, Map<String, Object> updates);
    public void delete(ID id);
    
    
//	public int save(Jobs entity);
//	public Jobs findById(int jobsid);
//  
//    public void update(int jobsid, Map<String, Object> updates);
//    public void delete(int jobsid);
	
	
}