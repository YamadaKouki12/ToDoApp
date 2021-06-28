package jp.kobespiral.yamadakouki.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/member")
public class ToDoController {
    @Autowired
    ToDoService tService;
    


}
