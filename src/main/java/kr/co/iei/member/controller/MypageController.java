package kr.co.iei.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.iei.member.model.service.MypageService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/mypage")
public class MypageController {
	@Autowired
	private MypageService mypageService;
	
	//내가 쓴 리뷰 불러오기
	@GetMapping("/reviews")
	public ResponseEntity<Map> reviewslist(@RequestParam String nickname, @RequestParam String value){
		System.out.println(value);
		System.out.println(nickname);
		Map map = mypageService.reviewsList(nickname,value);
		return ResponseEntity.ok(map);
	}
	
	//즐겨찾기 한 여행지 불러오기
	@GetMapping("/bookmark")
	public ResponseEntity<Map> bookmarkList(@RequestParam String nickname, @RequestParam String value){
		System.out.println(nickname);
		System.out.println(value);
		Map map = mypageService.BookmarkList(value);
		return ResponseEntity.ok(map);
	}
}
