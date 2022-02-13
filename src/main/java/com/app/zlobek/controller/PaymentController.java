package com.app.zlobek.controller;

import com.app.zlobek.entity.Attendance;
import com.app.zlobek.entity.Payment;
import com.app.zlobek.security.GetUserID;
import com.app.zlobek.service.AttendanceService;
import com.app.zlobek.service.PaymentService;
import com.app.zlobek.util.global.GlobalValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;
    private AttendanceService attendanceService;
    private int parentId;
    @Autowired
    public PaymentController(PaymentService paymentService, AttendanceService attendanceService) {
        this.paymentService = paymentService;
        this.attendanceService = attendanceService;
    }

    @Secured("ROLE_DIRECTOR")
    @GetMapping("/listOfAllPayments")
    public String listOfAllPayments(Model model) {

        List<Payment> payment = updatePayments();
        model.addAttribute("listOfPayments", payment);

        return "payments/listOfAllPayments";
    }

    @Secured("ROLE_USER")
    @GetMapping("/actualPayment")
    public String actualPayment(Model model, Authentication authentication) throws Exception {

        GetUserID userID = new GetUserID(authentication);
        parentId = userID.get();

        Payment payment = paymentService.findByParent(parentId, GlobalValues.actualMonth);
        Payment paymentPreviousMonth = paymentService.findByParent(parentId, GlobalValues.previousMonth);
        List<Attendance> attendanceList = attendanceService.findAllByIdFromLastMonth(payment.getParent().getId());
        String previousMonth ="";

        //tu na miękko wrzuca nowe płatności w metodzie, którą wywołują zasadniczo rodzice.
        //trzeba to zmienić tak, aby aplikacja w momencie działania sama aktualizowała tabele np na początku miesiąca
        //ZMIEN TO KONIECZNIE BO NIE ZACHOWANA JEST ZASADA HERMETYZACJI
        if (Objects.isNull(payment)) {
            listOfAllPayments(model);
            payment = paymentService.findByParent(parentId, GlobalValues.actualMonth);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("LLLL");

        String month = payment.getMonth().format(dateTimeFormatter);

        try {
            previousMonth = paymentPreviousMonth.getMonth().format(dateTimeFormatter);
        } catch (NullPointerException e){
            previousMonth = "";
        }

        model.addAttribute("payment", payment);
        model.addAttribute("month", month);
        model.addAttribute("paymentPreviousMonth", paymentPreviousMonth);
        model.addAttribute("previousMonth", previousMonth);
        model.addAttribute("attendance", attendanceList);

        return "payments/actualPayment";
    }

    @Secured("ROLE_DIRECTOR")
    private List<Payment> updatePayments() {
        int period = 0;
        int mealPaymentCounter = 0;
        List<Payment> p = paymentService.findAllByMonthOrderByMonthDesc(LocalDate.now().minusMonths(period));

        for (Payment temp : p) {
            List<Attendance> a = attendanceService.findAllByIdFromLastMonth(temp.getParent().getId());
            for (Attendance temp2 : a) {
                if (!(temp2.getAttendant().equals(temp2.getVerification()))) {
                    mealPaymentCounter++;
                }
            }
            temp.setMeals(mealPaymentCounter * GlobalValues.singleMealPrice);
            temp.setSummary(temp.getTuition() + temp.getMeals());
            paymentService.save(temp);
            mealPaymentCounter = 0;
        }

        return paymentService.findAllByMonthOrderByMonthDesc(LocalDate.now().minusMonths(period));
    }

}