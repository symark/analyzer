package pl.stock.web.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.stock.data.service.StatisticRecordService;

@Controller
@RequestMapping("stats")
public class StatisticController {

	@Autowired
	private StatisticRecordService statisticService;
	
}