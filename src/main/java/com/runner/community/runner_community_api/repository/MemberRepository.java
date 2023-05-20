package com.runner.community.runner_community_api.repository;

import com.runner.community.runner_community_api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("SELECT count(*) FROM Member m WHERE m.email = :email")
    long existsByEmail(@Param("email") String email);
}
