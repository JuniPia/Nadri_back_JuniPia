package kr.co.iei.tour.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="spot")
public class SpotPlace {
	private int contentId;
	private int contentTypeId;
	private String contentTitle;
	private String contentAddr;
	private String contentTel;
	private String areaCode;
	private String sigunguCode;
	private String contentOverview;
	private String contentCat1;
	private String contentCat2;
	private String contentCat3;
	private double mapLat;
	private double mapLng;
	private String contentThumb;
	private String usetime;
	private String restdate;
	private String parking;
	
	//여기서부터는 content 컬럼에 없습니다
	private int contentRating; //장소에 대한 평점
	private int contentReview; //장소에 대한 리뷰 수
	private double distance; //마커로부터 장소까지의 거리(m)
}
