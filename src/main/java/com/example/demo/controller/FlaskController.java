package com.example.demo.controller;

import java.util.Arrays;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.Category;
import com.example.demo.service.CarPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/data")
@CrossOrigin(origins = "hhttp://team7adreactclientcarexchange-env.eba-mpprj4gb.us-east-1.elasticbeanstalk.com/")
public class FlaskController {

    @Autowired
    CarPostService cpservice;

    String url = "http://pythonflask-env.eba-zafucm2t.us-east-2.elasticbeanstalk.com/";
                    

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

    @GetMapping("/coeTitle")
    public ResponseEntity<String> getCoeTitle() {
        String title = getTitlePython();

        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @RequestMapping("/coePrices")
    public ResponseEntity<Category[]> getCoePrices() {
        // String[] headers = { "Category", "Quota", "Premium" };
        Category[] categories = getPricesPython();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ResponseBody
    public String getTitlePython() {
        String url = "http://pythonflask-env.eba-zafucm2t.us-east-2.elasticbeanstalk.com/coe_title";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String title = result.getBody().substring(15, 45);

        return title;
    }

    public Category[] getPricesPython() {

        String url = "http://pythonflask-env.eba-zafucm2t.us-east-2.elasticbeanstalk.com/coe_prices";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String cats = result.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        Category[] categories = new Category[4];
        try {
            categories = objectMapper.readValue(cats, Category[].class);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
