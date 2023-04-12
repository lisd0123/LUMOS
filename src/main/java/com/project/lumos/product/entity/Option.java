package com.project.lumos.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_OPTION")
@SequenceGenerator(
		name = "OPTION_SEQ_GENERATOR",
		sequenceName = "SEQ_OP_CODE",
		initialValue = 359, allocationSize = 1
)
public class Option {
	
		@Id
		@Column(name = "OP_CODE")
		@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "OPTION_SEQ_GENERATOR"
		)
		private int opCode;
		
		@Column(name = "OPTION_NM")
		private String optionNm;
		
		@Column(name = "OPTION_STOCK")
		private int optionStock;
				
		@Column(name = "PD_CODE")
		private int pdCode;

		public Option() {
		}

		public Option(int opCode, String optionNm, int optionStock, int pdCode) {
			this.opCode = opCode;
			this.optionNm = optionNm;
			this.optionStock = optionStock;
			this.pdCode = pdCode;
		}

		public int getOpCode() {
			return opCode;
		}

		public void setOpCode(int opCode) {
			this.opCode = opCode;
		}

		public String getOptionNm() {
			return optionNm;
		}

		public void setOptionNm(String optionNm) {
			this.optionNm = optionNm;
		}

		public int getOptionStock() {
			return optionStock;
		}

		public void setOptionStock(int optionStock) {
			this.optionStock = optionStock;
		}

		public int getPdCode() {
			return pdCode;
		}

		public void setPdCode(int pdCode) {
			this.pdCode = pdCode;
		}

		@Override
		public String toString() {
			return "Option [opCode=" + opCode + ", optionNm=" + optionNm + ", optionStock=" + optionStock + ", pdCode="
					+ pdCode + "]";
		}
		
		
}
