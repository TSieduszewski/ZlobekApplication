package com.app.zlobek.controller;

import com.app.zlobek.entity.Attendance;
import com.app.zlobek.entity.Payment;
import com.app.zlobek.service.AttendanceService;
import com.app.zlobek.service.PaymentService;
import com.app.zlobek.util.global.GlobalValues;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PaymentController(PaymentService paymentService, AttendanceService attendanceService) {
        this.paymentService = paymentService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/listOfAllPayments")
    public String listOfAllPayments(Model model) {
        int period = 0;
        // List<Payment> payment = paymentService.findAllByMonthOrderByMonthDesc(LocalDate.now().minusMonths(period));
        List<Payment> payment = updatePayments();
        model.addAttribute("listOfPayments", payment);

        return "payments/listOfAllPayments";
    }

    @GetMapping("/actualPayment")
    public String actualPayment(Model model) {

        Payment payment = paymentService.findByParent(GlobalValues.idParent);
        List<Attendance> attendanceList = attendanceService.findAllByIdFromLastMonth(payment.getParent().getId());

        //tu na miękko wrzuca nowe płatności w metodzie, którą wywołują zasadniczo rodzice.
        //trzeba to zmienić tak, aby aplikacja w momencie działania sama aktualizowała tabele np na początku miesiąca
        //ZMIEN TO KONIECZNIE BO NIE ZACHOWANA JEST ZASADA HERMETYZACJI
        if (Objects.isNull(payment)) {
            listOfAllPayments(model);
            payment = paymentService.findByParent(GlobalValues.idParent);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("LLLL");
        String month = payment.getMonth().format(dateTimeFormatter);

        model.addAttribute("payment", payment);
        model.addAttribute("month", month);
        model.addAttribute("attendance", attendanceList);

        return "payments/actualPayment";
    }

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
            temp.setMeals(mealPaymentCounter*GlobalValues.singleMealPrice);
            temp.setSummary(temp.getTuition()+temp.getMeals());
            paymentService.save(temp);
            mealPaymentCounter = 0;
        }

        return paymentService.findAllByMonthOrderByMonthDesc(LocalDate.now().minusMonths(period));
    }

}