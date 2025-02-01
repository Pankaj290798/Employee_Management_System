package com.ems.service;

import com.ems.entity.City;
import com.ems.exception.ApplicationException;
import com.ems.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("City not found with id: ","","", HttpStatus.NOT_FOUND));
    }

    public City updateCity(Long id, City updatedCity) {
        City existingCity = getCityById(id);
        existingCity.setCityName(updatedCity.getCityName());
        return cityRepository.save(existingCity);
    }

    public void deleteCity(Long id) {
        City city =cityRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("City not found with id: ","","", HttpStatus.NOT_FOUND));
            cityRepository.delete(city);
    }
}

