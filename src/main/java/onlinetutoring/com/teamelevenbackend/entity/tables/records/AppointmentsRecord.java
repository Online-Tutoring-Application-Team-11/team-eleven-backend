/*
 * This file is generated by jOOQ.
 */
package onlinetutoring.com.teamelevenbackend.entity.tables.records;


import java.time.LocalDateTime;

import onlinetutoring.com.teamelevenbackend.entity.tables.Appointments;

import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppointmentsRecord extends TableRecordImpl<AppointmentsRecord> implements Record5<Integer, Integer, LocalDateTime, LocalDateTime, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.appointments.tutor_id</code>.
     */
    public AppointmentsRecord setTutorId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.appointments.tutor_id</code>.
     */
    public Integer getTutorId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.appointments.student_id</code>.
     */
    public AppointmentsRecord setStudentId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.appointments.student_id</code>.
     */
    public Integer getStudentId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.appointments.start_time</code>.
     */
    public AppointmentsRecord setStartTime(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.appointments.start_time</code>.
     */
    public LocalDateTime getStartTime() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.appointments.end_time</code>.
     */
    public AppointmentsRecord setEndTime(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.appointments.end_time</code>.
     */
    public LocalDateTime getEndTime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>public.appointments.subject</code>.
     */
    public AppointmentsRecord setSubject(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.appointments.subject</code>.
     */
    public String getSubject() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, LocalDateTime, LocalDateTime, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, Integer, LocalDateTime, LocalDateTime, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Appointments.APPOINTMENTS.TUTOR_ID;
    }

    @Override
    public Field<Integer> field2() {
        return Appointments.APPOINTMENTS.STUDENT_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return Appointments.APPOINTMENTS.START_TIME;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Appointments.APPOINTMENTS.END_TIME;
    }

    @Override
    public Field<String> field5() {
        return Appointments.APPOINTMENTS.SUBJECT;
    }

    @Override
    public Integer component1() {
        return getTutorId();
    }

    @Override
    public Integer component2() {
        return getStudentId();
    }

    @Override
    public LocalDateTime component3() {
        return getStartTime();
    }

    @Override
    public LocalDateTime component4() {
        return getEndTime();
    }

    @Override
    public String component5() {
        return getSubject();
    }

    @Override
    public Integer value1() {
        return getTutorId();
    }

    @Override
    public Integer value2() {
        return getStudentId();
    }

    @Override
    public LocalDateTime value3() {
        return getStartTime();
    }

    @Override
    public LocalDateTime value4() {
        return getEndTime();
    }

    @Override
    public String value5() {
        return getSubject();
    }

    @Override
    public AppointmentsRecord value1(Integer value) {
        setTutorId(value);
        return this;
    }

    @Override
    public AppointmentsRecord value2(Integer value) {
        setStudentId(value);
        return this;
    }

    @Override
    public AppointmentsRecord value3(LocalDateTime value) {
        setStartTime(value);
        return this;
    }

    @Override
    public AppointmentsRecord value4(LocalDateTime value) {
        setEndTime(value);
        return this;
    }

    @Override
    public AppointmentsRecord value5(String value) {
        setSubject(value);
        return this;
    }

    @Override
    public AppointmentsRecord values(Integer value1, Integer value2, LocalDateTime value3, LocalDateTime value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AppointmentsRecord
     */
    public AppointmentsRecord() {
        super(Appointments.APPOINTMENTS);
    }

    /**
     * Create a detached, initialised AppointmentsRecord
     */
    public AppointmentsRecord(Integer tutorId, Integer studentId, LocalDateTime startTime, LocalDateTime endTime, String subject) {
        super(Appointments.APPOINTMENTS);

        setTutorId(tutorId);
        setStudentId(studentId);
        setStartTime(startTime);
        setEndTime(endTime);
        setSubject(subject);
    }

    /**
     * Create a detached, initialised AppointmentsRecord
     */
    public AppointmentsRecord(onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Appointments value) {
        super(Appointments.APPOINTMENTS);

        if (value != null) {
            setTutorId(value.getTutorId());
            setStudentId(value.getStudentId());
            setStartTime(value.getStartTime());
            setEndTime(value.getEndTime());
            setSubject(value.getSubject());
        }
    }
}
