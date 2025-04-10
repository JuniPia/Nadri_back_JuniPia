package kr.co.iei.review.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import kr.co.iei.place.model.dto.PlaceInfoDTO;
import kr.co.iei.review.model.dao.ReviewDao;
import kr.co.iei.review.model.dto.PlaceImgDTO;
import kr.co.iei.review.model.dto.ReviewDTO;
import kr.co.iei.util.PageInfo;
import kr.co.iei.util.PageInfoUtil;


@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private PageInfoUtil pageInfoUtil;
	public Map reviewList(int reqPage, String value) {
		int numPerPage =9;
		 int  pageNaviSize=5;
		 HashMap<String, Object> map = new HashMap<>();
		 map.put("value",value);
		 int totalCount=reviewDao.totalCount(map);
		PageInfo pi =  pageInfoUtil.getPageInfo(reqPage, numPerPage, pageNaviSize, totalCount);
		map.put("pi", pi);
		List list = reviewDao.selectBoardList(map);
		map.put("list",list);
		return map;
	}
	public ReviewDTO selectOneReview(int reviewNo) {
		ReviewDTO  review = reviewDao.selectOneReview(reviewNo);
		
		return review;
	}
	public int deleteReview(int reviewNo) {
		int result = reviewDao.deleteReview(reviewNo);
		return result;
	}
	public List oneReviewList(int placeId) {
		List list = reviewDao.selectOneBoardList(placeId);
		return list;
	}
	public PlaceInfoDTO placeinfo(int placeId) {
		PlaceInfoDTO place = reviewDao.placeinfo(placeId);
	
		return place;
	}
	@Transactional
	public int insertReview(ReviewDTO review, List<PlaceImgDTO> placeImgList) {
		// TODO Auto-generated method stub
		int result= reviewDao.insertReview(review);
		for(PlaceImgDTO placeImg :placeImgList) {
			placeImg.setPlaceId(review.getPlaceId());
			placeImg.setReviewNo(review.getReviewNo());
			System.out.println(placeImg);
			result += reviewDao.insertPlaceImg(placeImg);
		}
		return result;
	}


	
}
