package jp.kobespiral.yamadakouki.todo.dto;

import java.util.Date;

import jp.kobespiral.yamadakouki.todo.entity.ToDo;
import lombok.Data;
@Data
public class ToDoForm {
    String title; //ToDoのタイトル

    public ToDo toEntity(){
        ToDo t = new ToDo();
        t.setTitle(title);
        t.setCreatedAt(new Date());
        return t;
    }
}
