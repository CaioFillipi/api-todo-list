package br.com.caio.todolist.service;

import br.com.caio.todolist.entity.Task;
import br.com.caio.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskrepository;

    public List<Task> getAllTasks(){
        List<Task> tasks = taskrepository.findAll();
        return tasks;
    }

    public ResponseEntity<Task> findTaskById(long id){
        return taskrepository.findById(id)
                 .map(task -> ResponseEntity.ok().body(task))
                 .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Task createTask(Task task){
        return taskrepository.save(task);
    }

    public ResponseEntity<Object> removeTask(long id){
       return taskrepository.findById(id)
               .map(task -> {
                   taskrepository.delete(task);
                   return ResponseEntity.noContent().build();
               })
               .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> replaceTask(long id,Task task){

        return taskrepository.findById(id)
                        .map(oldTask -> {
                            oldTask.setId(task.getId());
                            oldTask.setTitle(task.getTitle());
                            oldTask.setDescription(task.getDescription());
                            oldTask.setDeadline(task.getDeadline());
                            Task newTask = createTask(oldTask);
                            return ResponseEntity.ok().body(newTask);
                        }).orElse(ResponseEntity.notFound().build());
    }
}
