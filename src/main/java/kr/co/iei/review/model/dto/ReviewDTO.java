package kr.co.iei.review.model.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="review")
public class ReviewDTO {
 private int reviewNo;
 private String memberNickname;
 private String reviewTitle;
 private String reviewContent;
 private float starRate;
 private String reviewDate;
 private int placeId;
 private int placeTypeId;
 private String placeThumb;
private String placeTitle;
private String placeAddr;
private int readCount;
}
