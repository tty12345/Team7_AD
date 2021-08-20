package com.example.demo.service;

import java.util.List;




import com.example.demo.domain.Preference;
import com.example.demo.repo.PreferenceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

@Service
public class PreferenceServiceImplementation implements PreferenceService {
	@Autowired
	PreferenceRepository prefrepo;
	@Override
	public void save(Preference pref) {
		prefrepo.save(pref);
		
	}
	@Override
	public List<Preference> listPref() {
		
		return prefrepo.findAll();
	}
	
	@Override
	public Preference findprefByuserId(int id) {
		return prefrepo.findprefByuserId(id);
	}
	@Override
	public Preference findPreferenceById(int id) {
	
		return prefrepo.findById(id);
	}
	@Override
	public void delete(Preference prf) {
		
		prefrepo.delete(prf);
		
	}
    


}
