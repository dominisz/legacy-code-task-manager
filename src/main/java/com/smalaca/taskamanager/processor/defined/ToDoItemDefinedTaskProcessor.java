package com.smalaca.taskamanager.processor.defined;

import com.smalaca.taskamanager.model.entities.Task;
import com.smalaca.taskamanager.service.SprintBacklogService;
import org.springframework.stereotype.Component;

@Component
class ToDoItemDefinedTaskProcessor {

  private final SprintBacklogService sprintBacklogService;

  public ToDoItemDefinedTaskProcessor(SprintBacklogService sprintBacklogService) {
    this.sprintBacklogService = sprintBacklogService;
  }

  void process(Task task) {
    sprintBacklogService.moveToReadyForDevelopment(task, task.getCurrentSprint());
  }
}
