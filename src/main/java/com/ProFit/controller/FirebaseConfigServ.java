package com.ProFit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ProFit.bean.FirebaseConfigBean.FirebaseConfig;

@RestController
public class FirebaseConfigServ {
	
	@Autowired
	private FirebaseConfig firebaseConfig;
		 
    @GetMapping("/FirebaseConfigServ")
	public FirebaseConfig getFirebaseConfig() {
		
    	firebaseConfig.setApiKey("AIzaSyAx5jaHY_1aLNuqhjEDQEFGNfe5CdIDtdI");
    	firebaseConfig.setAuthDomain("profit-e686b.firebaseapp.com");
    	firebaseConfig.setProjectId("profit-e686b");
    	firebaseConfig.setStorageBucket("profit-e686b.appspot.com");
    	firebaseConfig.setMessagingSenderId("5209696765");
    	firebaseConfig.setAppId("1:5209696765:web:48fd43b6d8180e2d0c5194");
    	firebaseConfig.setMeasurementId("G-BHSWBE52Y6");

		return firebaseConfig;
	}
}