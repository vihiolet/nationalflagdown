package com.start.nationlflagdown.admin.repository;

import static com.start.nationlflagdown.admin.domain.QAdmNationVO.admNationVO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.nationlflagdown.admin.domain.AdmNationVO;
import com.start.nationlflagdown.admin.dto.AdmSearchCond;

import jakarta.persistence.EntityManager;

public class AdmNationRepositoryImpl implements AdmRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	public AdmNationRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<AdmNationVO> search(AdmSearchCond cond, Pageable pageable) {
		
		List<AdmNationVO> content = queryFactory
				.selectFrom(admNationVO)
				.where(searchCond(cond.getSearch()))
				.orderBy(admNationVO.nationCode.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		Long total = queryFactory
				.select(admNationVO.count())
				.from(admNationVO)
				.where(searchCond(cond.getSearch()))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total != null ? total : 0L);
	}

	private Predicate searchCond(String keyword) {
		
		if(keyword == null || keyword.isBlank()) {
			return null;
		}
		
		return admNationVO.nationNameKo.contains(keyword)
				.or(admNationVO.nationNameEn.contains(keyword))
				.or(admNationVO.capitarKo.contains(keyword))
				.or(admNationVO.capitarEn.contains(keyword));
	}

}
