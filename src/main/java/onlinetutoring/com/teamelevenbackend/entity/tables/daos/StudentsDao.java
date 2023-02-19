/*
 * This file is generated by jOOQ.
 */
package onlinetutoring.com.teamelevenbackend.entity.tables.daos;


import java.util.List;
import java.util.Optional;

import onlinetutoring.com.teamelevenbackend.entity.tables.Students;
import onlinetutoring.com.teamelevenbackend.entity.tables.records.StudentsRecord;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StudentsDao extends DAOImpl<StudentsRecord, onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students, Integer> {

    /**
     * Create a new StudentsDao without any configuration
     */
    public StudentsDao() {
        super(Students.STUDENTS, onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students.class);
    }

    /**
     * Create a new StudentsDao with an attached configuration
     */
    public StudentsDao(Configuration configuration) {
        super(Students.STUDENTS, onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students.class, configuration);
    }

    @Override
    public Integer getId(onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Students.STUDENTS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchById(Integer... values) {
        return fetch(Students.STUDENTS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students fetchOneById(Integer value) {
        return fetchOne(Students.STUDENTS.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchOptionalById(Integer value) {
        return fetchOptional(Students.STUDENTS.ID, value);
    }

    /**
     * Fetch records that have <code>favourite_tutor_ids BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchRangeOfFavouriteTutorIds(Integer[] lowerInclusive, Integer[] upperInclusive) {
        return fetchRange(Students.STUDENTS.FAVOURITE_TUTOR_IDS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>favourite_tutor_ids IN (values)</code>
     */
    public List<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchByFavouriteTutorIds(Integer[]... values) {
        return fetch(Students.STUDENTS.FAVOURITE_TUTOR_IDS, values);
    }

    /**
     * Fetch records that have <code>year BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchRangeOfYear(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Students.STUDENTS.YEAR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>year IN (values)</code>
     */
    public List<onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Students> fetchByYear(Integer... values) {
        return fetch(Students.STUDENTS.YEAR, values);
    }
}