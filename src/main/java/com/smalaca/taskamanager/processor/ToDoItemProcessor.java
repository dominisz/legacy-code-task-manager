package com.smalaca.taskamanager.processor;

import com.smalaca.taskamanager.model.interfaces.ToDoItem;
import com.smalaca.taskamanager.processor.approved.ToDoItemApprovedProcessor;
import com.smalaca.taskamanager.processor.defined.ToDoItemDefinedProcessor;
import com.smalaca.taskamanager.processor.done.ToDoItemDoneProcessor;
import com.smalaca.taskamanager.processor.inprogress.ToDoItemInProgressProcessor;
import com.smalaca.taskamanager.processor.released.ToDoItemReleasedProcessor;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemProcessor {
  private final ToDoItemApprovedProcessor toDoItemApprovedProcessor;
  private final ToDoItemDefinedProcessor toDoItemDefinedProcessor;
  private final ToDoItemDoneProcessor toDoItemDoneProcessor;
  private final ToDoItemInProgressProcessor toDoItemInProgressProcessor;
  private final ToDoItemReleasedProcessor toDoItemReleasedProcessor;

  public ToDoItemProcessor(
      ToDoItemApprovedProcessor toDoItemApprovedProcessor,
      ToDoItemDefinedProcessor toDoItemDefinedProcessor,
      ToDoItemDoneProcessor toDoItemDoneProcessor,
      ToDoItemInProgressProcessor toDoItemInProgressProcessor,
      ToDoItemReleasedProcessor toDoItemReleasedProcessor) {
    this.toDoItemApprovedProcessor = toDoItemApprovedProcessor;
    this.toDoItemDefinedProcessor = toDoItemDefinedProcessor;
    this.toDoItemDoneProcessor = toDoItemDoneProcessor;
    this.toDoItemInProgressProcessor = toDoItemInProgressProcessor;
    this.toDoItemReleasedProcessor = toDoItemReleasedProcessor;
  }

  public void processFor(ToDoItem toDoItem) {
    switch (toDoItem.getStatus()) {
      case DEFINED:
        toDoItemDefinedProcessor.process(toDoItem);
        break;
      case IN_PROGRESS:
        toDoItemInProgressProcessor.process(toDoItem);
        break;
      case DONE:
        toDoItemDoneProcessor.process(toDoItem);
        break;
      case APPROVED:
        toDoItemApprovedProcessor.process(toDoItem);
        break;
      case RELEASED:
        toDoItemReleasedProcessor.process(toDoItem);
        break;
      default:
        break;
    }
  }
}
