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
//@RequestMapping("/member")
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
        // List<ToDo> list=tService.getToDoList(mid);
        // model.addAttribute("ToDoList", list);
        // model.addAttribute("name", m.getName());
        // model.addAttribute("name","aiueo");
        return "redirect:/member/" + mid + "/todos";
    }
    /**
     * ユーザー用・ToDo確認 HTTP-GET /member/{mid}/todos
     * @param form
     * @param model
     * @return
     */
    @GetMapping("/member/{mid}/todos")
    String seeTodos(@PathVariable String mid, Model model) {
        ToDoForm form = new ToDoForm();
        Member m = mService.getMember(mid);
        List<ToDo> list=tService.getToDoList(mid);
        model.addAttribute("mid", mid);
        model.addAttribute("list", list);
        model.addAttribute("m",m);
        model.addAttribute("ToDoForm",form);
        return "list";
    }
    /**
    * ユーザ用・登録するToDoを確認 HTTP-POST /member/{mid}/todocheck
    * @param form
    * @param model
    * @return
    */
   @PostMapping("/member/{mid}/todocheck") 
   String checkUserToDo(@ModelAttribute(name = "ToDoForm") ToDoForm form,  Model model) {
       model.addAttribute("ToDoForm", form);
       return "todocheck";
   }
   /**
    * ユーザ用・みんなのToDoリストを確認 HTTP-Get /member/alllist
    * @param form
    * @param model
    * @return
    */
    @GetMapping("/member/alllist") 
    String seeAllTodos(Model model) {
        List<ToDo> donelist=tService.getToDoList();
        List<ToDo> undonelist=tService.getDoneList();
        model.addAttribute("donelist", donelist);
        model.addAttribute("undonelist", undonelist);
        return "alllist";
    }
    
    /**
    * ユーザ用・ToDoをdoneにする HTTP-Get /member/{mid}/makedone
    * @param form
    * @param model
    * @return
    */
    @GetMapping("/member/{mid}/makedone") 
    String makeDone(Model model) {
        List<ToDo> donelist=tService.getToDoList();
        List<ToDo> undonelist=tService.getDoneList();
        model.addAttribute("donelist", donelist);
        model.addAttribute("undonelist", undonelist);
        return "list";
    }

    // /**
    //  * ユーザー用・ToDoを登録 HTTP-POST /member/{mid}/todocheck
    //  * @param form
    //  * @param model
    //  * @return
    //  */
    // @PostMapping("/member/{mid}/todocheck")
    // String todoCheck(@ModelAttribute(name = "ToDoForm") ToDoForm form, Model model) {
    //     ToDo t = tService.createToDo(form);
    //     model.addAttribute("ToDoForm",t);
    //     return "list";
    // }

}
