package kr.co.iei.comm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.iei.comm.model.service.PlanCommService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/palncomm")
public class PlanCommController {
	@Autowired
private PlanCommService planCommService;
}
