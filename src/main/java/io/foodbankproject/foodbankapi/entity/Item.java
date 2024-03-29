package io.foodbankproject.foodbankapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Item {

	private @Id @GeneratedValue Integer itemId;
	
	private String name;
	
	private String description;
	
	private int itemCount;
	
	@ManyToOne
	@JoinColumn(name="donation_id")
	@JsonBackReference
	private Donation donation;
	
	public Item() {
		
	}

	public Item(String name, String description, int itemCount) {
		this.name = name;
		this.description = description;
		this.itemCount = itemCount;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", description=" + description + ", itemCount=" + itemCount
				+ ", donation=" + donation + "]";
	}
		
}
