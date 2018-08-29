package org.com.zeus.controller;

import org.com.zeus.sequence.handler.SeqGenerator;
import org.com.zeus.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SequenceController2 {
	@Autowired
	SequenceService sequenceService;
	@Autowired
	SeqGenerator seq3Sequence;
	
	@GetMapping("/getId2")
	@ResponseBody
	public String getId() {
		return "sequence2------->"+seq3Sequence.getNextVal();
	}

}
