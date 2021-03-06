/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.hackingspringboot.reactive.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Greg Turnquist
 */
@Controller
public class HomeController {

	private ItemRepository repository;

	public HomeController(ItemRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	String home(Model model) {
		model.addAttribute("items", this.repository.findAll());
		return "home.html";
	}

	@PostMapping
	String createEmployee(@ModelAttribute Item newItem) {
		this.repository.save(newItem);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	String deleteEmployee(@PathVariable long id) {
		this.repository.deleteById(id);
		return "redirect:/";
	}
}
