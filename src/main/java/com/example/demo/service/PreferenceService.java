package com.example.demo.service;
import java.util.List;

import com.example.demo.domain.Preference;

public interface PreferenceService {
    public void save(Preference pref);
	public List<Preference> listPref();
    
}
