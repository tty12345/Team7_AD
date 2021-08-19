package com.example.demo.service;
import java.util.List;

import com.example.demo.domain.Preference;

public interface PreferenceService {
    public void save(Preference pref);
	public List<Preference> listPref();
    public Preference findprefByuserId(int id);
    public Preference findPreferenceById(int id);
    public void delete(Preference prf);
    
}
