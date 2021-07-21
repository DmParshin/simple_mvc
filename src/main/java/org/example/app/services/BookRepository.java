package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public List<Book> getFilteredBooks(Book book) {
        List<Book> allBooks = new ArrayList<>(repo);
        List<Book> result = new ArrayList<>();
        for (Book bookItem : allBooks){
            if(!book.getAuthor().equals("") && bookItem.getAuthor().matches(book.getAuthor())){
                logger.info("filtered book by author completed: " + book);
                result.add(bookItem);
                continue;
            }
            if(!book.getTitle().equals("") && bookItem.getTitle().matches(book.getTitle())){
                logger.info("filtered book by title completed: " + book);
                result.add(bookItem);
                continue;
            }
            if(book.getSize() != null && bookItem.getSize().toString().matches(book.getSize().toString())){
                logger.info("filtered book by size completed: " + book);
                result.add(bookItem);
                continue;
            }
        }
        return result;
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        for (Book book : getAll()) {
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(Book book) {
        for(Book bookItem : getAll()){
            if(!book.getAuthor().equals("") && bookItem.getAuthor().matches(book.getAuthor())){
                logger.info("remove book by author completed: " + book);
                repo.remove(bookItem);
                continue;
            }
            if(!book.getTitle().equals("") && bookItem.getTitle().matches(book.getTitle())){
                logger.info("remove book by title completed: " + book);
                repo.remove(bookItem);
                continue;
            }
            if(book.getSize() != null && bookItem.getSize().toString().matches(book.getSize().toString())){
                logger.info("remove book by size completed: " + book);
                repo.remove(bookItem);
                continue;
            }
        }
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit(){
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy(){
        logger.info("default DESTROY in book repo bean");
    }
}
