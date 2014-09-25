package com.mystique.ghost.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mystique.ghost.core.strategy.GameStrategy;

/**
 * @author mystique
 */
@Controller
public class GameController {

  @Autowired
  private GameStrategy gameStrategy;

  @RequestMapping("/")
  public String loadHomePage() {
    return "index";
  }

  @RequestMapping("/greeting")
  public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    model.addAttribute("name", name);
    return "greeting";
  }

  @RequestMapping("/rest")
  @ResponseBody
  public TestObject test() {
    return new TestObject("ad");
  }

}
