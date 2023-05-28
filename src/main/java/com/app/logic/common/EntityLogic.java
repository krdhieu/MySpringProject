package com.app.logic.common;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public interface EntityLogic<T, I> {
    @Transactional
    public void saveEntity(T entity);

    @Transactional
    public T findById(I id);
}
