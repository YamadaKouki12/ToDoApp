package jp.kobespiral.yamadakouki.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import jp.kobespiral.yamadakouki.todo.dto.ToDoForm;
import jp.kobespiral.yamadakouki.todo.repository.ToDoRepository;
import jp.kobespiral.yamadakouki.todo.entity.ToDo;
import jp.kobespiral.yamadakouki.todo.exception.ToDoAppException;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository tRepo;
    /**
     * TODOを作成する(C)
     * @param form
     * @return
     */
    public ToDo createToDo(String mid,ToDoForm form) {
        ToDo t = form.toEntity();
        t.setMid(mid);
        t.setCreatedAt(new Date());
        t.setDone(false);
        return tRepo.save(t);
    }

    public ToDo getToDo(Long seq) {
        ToDo t = tRepo.findById(seq).orElseThrow(
            () -> new ToDoAppException(ToDoAppException.NO_SUCH_SEQUENCE_EXISTS, seq + ": No such sequence exists"));
        return t;
    }

    public List<ToDo> getToDoList(String mid) {
        return tRepo.findByMidAndDone(mid, false);
    }
    public List<ToDo> getDoneList(String mid) {
        return tRepo.findByMidAndDone(mid,true);
    }
    public List<ToDo> getToDoList() {
        return tRepo.findByDone(false);
    }
    public List<ToDo> getDoneList() {
        return tRepo.findByDone(true);
    }

}