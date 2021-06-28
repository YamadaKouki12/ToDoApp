package jp.kobespiral.yamadakouki.todo.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kobespiral.yamadakouki.todo.dto.MemberForm;
import jp.kobespiral.yamadakouki.todo.dto.ToDoForm;
import jp.kobespiral.yamadakouki.todo.entity.Member;
import jp.kobespiral.yamadakouki.todo.entity.ToDo;
import jp.kobespiral.yamadakouki.todo.service.MemberService;
import jp.kobespiral.yamadakouki.todo.service.ToDoService;


@Controller
// @RequestMapping("/member")
public class ToDoController {
    @Autowired
    ToDoService tService;
    @Autowired
    MemberService mService;
    /**
     * ToDoサービスへのログイン HTTP-GET /member/login
     * @param 
     * @return
     */
    @PostMapping("/login")
    String todoLogin(String mid,Model model){
        Member m = mService.getMember(mid);
        List<ToDo> list=tService.getToDoList(mid);
        model.addAttribute("ToDoList", list);
        model.addAttribute("name", m.getName());
        // model.addAttribute("name","aiueo");
        return "redirect:/" + mid + "/todos";
    }
    /**
     * ユーザー用・ToDo確認
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/{mid}/todos")
    String seeTodos(Model model) {
        return "list";
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
