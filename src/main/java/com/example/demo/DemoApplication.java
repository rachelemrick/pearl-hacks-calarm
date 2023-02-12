package com.example.demo;

import com.example.demo.javaCode.controller.AlarmControllerImpl;
import com.example.demo.javaCode.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static final String ALARMS_HEADER = "<b>Here are your alarms for %s:</b>";
	public static final String ALARMS_FOOTER = ""; // TODO: make a link to homepage, style as button
	public static final String ADD_ALARM = "<form method = \"post\"><input type = \"time\" name=\"time\"/><input type = \"text\" name = \"name\"/><input type = \"hidden\" value = \"%s\" name = \"day\"/></form>";
	@Autowired
	private AlarmControllerImpl alarmController;
	@Autowired
	private ModelImpl alarmModel;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/alarms")
	public String getAlarms(
			// Accepts request that is a string, with default value Monday, and binds to day (because of the end)
			@RequestParam(value = "day", defaultValue = "MONDAY") String day

	) {
		DAYS_OF_WEEK dayEnum = DAYS_OF_WEEK.valueOf(day);
		ArrayList<Alarm> list = alarmModel.getAlarms(dayEnum);

		// Calculate output
		// Calculate header
		String header = String.format(ALARMS_HEADER, day);
		// Loop and display alarms
		String alarms = "\n";

		for (Alarm alarm : list) {
			alarms += alarm.getName() + " at " + alarm.getTime().getHour() + ":" + alarm.getTime().getMinute();
			alarms += "\n";
		}

		String addAlarm = String.format(ADD_ALARM, day);

		return (header + alarms + addAlarm + ALARMS_FOOTER);

		// FORMAT: http://localhost:8080/alarms?day=MONDAY

	}

	@PostMapping("/alarms")
	public String addAlarms(
			@ModelAttribute AlarmStringImpl asi
	) {
		// This means the form has been filled out
		System.out.println(asi.getTime() + asi.getName()+ asi.getDay().split(",")[0]);
		AlarmImpl alarm = asi.makeAlarmImpl();
		DAYS_OF_WEEK dayEnum = DAYS_OF_WEEK.valueOf(asi.getDay().split(",")[0]);
		alarmController.createAlarm(alarm.getTime(), dayEnum, alarm.getName());
		return "Success";
	}


	@GetMapping("/debug/add")
	public String debugAdd (
	) {
		// Add a bunch of alarms
		TimeImpl t1 = new TimeImpl(8, 30, 0);
		TimeImpl t2 = new TimeImpl(11, 0, 0);
		alarmController.createAlarm(t1, DAYS_OF_WEEK.MONDAY, "Wake up!");
		alarmController.createAlarm(t2, DAYS_OF_WEEK.WEDNESDAY, "Go to class");

		return "Success";
	}


	@GetMapping("/debug/remove")
	public String debugRemove(

	) {
		TimeImpl t1 = new TimeImpl(8, 30, 0);
		TimeImpl t2 = new TimeImpl(11, 0, 0);
		alarmController.removeAlarm(t1, DAYS_OF_WEEK.MONDAY);
		alarmController.removeAlarm(t2, DAYS_OF_WEEK.WEDNESDAY);

		return "Success";
	}

	// Next steps:
	// Footer home button (link) tomorrow
	// 11:00 formatting to 11:0


}
