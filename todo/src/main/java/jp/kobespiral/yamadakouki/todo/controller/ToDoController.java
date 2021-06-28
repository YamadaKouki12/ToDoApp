package jp.kobespiral.yamadakouki.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/member")
public class ToDoController {
    @Autowired
    ToDoService tService;
    /**
     * ToDoサービスへのログイン HTTP-GET /member/login
     * @param 
     * @return
     */
    @GetMapping("/login")
    String todoLogin(){
        model.addAttribute();
        return "login";
    }
    /**
     * ユーザー用・ToDo確認
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/{mid}/todos")
    Strign seeTodos(@PathVariable String user, Model model) {

    }
    /**
    * ユーザ用・ToDoリストを表示 HTTP-POST /{mid}/todo/check
    * @param form
    * @param model
    * @return
    */
   @PostMapping("/{mid}/todo/check") 
   String checkUserToDo(@ModelAttribute(name = "ToDoForm") ToDoForm form,  Model model) {
       model.addAttribute("ToDoForm", form);
       return "check";
   }

}
