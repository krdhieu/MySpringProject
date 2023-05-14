package com.app.logic;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
@Component
public interface EntityLogic<T, I> {
    public void saveEntity(T entity);

    @Transactional
    public T findById(I id);
}
