package ru.akozlovskiy.library.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAcitvity {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonProperty("Имя пользователя")
	private String appUserName;

	@JsonProperty("Тип активности")
	private String activityType;

	@JsonProperty("Количество")
	private long activitiesCount;

	public UserAcitvity(String appUserName, String activityType, long activitiesCount) {
		this.appUserName = appUserName;
		this.activityType = activityType;
		this.activitiesCount = activitiesCount;
	}
}
