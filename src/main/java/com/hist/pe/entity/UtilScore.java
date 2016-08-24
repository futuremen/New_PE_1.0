package com.hist.pe.entity;

public class UtilScore {
	
	private Score score = new Score();
	private WarpForScore warpForScore;
	
	public Score getScore() {
		
		    if (score==null) {
				return null;
			}

			score.setDash(warpForScore.getDash());
			
			score.setEndurance(warpForScore.getEndurance());
			
			score.setHeight(warpForScore.getHeight());

			score.setLung(warpForScore.getLung());

			score.setPullups_situps(warpForScore.getPullups_situps());

			score.setTerm_id(warpForScore.getTerm_id());

			score.setWeight(warpForScore.getWeight());

			score.setStudent_id(warpForScore.getStudent_id());

			score.setSitreach(warpForScore.getSitreach());

			score.setStandingleap(warpForScore.getStandingleap());
			
			score.setSunScore(warpForScore.getSunScore());
			
			score.setOnlineScore(warpForScore.getOnlineScore());
			
			score.setId(warpForScore.getScore_id());

		    return score;
	}
	public void setScore(Score score) {
		
		
		
		
		warpForScore.setDash(score.getDash());
		
		warpForScore.setEndurance(score.getEndurance());
		
		warpForScore.setHeight(score.getHeight());
		
		warpForScore.setLung(score.getLung());
		
		warpForScore.setPullups_situps(score.getPullups_situps());
		
		warpForScore.setSitreach(score.getSitreach());
		
		warpForScore.setStandingleap(score.getStandingleap());
		
		warpForScore.setWeight(score.getWeight());
		
		warpForScore.setScore_id(score.getId());
		
		warpForScore.setSunScore(score.getSunScore());
		
		warpForScore.setOnlineScore(score.getOnlineScore());
		
		this.score = score;
	}
	public WarpForScore getWarpForScore() {
		return warpForScore;
	}
	public void setWarpForScore(WarpForScore warpForScore) {
		this.warpForScore = warpForScore;
	}
	
	

}
