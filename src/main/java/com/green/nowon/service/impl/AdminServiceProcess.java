package com.green.nowon.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.admin.AdminUpdateDTO;
import com.green.nowon.domain.entity.GoodsEntity;
import com.green.nowon.domain.entity.GoodsEntityRepository;
import com.green.nowon.domain.entity.GoodsImgEntityRepository;
import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceProcess implements AdminService {

	@Value("${file.location.temp}")
	private String locationTemp;
	
	@Value("${file.location.upload}")
	private String locationUpload;
	
	@Autowired
	private GoodsEntityRepository repo;
	
	@Autowired
	GoodsImgEntityRepository giRepository;
	
	@Transactional
	@Override
	public void update(long gno, AdminUpdateDTO dto) {
		System.out.println(">>>>>>>>>>>>수정처리 실행");
//		repo.findById(gno).map(entity->entity.update(dto));
//		System.out.println(">>>>>>>>>>>>수정처리완료 확인");
		GoodsEntity entityImg=null;
		Optional<GoodsEntity> result= repo.findById(gno);
		if(result.isPresent()) {
			GoodsEntity entity=result.get();
			entity.update(dto);
			entityImg =repo.save(entity);
		}
		//이미지 디비내용물 삭제 작동안함
		dto.toItemListImgs(entityImg, locationUpload).forEach(giRepository::delete);
		//이미지 저장
		dto.toItemListImgs(entityImg, locationUpload).forEach(giRepository::save);
		
	}
	//삭제
	@Override
	public void delete(long gno) {
		repo.deleteById(gno);
		
	}

}
