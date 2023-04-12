package com.project.lumos.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMAGE")
@SequenceGenerator(
		name = "IMG_SEQ_GENERATOR",
		sequenceName = "SEQ_IMG_NUM",
		initialValue = 359, allocationSize = 1
)
public class ProductImage {
	
		@Id
		@Column(name = "IMG_NUM")
		@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "IMG_SEQ_GENERATOR"
		)
		private int imgNum;
		
		@Column(name = "PD_IMG_PATH")
		private String pdImgPath;
		
		@Column(name = "PD_CODE")
		private int pdCode;
		
		@Column(name = "MAIN_IMG")
		private String mainImg;

		public ProductImage() {
		}

		public ProductImage(int imgNum, String pdImgPath, int pdCode, String mainImg) {
			this.imgNum = imgNum;
			this.pdImgPath = pdImgPath;
			this.pdCode = pdCode;
			this.mainImg = mainImg;
		}

		public int getImgNum() {
			return imgNum;
		}

		public void setImgNum(int imgNum) {
			this.imgNum = imgNum;
		}

		public String getPdImgPath() {
			return pdImgPath;
		}

		public void setPdImgPath(String pdImgPath) {
			this.pdImgPath = pdImgPath;
		}

		public int getPdCode() {
			return pdCode;
		}

		public void setPdCode(int pdCode) {
			this.pdCode = pdCode;
		}

		public String getMainImg() {
			return mainImg;
		}

		public void setMainImg(String mainImg) {
			this.mainImg = mainImg;
		}

		@Override
		public String toString() {
			return "ProductImage [imgNum=" + imgNum + ", pdImgPath=" + pdImgPath + ", pdCode=" + pdCode + ", mainImg="
					+ mainImg + "]";
		}
		
}
