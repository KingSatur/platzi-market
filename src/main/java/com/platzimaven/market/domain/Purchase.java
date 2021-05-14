package com.platzimaven.market.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Purchase {

		private int purchaseId;
		private String clienteId;
		private LocalDateTime date;
		private String paymenMethod;
		private String comment;
		private String state;
		private List<PurchaseItem> items;
		public int getPurchaseId() {
			return purchaseId;
		}
		public void setPurchaseId(int purchaseId) {
			this.purchaseId = purchaseId;
		}
		public String getClienteId() {
			return clienteId;
		}
		public void setClienteId(String clienteId) {
			this.clienteId = clienteId;
		}
		public LocalDateTime getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public String getPaymenMethod() {
			return paymenMethod;
		}
		public void setPaymenMethod(String paymenMethod) {
			this.paymenMethod = paymenMethod;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public List<PurchaseItem> getItems() {
			return items;
		}
		public void setItems(List<PurchaseItem> item) {
			this.items = item;
		}
		
		
		
	
}
