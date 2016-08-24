package com.future.hist.serviceinter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hist.pe.entity.Degree;

@Repository
public interface DegreeService {
   public List<Degree> getAllDegree();
}
