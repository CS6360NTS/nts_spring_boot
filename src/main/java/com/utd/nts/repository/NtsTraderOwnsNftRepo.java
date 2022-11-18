package com.utd.nts.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utd.nts.entity.NtsTraderOwnsNft;

public interface NtsTraderOwnsNftRepo extends JpaRepository<NtsTraderOwnsNft, String> {
	@Query(value = "SELECT * from nts_db.nts_trader_owns_nft where nts_db.nts_trader_owns_nft.client_id =?1 order by nts_db.nts_trader_owns_nft.last_modified_date DESC;", nativeQuery = true)
	Collection<NtsTraderOwnsNft> findAllNftsByClientId(Integer clientId);

	@Query(value = "SELECT * from nts_db.nts_trader_owns_nft where nts_db.nts_trader_owns_nft.client_id <>?1 and nts_db.nts_trader_owns_nft.is_open_for_trade = true order by nts_db.nts_trader_owns_nft.last_modified_date DESC;", nativeQuery = true)
	Collection<NtsTraderOwnsNft> findAllNftsByExcludingClientId(Integer clientId);
}
