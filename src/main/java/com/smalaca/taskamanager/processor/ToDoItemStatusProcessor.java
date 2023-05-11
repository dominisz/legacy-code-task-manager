package com.smalaca.taskamanager.processor;

import com.smalaca.taskamanager.model.enums.ToDoItemStatus;
import com.smalaca.taskamanager.model.interfaces.ToDoItem;

public interface ToDoItemStatusProcessor {

  ToDoItemStatus forStatus();

  void process(ToDoItem toDoItem);
}
