package kr.co.iei.search.model.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.place.model.service.PlaceService;
import kr.co.iei.plan.model.dao.PlanDao;
import kr.co.iei.plan.model.dto.PlanDTO;
import kr.co.iei.plan.model.service.PlanService;
import kr.co.iei.review.model.dao.ReviewDao;
import kr.co.iei.review.model.dto.ReviewDTO;
import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.search.model.dao.SearchDao;
import kr.co.iei.search.model.dto.QueryDTO;
import kr.co.iei.search.model.dto.SearchLogDTO;

@Service
public class SearchService {
	@Autowired
	private SearchDao searchDao;
	@Autowired
	public PlaceService placeService;
	@Autowired
	public PlanService planService;
	@Autowired
	public ReviewService reviewService;

	public List selectKeyword(QueryDTO search) {
		List keywordList = searchDao.selectKeyword(search);
		return keywordList;
	}

	@Transactional
	public Map<String, Map> searchResult(QueryDTO search) {
		String query = search.getQuery();
		List<Integer> placeList = searchDao.selectPlaceByKeyword(search);
		if (!query.equals("")) {
			int logResult = searchDao.insertSearchLog(query);
		}
		// 장소 정보 조회
		if (!placeList.isEmpty()) {
			int[] id = new int[placeList.size()];
			int i = 0;
			for (int no : placeList) {
				id[i] = no;
			}
			Map placeInfo = placeService.selectALLPlaceList(1, 1, null, id);
			// List<ReviewDTO> reviewList = reviewDao.selectReviewsByPlaceIds(placeList);
			// 3. 묶어서 반환
			Map<String, Map> searchResult = new HashMap<>();
			searchResult.put("PLACE", placeInfo);
			// searchResult.put("PLAN", planList);
			// searchResult.put("REVIEW", reviewList);
			return searchResult;
		}
		return null;
	}

	public Map<String, List<SearchLogDTO>> selectMostSearch(String date) {
		Map<String, List<SearchLogDTO>> popular = new HashMap<>();
		// 문자열 → LocalDate로 변환
		LocalDate today = LocalDate.parse(date); // "2025-04-10"
		// 주간 (월~일)
		LocalDate weekStart = today.with(DayOfWeek.MONDAY);
		LocalDate weekEnd = today.with(DayOfWeek.SUNDAY);
		// 월 시작/끝
		LocalDate monthStart = today.withDayOfMonth(1);
		LocalDate monthEnd = today.withDayOfMonth(today.lengthOfMonth());
		// 연도 시작/끝
		LocalDate yearStart = today.withDayOfYear(1);
		LocalDate yearEnd = today.withDayOfYear(today.lengthOfYear());
		// DAO 호출 시 문자열 형식으로 다시 변환
		popular.put("daily", searchDao.selectPopularByDate(date));
		popular.put("weekly", searchDao.selectPopularByWeek(weekStart.toString(), weekEnd.toString()));
		popular.put("monthly", searchDao.selectPopularByMonth(monthStart.toString(), monthEnd.toString()));
		popular.put("yearly", searchDao.selectPopularByYear(yearStart.toString(), yearEnd.toString()));

		return popular;
	}

	public List searchPlaceTitle(String query) {
		List titleList = searchDao.searchPlaceTitle(query);
		return titleList;
	}

}
