package com.app.zlobek.controller;

import com.app.zlobek.entity.Attendance;
import com.app.zlobek.entity.Parent;
import com.app.zlobek.service.AttendanceService;
import com.app.zlobek.service.ParentService;
import com.app.zlobek.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {


    private AttendanceService attendanceService;
    private ParentService parentService;
    private PaymentService paymentService;
    private boolean hourGuard;
    private boolean isAnyPayment;

    @Autowired
    public AttendanceController(AttendanceService attendanceService, ParentService parentService, PaymentService paymentService) {
        this.attendanceService = attendanceService;
        this.parentService = parentService;
        this.paymentService = paymentService;
    }

//    @Secured("ROLE_USER")
    @GetMapping("/showFormForSelectAttendance")
    public String showForm(Model model) {
        List<Attendance> attendanceList = attendanceService.findAllByIdAndDate();

        model.addAttribute("attendanceList", attendanceList);

        return "attendance/attendanceForm";
    }

//    @Secured("ROLE_USER")
    @GetMapping("/change")
    public String changeAttendanceStatus(@RequestParam("attendanceId") int theId, Model model) {

        Attendance attendance = attendanceService.findById(theId);

        if (attendance.getAttendant()) {
            attendance.setAttendant(false);
        } else {
            attendance.setAttendant(true);
        }

        attendanceService.save(attendance);

        return "redirect:/attendance/showFormForSelectAttendance";
    }

//    @Secured("ROLE_DIRECTOR")
    @GetMapping("/verify")
    public String changeVerificationStatus(@RequestParam("verificationId") int theId, Model model) {

        Attendance attendance = attendanceService.findById(theId);

        if (attendance.getVerification()) {
            attendance.setVerification(false);
        } else {
            attendance.setVerification(true);
        }

        attendanceService.save(attendance);

        return "redirect:/attendance/showPresentDayListOfAttendanceOfAllParents";
    }

//    @Secured("ROLE_DIRECTOR")
    @GetMapping("/showAttendanceOfAllParents")
    public String showAttendanceOfAllParents(Model model) {
        List<Parent> parentList = parentService.findAll();
        model.addAttribute("parents", parentList);
        return "attendance/attendanceList";
    }

//    @Secured("ROLE_DIRECTOR")
    @GetMapping("/showListOfSingleParentsAttendance")
    public String showListOfSingleParentsAttendance(@RequestParam("parentId") int id, Model model) {

        Parent singleParent = parentService.findById(id);
        List<Attendance> parentAttendanceList = attendanceService.findAllById(id);
        model.addAttribute("singleParent", singleParent);
        model.addAttribute("singleAttendanceList", parentAttendanceList);


        return "attendance/singleAttendanceList";
    }

//    @Secured("ROLE_DIRECTOR")
    @GetMapping("/showPresentDayListOfAttendanceOfAllParents")
    public String showPresentDayListOfAttendanceOfAllParents(Model model) {
        hourGuard = LocalTime.now().isBefore(LocalTime.of(7, 0, 0));
        List<Attendance> parentList = attendanceService.findAllByDate();


        for (Attendance temp : parentList) {
            if (!(temp.getVerification() && temp.getAttendant())) {
                isAnyPayment = true;
                break;
            }
            isAnyPayment = false;
        }

        model.addAttribute("parents", parentList);
        model.addAttribute("hourGuard", hourGuard);
        model.addAttribute("isAnyPayment", isAnyPayment);

        return "attendance/attendanceListPresentDay";
    }
}
