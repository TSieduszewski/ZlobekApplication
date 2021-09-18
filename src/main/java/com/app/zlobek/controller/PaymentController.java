package com.app.zlobek.controller;

import com.app.zlobek.entity.Payment;
import com.app.zlobek.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/listOfAllPayments")
    public String listOfAllPayments( Model model){
        int period = 0;
        List<Payment> payment = paymentService.findAllByMonthOrderByMonthDesc(LocalDate.now().minusMonths(period));
        model.addAttribute("listOfPayments", payment);

        return "payments/listOfAllPayments";
    }

    @GetMapping("/actualPayment")
    public String actualPayment(Model model){

        Payment payment = paymentService.findByParent();
        model.addAttribute("payment", payment);

        return "payments/actualPayment";
    }
}