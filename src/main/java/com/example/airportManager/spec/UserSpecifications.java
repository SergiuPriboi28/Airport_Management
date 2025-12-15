package com.example.airportManager.spec;

import com.example.airportManager.model.User;
import com.example.airportManager.model.UserStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public final class UserSpecifications {

    private UserSpecifications(){};


    public static Specification<User> createdBetween(LocalDateTime from, LocalDateTime to){
        return (root, query, cb) -> {
            if (from == null && to == null) return null;
            if (from != null && to !=null) {
                return cb.between(root.get("createdAt"), from, to);
            } else if (from != null) {
                return cb.greaterThanOrEqualTo(root.get("createdAt"), from);
            } else {
                return cb.lessThanOrEqualTo(root.get("createdAt"), to);
            }
        };
    }

    public static Specification<User> hasStatus(UserStatus status){
        return(root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"),status);
    }

}
