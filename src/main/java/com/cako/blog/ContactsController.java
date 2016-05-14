package com.cako.blog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outLink")
public class ContactsController {

	@RequestMapping(value = "/contacts.html")
	public String contacts() {

		return "bbs/contacts";
	}

	@RequestMapping(value = "/about.html")
	public String about() {

		return "bbs/about";
	}

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(format.format(new Date()));
		System.out.println(format1.format(new Date()));
	}
}
