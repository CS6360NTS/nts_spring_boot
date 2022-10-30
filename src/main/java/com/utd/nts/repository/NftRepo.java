package com.utd.nts.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utd.nts.entity.NtsNftEntity;

/**
 * 
 * @author NXB210086
 *
 */
public interface NftRepo extends JpaRepository<NtsNftEntity, String> {

	@Query(value = "SELECT * FROM nts_nft N WHERE N.contract_ethereum_address=?1", nativeQuery = true)
	Collection<NtsNftEntity> findAllByContractEthereumAddress(String contractEthereumAddress);
}
