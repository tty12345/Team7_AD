package com.example.demo.controller;

import com.example.demo.domain.CarPosting;
import com.example.demo.service.CarPostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/data")
@CrossOrigin(origins = "http://localhost:3000")
public class FlaskController {

    @Autowired
    CarPostService cpservice;

    String url = "http://localhost:5000";

    @PostMapping(path = "/estimate", consumes = { "multipart/form-data" })
    public ResponseEntity<String> getPriceEstimate(@ModelAttribute CarPosting carpost) {
        CarPosting toEstimate = new CarPosting(carpost.getDepreciation(), carpost.getBrand(),
                carpost.getEngineCapacity(), carpost.getAge(), carpost.getMileage(), carpost.getCategory());

        System.out.println(toEstimate.getDepreciation());
        System.out.println(toEstimate.getBrand());
        System.out.println(toEstimate.getEngineCapacity());
        System.out.println(toEstimate.getAge());
        System.out.println(toEstimate.getMileage());
        System.out.println(toEstimate.getCategory());

        String estimate = getPriceEstimateFlask(toEstimate);

        System.out.println(estimate);

        return new ResponseEntity<>(estimate, HttpStatus.OK);
    }

    public String getPriceEstimateFlask(CarPosting toEstimate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
        map.add("depreciation", toEstimate.getDepreciation());
        map.add("age", toEstimate.getAge());
        map.add("mileage_bin", toEstimate.getMileage());
        map.add("ec_bin", toEstimate.getEngineCapacity());
        map.add("brand", 15);
        map.add("category", 1);

        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<MultiValueMap<String, Integer>>(map,
                headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url + "/predict_api", request, String.class);

        String estimate = response.getBody();

        return estimate;
    }
}
