package com.smalaca.taskamanager.processor.defined;

import com.smalaca.taskamanager.model.entities.Epic;
import com.smalaca.taskamanager.model.entities.Story;
import com.smalaca.taskamanager.model.entities.Task;
import com.smalaca.taskamanager.model.interfaces.ToDoItem;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemDefinedProcessor {

  private final ToDoItemDefinedEpicProcessor toDoItemDefinedEpicProcessor;
  private final ToDoItemDefinedStoryProcessor toDoItemDefinedStoryProcessor;
  private final ToDoItemDefinedTaskProcessor toDoItemDefinedTaskProcessor;

  public ToDoItemDefinedProcessor(
      ToDoItemDefinedEpicProcessor toDoItemDefinedEpicProcessor,
      ToDoItemDefinedStoryProcessor toDoItemDefinedStoryProcessor,
      ToDoItemDefinedTaskProcessor toDoItemDefinedTaskProcessor) {
    this.toDoItemDefinedEpicProcessor = toDoItemDefinedEpicProcessor;
    this.toDoItemDefinedStoryProcessor = toDoItemDefinedStoryProcessor;
    this.toDoItemDefinedTaskProcessor = toDoItemDefinedTaskProcessor;
  }

  public void process(ToDoItem toDoItem) {
    if (toDoItem instanceof Epic) {
      toDoItemDefinedEpicProcessor.process((Epic) toDoItem);
    } else if (toDoItem instanceof Story) {
      toDoItemDefinedStoryProcessor.process((Story) toDoItem);
    } else {
      toDoItemDefinedTaskProcessor.process((Task) toDoItem);
    }
  }
}
