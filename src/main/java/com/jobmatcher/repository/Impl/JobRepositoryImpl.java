package com.jobmatcher.repository.Impl;

import com.jobmatcher.repository.JobRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by gevlad on 14-Dec-16.
 */
@Repository
public class JobRepositoryImpl implements JobRepository{

    @PersistenceContext
    private EntityManager entityManager;
}

