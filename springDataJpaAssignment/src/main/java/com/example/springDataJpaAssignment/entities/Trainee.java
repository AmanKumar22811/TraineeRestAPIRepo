package com.example.springDataJpaAssignment.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Trainee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int traineeId;
	
	@NotEmpty(message = "Name must not be empty")
	private String tarineeName;
	
	private String Domain;
	
	private String Location;

	public int getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}

	public String getTarineeName() {
		return tarineeName;
	}

	public void setTarineeName(String tarineeName) {
		this.tarineeName = tarineeName;
	}

	public String getDomain() {
		return Domain;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", tarineeName=" + tarineeName + ", Domain=" + Domain + ", Location="
				+ Location + "]";
	}
	
	
	
}
