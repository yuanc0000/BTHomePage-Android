package com.example.bthomepage;

import com.google.firebase.Timestamp;

import java.util.List;

//class referenced when pulling user data
public class MyUser {
        private String name;
        private String email;
        private String uid;
        private List<Timestamp> completedExerciseDates;

        public MyUser() {}

        public MyUser(String name, String email, String uid,  List<Timestamp> completedExerciseDates) {
            // ...
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getUid() {
            return uid;
        }

        public List<Timestamp> getCompletedExerciseDates() {
            return completedExerciseDates;
        }

    }


