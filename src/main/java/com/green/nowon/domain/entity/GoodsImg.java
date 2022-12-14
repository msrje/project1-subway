package com.green.nowon.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@ToString(exclude = "goods")
@Getter
@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "gen_seq_img",
sequenceName = "seq_img", initialValue = 1, allocationSize = 1)
@Table(name = "picture")
@Entity
public class GoodsImg extends BaseDateEntity{
	
	@Id
	@GeneratedValue(generator = "gen_seq_img", strategy = GenerationType.SEQUENCE)
	private long fno;
	@Column(nullable = false)
	private String url;
	@Column(nullable = false)
	private String orgName;
	@Column(nullable = false)
	private String newName;
	@Column(nullable = false)
	private long size;
	
	private boolean def;

	//대표이미지를 세팅해주는 편의메서드
	public GoodsImg def(boolean def) {
		this.def=def;
		return this;
	}

	

	

}
