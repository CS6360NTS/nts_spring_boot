package com.utd.nts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utd.nts.entity.NtsUserEntity;

/**
 * 
 * @author navaneethkumarbuddi
 *
 */

public interface NtsUserRepository extends JpaRepository<NtsUserEntity, Integer> {

}
