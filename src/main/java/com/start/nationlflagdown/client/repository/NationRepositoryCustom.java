package com.start.nationlflagdown.client.repository;


import com.start.nationlflagdown.client.domain.NationVO;
import com.start.nationlflagdown.client.dto.NationSearchCond;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NationRepositoryCustom {
	 Page<NationVO> search(NationSearchCond cond, Pageable pageable);
	 
	 Page<NationVO> select(NationSearchCond cond, Pageable pageable);
}
