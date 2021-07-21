package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {

    List<T> getAll();

    List<T> getFilteredBooks(T book);

    void store(T book);

    boolean removeItemById(String bookIdToRemove);

    boolean removeItemByRegex(T book);
}
