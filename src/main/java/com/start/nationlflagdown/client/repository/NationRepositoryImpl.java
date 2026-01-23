package com.start.nationlflagdown.client.repository;

import static com.start.nationlflagdown.client.domain.QNationVO.nationVO;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.nationlflagdown.client.domain.NationVO;
import com.start.nationlflagdown.client.domain.QImageVO;
import com.start.nationlflagdown.client.dto.NationSearchCond;

import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

public class NationRepositoryImpl implements NationRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	public NationRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

//	@Override
//	public List<NationVO> search(NationSearchCond cond) {
//		
//		return queryFactory.selectFrom(nationVO).where(searchCond(cond.getSearch())).orderBy(nationVO.capitarEn.asc()).fetch();
//	}
	
	//페이징 때문에 위 search() 메서드를 수정하였음
	@Override
	public Page<NationVO> search(NationSearchCond cond, Pageable pageable){
		
		QImageVO image = QImageVO.imageVO;
		
		List<NationVO> content = queryFactory
				.selectFrom(nationVO)
				.leftJoin(nationVO.images, image).fetchJoin()
				.where(searchCond(cond.getSearch()))
				.orderBy(nationVO.nationCode.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		Long total = queryFactory
				.select(nationVO.count())
				.from(nationVO)
				.where(searchCond(cond.getSearch()))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total != null ? total : 0L);	//PageImpl<>(조회한리스트, pageable객체, 전체개수);
	}
	
	@Override
	public Page<NationVO> select(NationSearchCond cond, Pageable pageable){
		
		List<NationVO> content = queryFactory
				.selectFrom(nationVO)
				.where(selectContinent(cond.getContinent()))
				.orderBy(nationVO.capitarEn.asc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		Long total = queryFactory
				.select(nationVO.count())
				.from(nationVO)
				.where(selectContinent(cond.getContinent()))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total != null ? total : 0L);	//PageImpl<>(조회한리스트, pageable객체, 전체개수);
	}
	
	
	//검색창 빈값 검사 및 검색 키워드로 찾는 컬럼
	private BooleanExpression searchCond(String keyword) {
		
		if(keyword == null || keyword.isBlank()) {
			return null;
		}
		
		//키워드를 넣고 컬럼 조회
		return nationVO.nationNameKo.contains(keyword)
				.or(nationVO.nationNameEn.contains(keyword))
				.or(nationVO.capitarKo.contains(keyword))
				.or(nationVO.capitarEn.contains(keyword));
		
	}
	
	private BooleanExpression selectContinent(String continent) {
		
		if(continent == null || continent.isBlank() || continent.equalsIgnoreCase("All")) {
			return null;
		}
		
		return nationVO.continent.eq(continent);
	}

}
