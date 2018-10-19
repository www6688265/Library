package cn.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("User_table")
    public String userTable() {
        return "User_table";
    }

    @RequestMapping("addBook")
    public String addBook() {
        return "addBook";
    }

    @RequestMapping("Book_table")
    public String bookTable() {
        return "Book_table";
    }

    @RequestMapping("Borrow_table")
    public String borrowTable() {
        return "Borrow_table";
    }

    @RequestMapping("Ticket_table")
    public String ticketTable() {
        return "Ticket_table";
    }

    @RequestMapping("Borrow")
    public String borrow() {
        return "Borrow";
    }

    @RequestMapping("Return")
    public String Return() {
        return "Return";
    }

    @RequestMapping("Admin_table")
    public String AdminTable() {
        return "Admin_table";
    }
}
