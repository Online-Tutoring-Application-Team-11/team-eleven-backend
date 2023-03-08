package onlinetutoring.com.teamelevenbackend.service;

import onlinetutoring.com.teamelevenbackend.controller.models.UpdateTutorRequest;
import onlinetutoring.com.teamelevenbackend.entity.tables.records.TutorsRecord;
import onlinetutoring.com.teamelevenbackend.entity.tables.records.UsersRecord;
import onlinetutoring.com.teamelevenbackend.models.TutorUser;
import org.apache.commons.collections4.CollectionUtils;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static onlinetutoring.com.teamelevenbackend.entity.Tables.USERS;
import static onlinetutoring.com.teamelevenbackend.entity.Tables.TUTORS;

@Component
public class TutorService {

    private static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR = new StrongPasswordEncryptor();

    private DSLContext dslContext;
    @Autowired
    public void setDslContext(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public ResponseEntity<List<TutorUser>> getAllTutors() throws SQLException {
        try {
            // fetching all tutors
            Result<TutorsRecord> allTutors = dslContext.fetch(TUTORS, TUTORS.ID.ge(0));

            List<TutorUser> response = new ArrayList<>();

            for (TutorsRecord tutor : allTutors) {
                UsersRecord resUser = dslContext.fetch(USERS, USERS.ID.eq(tutor.getId())).get(0);
                response.add(buildTutorUser(resUser, tutor));
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            throw new SQLException("Could not fetch all tutors", ex);
        }
    }

    public List<Integer> validateIsTutor(List<Integer> tutorIds) {
        if (CollectionUtils.isEmpty(tutorIds)) {
            return Collections.emptyList();
        }

        List<Integer> finalTutorList = new ArrayList<>();
        for (Integer id : tutorIds) {
            Result<TutorsRecord> tutorData = dslContext.fetch(TUTORS, TUTORS.ID.eq(id));
            if (tutorData.isNotEmpty()) {
                finalTutorList.add(tutorData.get(0).getId());
            }
        }

        return finalTutorList;
    }

//    public List<String> availableSubjects(List<String> subjects) {
//        if (CollectionUtils.isEmpty(subjects)) {
//            return Collections.emptyList();
//        }
//
//        List<String> SubjectList = new ArrayList<>();
//        for (String subject : subjects) {
//            Result<TutorsRecord> tutorData = dslContext.fetch(TUTORS, TUTORS.SUBJECTS.eq(subject));
//            if (tutorData.isNotEmpty()) {
//                SubjectList.add(tutorData.get(0).getSubjects());
//            }
//        }
//
//        return SubjectList;
//    }

    public boolean insertIntoTutors(int id, List<String> subjects) throws SQLException {
        try {
        dslContext.insertInto(TUTORS)
                .set(TUTORS.ID, id)
                .set(TUTORS.SUBJECTS, subjects.toArray(new String[0]))
                .execute();
        // NOTE: Maximum subjects taught by a tutor is 100

            Result<TutorsRecord> resTutors = dslContext.fetch(TUTORS, TUTORS.ID.eq(id));

            // check if insert failed
            return !resTutors.isEmpty();
        } catch (Exception ex) {
            throw new SQLException("Could not insert data into tutors", ex);
        }
    }

    public ResponseEntity<TutorUser> getTutorByEmail(String email) throws SQLException {
        if (StringUtils.isEmpty(email)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Result<UsersRecord> userData = dslContext.fetch(USERS, USERS.EMAIL.eq(email));
            if (userData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            UsersRecord usersRecord = userData.get(0);

            Result<TutorsRecord> tutorData = dslContext.fetch(TUTORS, TUTORS.ID.eq(usersRecord.getId()));
            if (tutorData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(this.buildTutorUser(usersRecord, tutorData.get(0)), HttpStatus.OK);
        } catch (Exception ex) {
            throw new SQLException("Could not query data", ex);
        }
    }

    public ResponseEntity<TutorUser> updateTutor(UpdateTutorRequest updateTutorRequest) throws SQLException {
        if (StringUtils.isEmpty(updateTutorRequest.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Result<UsersRecord> resUserBefore = dslContext.fetch(USERS, USERS.EMAIL.eq(updateTutorRequest.getEmail()));

            // user does not exists
            if (resUserBefore.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // update user
            dslContext.update(USERS)
                    .set(USERS.F_NAME, updateTutorRequest.getfName())
                    .set(USERS.L_NAME, updateTutorRequest.getlName())
                    .set(USERS.PROFILE_PIC, updateTutorRequest.getProfilePic())
                    .set(USERS.ABOUT_ME, updateTutorRequest.getAboutMe())
                    .set(USERS.PASSWORD, PASSWORD_ENCRYPTOR.encryptPassword(updateTutorRequest.getPassword()))
                    .where(USERS.EMAIL.eq(updateTutorRequest.getEmail()))
                    .execute();

            UsersRecord resUser = dslContext.fetch(USERS, USERS.EMAIL.eq(updateTutorRequest.getEmail())).get(0);

            // update tutors
            dslContext.update(TUTORS)
                    .set(TUTORS.SUBJECTS, updateTutorRequest.getSubjects().toArray(new String[0]))
                    .where(TUTORS.ID.eq(resUser.getId()))
                    .execute();

            TutorsRecord resTutor = dslContext.fetch(TUTORS, TUTORS.ID.eq(resUser.getId())).get(0);


            return new ResponseEntity<>(this.buildTutorUser(resUser, resTutor), HttpStatus.OK);
        } catch (Exception ex) {
            throw new SQLException("Could not update Tutor", ex);
        }
    }

    private TutorUser buildTutorUser(UsersRecord usersRecord, TutorsRecord tutorsRecord) {
        TutorUser response = new TutorUser();

        // user data
        response.setId(usersRecord.getId());

        response.setFName(usersRecord.getFName());
        response.setLName(usersRecord.getLName());
        response.setEmail(usersRecord.getEmail());
        // PASSWORD SET AS NULL (SHOULD NOT BE A PART OF THE RESPONSE)
        response.setPassword(null);
        response.setTotalHours(usersRecord.getTotalHours()); // Can we use this as available hours for tutors
        response.setTutor(true);
        response.setProfilePic(usersRecord.getProfilePic());
        response.setAboutMe(usersRecord.getAboutMe());

        // tutor data
        response.setSubjects(Arrays.asList(tutorsRecord.getSubjects()));

        return response;
    }
}
