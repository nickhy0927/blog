package com.cako.blog;

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
}
