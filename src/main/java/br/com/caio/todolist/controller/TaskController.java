package br.com.caio.todolist.controller;

import br.com.caio.todolist.entity.Task;
import br.com.caio.todolist.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/task")
@Api(value="Task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "Lista todas as tasks")
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "Task criada com sucesso"),
            @ApiResponse(code = 500,message = "Erro interno")
    })
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @ApiOperation(value = "Busca uma task por ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id){
       return taskService.findTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@Valid @RequestBody Task task, UriComponentsBuilder builder){
        Task newTask = taskService.createTask(task);
        URI uri = builder.path("/{id}").buildAndExpand(newTask.getId()).toUri();
        return ResponseEntity.created(uri).body(newTask);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable long id){
        return taskService.removeTask(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id,@RequestBody Task task,UriComponentsBuilder uriBuilder){
        Task updatedTask = taskService.replaceTask(id,task).getBody();
        URI uri = uriBuilder.path("/{id}").buildAndExpand(updatedTask.getId()).toUri();
        return ResponseEntity.created(uri).body(updatedTask);
    }
}
