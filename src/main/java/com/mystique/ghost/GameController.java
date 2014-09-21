package com.mystique.ghost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

  @RequestMapping({"/", "home"})
  public String loadHomePage() {
    return "index";
  }
}
