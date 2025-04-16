package kr.co.iei.plan.model.dto;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanRequestDTO {
	private int reqPage;
	private Integer numPerPage;
	private String loginNickname;
	private String memberNickname;
	private int[] id;
	private Integer order;
	private Integer start;
	private Integer end;
}
