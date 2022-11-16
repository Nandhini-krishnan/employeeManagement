package com.ideas2it.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * <p>
 * Base model class has the getters and setters for basic attributes.
 * </p>
 *
 * @author  Naganandhini
 * @version  1.0  09-AUG-2022
 */
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_deleted", columnDefinition="tinyint(1) default false", nullable = false)
    private boolean isDeleted;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public void setId(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
	return updatedAt;
    }

    public void setDelete(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
	return isDeleted;
    }
}