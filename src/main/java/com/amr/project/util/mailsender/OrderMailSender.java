package com.amr.project.util.mailsender;

import com.amr.project.model.entity.Order;
import com.amr.project.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// this class is subscribed for Reactor mono events (Order.setStatus, OrderServiceImpl.createOrderFromBasket)
@Component
final public class OrderMailSender {
    static private  MailServiceImpl mailService;

    private MailServiceImpl automailservice;
    @Autowired
    public OrderMailSender(MailServiceImpl automailservice) {
        this.automailservice = automailservice;
    }

    @PostConstruct
    private void init() {
        mailService = this.automailservice;
    }

    public static void changeStatusMail(Order order) {
        System.out.println("ORDER STATUS HAS BEEN CHANGED TO: ");
        System.out.println("Order id: " + order.getId());
        System.out.println("Order total: " + order.getGrandTotal());
        System.out.println("User name: " + order.getUser().getUsername());
        System.out.println("User email: " + order.getUser().getEmail());
        String message =
        switch (order.getStatus()) {
            case START -> String.format("""
                            Уважамый ,%s!\s
                            Вы создали заказ № %s на сайте Avito, \s
                            в случае отсутствия оплаты в течение %s часов\s
                            заказ будет удален""",
                    order.getUser().getUsername(), order.getId(), Order.EXPIRATION_HOURS);
            case WAITING -> String.format("""
                            Уважамый ,%s!\s
                            Ваш заказ № %s на сайте Avito, ожидает оплаты\s
                            общая сумма заказа:  %s \s
                            """,
                    order.getUser().getUsername(), order.getId(), order.getGrandTotal());
            case PAID -> String.format("""
                            Уважамый ,%s!\s
                            Ваш заказ № %s на сайте Avito, оплачен\s
                            общая сумма заказа:  %s \s
                            """,
                    order.getUser().getUsername(), order.getId(), order.getGrandTotal());
            case SENT -> String.format("""
                            Уважамый ,%s!\s
                            Ваш заказ № %s на сайте Avito, отправлен\s
                            адрес доставки:  %s \s
                            """,
                    order.getUser().getUsername(), order.getId(), order.getAddress().toString());
            case DELIVERED -> String.format("""
                            Уважамый ,%s!\s
                            Ваш заказ № %s на сайте Avito, доставлен\s
                            адрес доставки:  %s \s
                            """,
                    order.getUser().getUsername(), order.getId(), order.getAddress().toString());
        };
        sendEmail(message, order);
    }

    public static void deleteMail(Order order) {
        String message = String.format("""
                        Уважамый ,%s!\s
                        Ваш заказ № %s на сайте Avito, был удален в связи\s
                        с отсутствием оплаты в течение %s часов\s
                        держитесь там и хорошего вам настроения!""",
                order.getUser().getUsername(), order.getId(), Order.EXPIRATION_HOURS);
        sendEmail(message, order);
    }
    private static void sendEmail(String message, Order order) {
        mailService.send(order.getUser().getEmail(), "Your Order status in Avito 2.0", message);
    }
}


