package com.app.zlobek.controller;

import com.app.zlobek.entity.Attendance;
import com.app.zlobek.service.AttendanceService;
import com.app.zlobek.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {


    private AttendanceService attendanceService;
    private ParentService parentService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService, ParentService parentService) {
        this.attendanceService = attendanceService;
        this.parentService = parentService;
    }

    @GetMapping("/showFormForSelectAttendance")
    public String showForm(Model model) {
        List<Attendance> attendanceList = attendanceService.findAllByIdAndDate();

        model.addAttribute("attendanceList", attendanceList);

        return "attendance/attendanceForm";
    }

    @GetMapping("/change")
    public String changeAttendanceStatus(@RequestParam("attendanceId") int theId, Model model){

        Attendance attendance = attendanceService.findById(theId);

        if(attendance.getAttendant()){
            attendance.setAttendant(false);
        } else {
            attendance.setAttendant(true);
        }

        attendanceService.save(attendance);

        return "redirect:/attendance/showFormForSelectAttendance";
    }
}
