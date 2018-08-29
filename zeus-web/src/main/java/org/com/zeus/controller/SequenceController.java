package org.com.zeus.controller;

import java.util.List;

import org.com.zeus.common.model.SequenceDO;
import org.com.zeus.sequence.handler.SeqGenerator;
import org.com.zeus.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SequenceController {
	@Autowired
	SequenceService sequenceService;
	@Autowired
	SeqGenerator seq3Sequence;

	@GetMapping("/getList")
	@ResponseBody
	public List<SequenceDO> getList() {
		return sequenceService.getList();
	}
	
	@GetMapping("/getId")
	@ResponseBody
	public String getId() {
		return "sequence------->"+seq3Sequence.getNextVal();
	}

}
