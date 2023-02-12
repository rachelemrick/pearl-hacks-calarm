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
	public static final String ALARMS_FOOTER = "<a href = \"http://localhost:8080/home\">Home</a>";
	public static final String ADD_ALARM = "<form method = \"post\"><input type = \"time\" name=\"time\"/><input type = \"text\" name = \"name\"/><input type = \"hidden\" value = \"%s\" name = \"day\"/></form>";
	public static final String REMOVE_ALARM = "<form method = \"delete\"><input type = \"button\" name = \"button\"><input type = \"hidden\" value = \"%s\" name=\"time\"/><input type = \"hidden\" value = \"%s\" name = \"name\"/><input type = \"hidden\" value = \"%s\" name = \"day\"/></form> Remove Alarm";
	@Autowired
	private AlarmControllerImpl alarmController;
	@Autowired
	private ModelImpl alarmModel;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping("/home")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		String text = "<a href = \"http://localhost:8080/alarms?day=MONDAY\">Monday</a> \n";
		text += "<a href = \"http://localhost:8080/alarms?day=TUESDAY\">Tuesday</a> \n";
		text += "<a href = \"http://localhost:8080/alarms?day=WEDNESDAY\">Wednesday</a> \n";
		text += "<a href = \"http://localhost:8080/alarms?day=THURSDAY\">Thursday</a> \n";
		text += "<a href = \"http://localhost:8080/alarms?day=FRIDAY\">Friday</a> \n";
		text += "<a href = \"http://localhost:8080/alarms?day=SATURDAY\">Saturday</a> \n";
		text += "<a href = \"http://localhost:8080/alarms?day=SUNDAY\">Sunday</a> \n";
		return text;

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
			alarms += String.format(REMOVE_ALARM, alarm.getTime().getHour() + ":" + alarm.getTime().getMinute(), alarm.getName(), day);
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
		AlarmImpl alarm = asi.makeAlarmImpl();
		DAYS_OF_WEEK dayEnum = DAYS_OF_WEEK.valueOf(asi.getDay().split(",")[0]);
		alarmController.createAlarm(alarm.getTime(), dayEnum, alarm.getName());
		String retVal = String.format("<a href = \"http://localhost:8080/alarms?day=%s\">Return</a> \n", dayEnum);
		return retVal;

	}

	@DeleteMapping("/alarms")
	public String removeAlarm(
			@ModelAttribute AlarmStringImpl asi
	) {
		// Remove alarm
		// We seem to be getting the right values for getDay, getTime, and getName
		System.out.println("Are we getting here? TEST");
		System.out.println("Remove: " + asi.getDay() + ":" + asi.getTime() + ":" + asi.getName());

		DAYS_OF_WEEK dayEnum = DAYS_OF_WEEK.valueOf(asi.getDay());
		System.out.println(dayEnum.toString());
		alarmController.removeAlarm(asi.makeAlarmImpl().getTime(), dayEnum);


		String retVal = String.format("<a href = \"http://localhost:8080/alarms?day=%s\">Return</a> \n", dayEnum);
		return retVal;
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
	// 11:00 formatting to 11:0


}
