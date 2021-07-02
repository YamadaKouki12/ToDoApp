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

import ch.qos.logback.core.net.SyslogOutputStream;
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
        List<ToDo> todolist=tService.getToDoList(mid);
        List<ToDo> donelist=tService.getDoneList(mid);
        model.addAttribute("todolist", todolist);
        model.addAttribute("donelist", donelist);
        model.addAttribute("mid", mid);
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
   String checkUserToDo(@PathVariable String mid ,@ModelAttribute(name = "ToDoForm") ToDoForm form,  Model model) {
       model.addAttribute("ToDoForm", form);
       model.addAttribute("mid", mid);
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
        List<ToDo> todolist=tService.getToDoList();
        List<ToDo> donelist=tService.getDoneList();
        model.addAttribute("todolist", todolist);
        model.addAttribute("donelist", donelist);
        return "alllist";
    }

    /**
     * ユーザー用・ToDoを登録 HTTP-POST /member/{mid}/todocheck
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/member/{mid}/todos")
    String todoCheck(@PathVariable String mid,@ModelAttribute(name = "ToDoForm") ToDoForm form, Model model) {
        tService.createToDo(mid,form);
        return "redirect:/member/"+mid+"/todos";
    }

    /**
    * ユーザ用・ToDoをdoneにする HTTP-Get /member/{mid}/makedone
    * @param form
    * @param model
    * @return
    */
    @GetMapping("/member/{mid}/makedone/{seq}") 
    String makeDone(@PathVariable String mid,@PathVariable Long seq,@ModelAttribute(name = "ToDoForm") ToDoForm form,Model model) {
        // Todoを完了させる
        // ToDo t = tService.getToDo(seq);
        tService.setDone(seq);
        // tService.createDone(mid, form);
        // tService.deleteToDo(seq);
        return "redirect:/member/"+mid+"/todos";
    }

}
