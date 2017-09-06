package com.jobmatcher.model;

import javax.persistence.*;
/**
 * Created by gevlad on 14-Dec-16.
 */
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    public Job(String description) {
        this.description = description;
    }

    public Job() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String title) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
