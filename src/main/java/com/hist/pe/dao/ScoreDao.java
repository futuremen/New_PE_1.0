package com.hist.pe.dao;

import java.util.List;
import java.util.Map;

import com.hist.pe.dao.base.Curd;
import com.hist.pe.entity.Score;
import com.hist.pe.entity.WarpForScore;

public interface ScoreDao extends Curd<Score> {
	
            List<Score> getClssScoreByPage(Map<String, Object> parameter);
            
            List<Score> getStudentScoreByPage(Map<String, Object> parameter);
            
            List<Score> getOriginalStudentScoreByPage(Map<String, Object> parameter);
            
            void getReallyScore();
            
            Score getOneStudentScore(String student_id);
            
            
            List<WarpForScore> getClassStudentScore(Map<String, Object> parameter);
            
            void updateOnlineScore(Map<String, Object> map);
            
           void  insertOnlineScore(Map<String, Object> map);
       
            

}
